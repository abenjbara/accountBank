package ing.kata.business;

import org.springframework.beans.factory.annotation.Value;

import ing.kata.common.exception.BankException;
import ing.kata.domain.Account;
import ing.kata.domain.Transaction;
import ing.kata.repository.TransactionRepository;


public class OperationDeposit implements Operation {
	
	private TransactionRepository transactionRepository;
	
	@Value("${bank.min-deposit}")
	private float minDeposite;
	
	private double amount;
	
	public OperationDeposit(double amount, TransactionRepository transactionRepository) {
		this.amount = amount;
		this.transactionRepository = transactionRepository;
	}

	/**
	 * deposer l'argent d'un client vers son compte
	 * @return message
	 */
	@Override
	public String operate(Account account) {
		if(Double.compare(amount, minDeposite) < 0 ) {
			throw new BankException("The amount deposited must be greater than " +  minDeposite + " euros");
		}
		
		// MAJ solde
		account.setSolde(account.getSolde() + amount);
		// ajout transaction
		transactionRepository.save(new Transaction("deposit of "+ amount + " euros" , account));				
		
		return "Successful deposit!";
	}

}
