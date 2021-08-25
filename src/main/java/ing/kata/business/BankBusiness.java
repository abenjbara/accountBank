package ing.kata.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ing.kata.common.exception.BankException;
import ing.kata.common.exception.NotFoundException;
import ing.kata.domain.Account;
import ing.kata.repository.AccountRepository;

@Service
public class BankBusiness {
	
	private static final String COMPTE_INTROUVABLE = "Account not found";

	@Autowired
	private AccountRepository accountRepository;
	
	/**
	 * methode qui permet de deposer d'argent d'un client vers son compte
	 * @return message
	 */
	public String operate(Long custumerId, Long acountId, Operation operation) {		
		// controle
		Account account =  accountRepository.findById(acountId)
											.orElseThrow(() -> new NotFoundException(COMPTE_INTROUVABLE));
		this.checkAcount(custumerId, account);
		
		// operate
		return operation.operate(account);
	}
	
	
	/**
	 * permet de consulter l'historique des transaction
	 * @return  message recapitulatif

	 */
	public String transactionHistory(Long custumerId, Long acountId) {	
		Account account =  accountRepository.findById(acountId)
				.orElseThrow(() -> new NotFoundException(COMPTE_INTROUVABLE));
		this.checkAcount(custumerId, account);
		
		return new OperationHistory().operate(account);
	}
	
	
	/**
	 * Verifie si le client possède bien le compte 
	 * @throws BankException
	 */
	private void checkAcount(long custumerId, Account account) throws BankException{
		if(!account.getOwner().getId().equals(custumerId)) {
			throw new BankException("The custumer does not manage this account");
		}
	}
	
}
