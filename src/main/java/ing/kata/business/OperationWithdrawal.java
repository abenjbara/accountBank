package ing.kata.business;

import org.springframework.beans.factory.annotation.Value;

import ing.kata.common.exception.BankException;
import ing.kata.domain.Account;
import ing.kata.domain.Transaction;
import ing.kata.repository.TransactionRepository;

public class OperationWithdrawal implements Operation{

	private TransactionRepository transactionRepository;
	
	@Value("${bank.min-deposit}")
	private float minDeposite;
	
	private double amount;
	
	public OperationWithdrawal(double amount, TransactionRepository transactionRepository) {
		this.amount = amount;
		this.transactionRepository = transactionRepository;
	}

	@Override
	public String operate(Account account) {
		if(account.getSolde() - amount < 0) {
			throw new BankException("You do not have the right to use the overdraft");
		}
		
		// MAJ solde
		account.setSolde(account.getSolde() - amount);
		// ajout transaction
		transactionRepository.save(new Transaction("withdrawal of "+ amount + " euros" , account));

		return "Successful withdrawal!";
	}

}
