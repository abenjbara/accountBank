package ing.kata.business;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import ing.kata.common.exception.BankException;
import ing.kata.common.exception.NotFoundException;
import ing.kata.domain.Account;
import ing.kata.domain.Transaction;
import ing.kata.repository.AccountRepository;
import ing.kata.repository.TransactionRepository;

@Service
public class BankBusiness {
	
	private static final String COMPTE_INTROUVABLE = "Compte introuvable";

	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private TransactionRepository transactionRepository;
	
	@Value("${bank.min-deposit}")
	private float minDeposite;
	
	/**
	 * methode qui permet de deposer d'argent d'un client vers son compte
	 * @return message
	 */
	@Transactional
	public String depositMoney(Long acountId, double amount) {
		Account account =  accountRepository.findById(acountId)
											.orElseThrow(() -> new NotFoundException(COMPTE_INTROUVABLE));
		
		if(amount < minDeposite) {
			throw new BankException("Le montant déposé doit etre superieur à " +  minDeposite + " euros");
		}
		
		// MAJ solde
		account.setSolde(account.getSolde() + amount);
		// ajout transaction
		transactionRepository.save(new Transaction("depot de "+ amount + " euros" , account));				
		
		return "dépot avec succes!";
	}
	
	/**
	 * methode qui permet de retirer d'argent d'un client depuis son compte
	 * @return message
	 */
	@Transactional
	public String withdrawMoney(Long acountId, double amount) {
		Account account =  accountRepository.findById(acountId)
				.orElseThrow(() -> new NotFoundException(COMPTE_INTROUVABLE));
		
		if(account.getSolde() - amount < 0) {
			throw new BankException("Vous n'avez pas le droit d'utiliser le découvert");
		}
		
		// MAJ solde
		account.setSolde(account.getSolde() - amount);
		// ajout transaction
		transactionRepository.save(new Transaction("retrait de "+ amount + " euros" , account));

		return "retrait avec succes!";
	}
	
	/**
	 * permet a un client de consulter le solde de son compte
	 * @return message
	 */
	public double accountBalance(Long acountId){
		Account account =  accountRepository.findById(acountId)
				.orElseThrow(() -> new NotFoundException(COMPTE_INTROUVABLE));
		
		return account.getSolde();
	}
	
	/**
	 * permet de consulter l'historique des transaction
	 * @return  message recapitulatif

	 */
	public String transactionHistory(Long acountId) { 
		Account account =  accountRepository.findById(acountId)
				.orElseThrow(() -> new NotFoundException(COMPTE_INTROUVABLE));
			
			if(!account.getTransactions().isEmpty()) {
				
				// Mettre tout l'historique dans un seul message (String)
				StringBuilder history = new StringBuilder("Voici l'historique de vos transactions: ");			
				for(Transaction transaction : account.getTransactions()) {
					history.append(transaction.getAction() + " - ");
				}
				return history.toString();
				
			} else {
				return "Aucune transaction sur ce compte";
			}	
	}
	
}
