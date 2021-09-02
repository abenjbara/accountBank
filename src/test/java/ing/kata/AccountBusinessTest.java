package ing.kata;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import ing.kata.business.AccountBusiness;
import ing.kata.business.AccountBusinessImpl;
import ing.kata.business.OperationDeposit;
import ing.kata.business.OperationWithdrawal;
import ing.kata.common.BankUtils;
import ing.kata.common.BankUtils.transactionType;
import ing.kata.common.exception.BankException;
import ing.kata.common.exception.NotFoundException;
import ing.kata.domain.Account;
import ing.kata.domain.Customer;
import ing.kata.domain.Transaction;
import ing.kata.repository.AccountRepository;
import ing.kata.repository.TransactionRepository;

@SpringBootTest
public class AccountBusinessTest {
	
	@InjectMocks
	private AccountBusiness accountBusiness = new AccountBusinessImpl();
	
	@Mock
	private TransactionRepository transactionRepository;
	
	@Mock
	private AccountRepository accountRepository;
	
	// Variable
	private Customer owner = new Customer();
	private Account account;
	private final long customerID = 3l;
	private final long accountId = 6l;
	private final double accountAmount = 300.5;
	
	@BeforeEach
    void setMockOutput() {
		owner.setId(customerID);
		
		account = new Account(accountAmount,owner);
		
		when(accountRepository.findById(accountId))
			.thenReturn(Optional.of(account));
	}
	
	@Test
	void shouldDepositMoneyWhenTheAmountIsPositif(){
		double amountToDeposit = 94.5;
		
		Transaction depositTransaction = new Transaction(transactionType.DEPOSIT, account, accountAmount);		
		when(transactionRepository.save(depositTransaction)).thenReturn(new Transaction());
		
		String content = accountBusiness.operate(customerID, accountId, new OperationDeposit(amountToDeposit,transactionRepository));
		assertThat(content).isEqualTo(BankUtils.SUCCESS_DEPOSIT);
		assertThat(account.getSolde()).isEqualTo(accountAmount + amountToDeposit);
	}
	
	@Test
	void shouldNotDepositMoneyWhenTheAmountIsNotPositif(){
		double amountToDeposit = -2;
		
		Transaction depositTransaction = new Transaction(transactionType.DEPOSIT, account, accountAmount);		
		when(transactionRepository.save(depositTransaction)).thenReturn(new Transaction());
		
		Assertions.assertThrows(BankException.class, () -> 
			accountBusiness.operate(customerID, accountId, new OperationDeposit(amountToDeposit,transactionRepository))
		);	
	}	
	
	@Test
	void shouldThrowExceptionWhenTheCustomerDoesntOwnTheAccount() {
		double amountToDeposit = 92;
		long wrongAccountId = 92l;
		
		Transaction depositTransaction = new Transaction(transactionType.DEPOSIT, account, accountAmount);		
		when(transactionRepository.save(depositTransaction)).thenReturn(new Transaction());
		
		Assertions.assertThrows(NotFoundException.class, () -> 
			accountBusiness.operate(customerID,wrongAccountId , new OperationDeposit(amountToDeposit,transactionRepository))
		);		
	}
	
	@Test
	void shouldWithdrawalMoneyWhenTheBalanceIsStillPositif(){
		double amountToWithdrawal = 94.5;
		
		Transaction withdrawalTransaction = new Transaction(transactionType.WITHDRAWAL, account, accountAmount);		
		when(transactionRepository.save(withdrawalTransaction)).thenReturn(new Transaction());
		
		String content = accountBusiness.operate(customerID, accountId, new OperationWithdrawal(amountToWithdrawal,transactionRepository));
		assertThat(content).isEqualTo(BankUtils.SUCCESS_WITHDRAWAL);
		assertThat(account.getSolde()).isEqualTo(accountAmount - amountToWithdrawal);
	}
	
	@Test
	void shouldNotUseTheOverdraft(){
		double amountToWithdrawal = 50;
		double studentAccountBalance = 3.5;
		
		Account studentAccount = new Account(studentAccountBalance, owner);
		studentAccount.setId(55l);
		
		Transaction withdrawalTransaction = new Transaction(transactionType.WITHDRAWAL, studentAccount, accountAmount);		
		when(transactionRepository.save(withdrawalTransaction)).thenReturn(new Transaction());
		
		Assertions.assertThrows(NotFoundException.class, () -> 
			accountBusiness.operate(customerID, studentAccount.getId() , new OperationWithdrawal(amountToWithdrawal,transactionRepository))
		);
	}
}
