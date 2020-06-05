package ing.kata.business;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ing.kata.domain.Account;
import ing.kata.domain.Custumer;
import ing.kata.domain.Transaction;
import ing.kata.repository.AccountRepository;
import ing.kata.repository.CustumerRepository;
import ing.kata.repository.TransactionRepository;

@Service
public class BankBusiness {

	@Autowired
	private CustumerRepository custumerRepository;
	
	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private TransactionRepository transactionRepository;
	
	/**
	 * methode qui permet de deposer d'argent d'un client vers son compte
	 * @return message
	 */
	public String depositMoney(Long CustumerId, Long acountId, double amount) {
		Optional<Custumer> c = custumerRepository.findById(CustumerId);
		Optional<Account> a = accountRepository.findById(acountId);
		String result = "";
		
		if(c.isPresent() && a.isPresent()) {
			Account account = a.get();
				if(amount > 0.1) {
					// MAJ solde
					account.setSolde(account.getSolde() + amount);
					// ajout transaction
					transactionRepository.save(new Transaction("depot de "+ amount + " euros" , account));				
					result = "dépot avec succes!";
				} else {
					result = " le montant déposé doit etre superieur a 0.1 euros";
				}
				
	   } else {
		   result = "client ou compte non trouvé";
	   }
		
		return result;
	}
	
	/**
	 * methode qui permet de retirer d'argent d'un client depuis son compte
	 * @return message
	 */
	public String withdrawMoney(Long CustumerId, Long acountId, double amount) {
		Optional<Custumer> c = custumerRepository.findById(CustumerId);
		Optional<Account> a = accountRepository.findById(acountId);
		String result = "";
		
		if(c.isPresent() && a.isPresent()) {
			Account account = a.get();
				if(account.getSolde() - amount > 0) {
					// MAJ solde
					account.setSolde(account.getSolde() - amount);
					// ajout transaction
					transactionRepository.save(new Transaction("retrait de "+ amount + " euros" , account));
					result = "retrait avec succes!";
				} else {
					result = "vous ne devrez pas etre a decouvert pour pouvoir retirer de l'argent";
				}
				
	   } else {
		   result = "client ou compte non trouvé";
	   }
		
		return result;
	}
	
	/**
	 * permet a un client de consulter le solde de son compte
	 * @return message
	 * @throws Exception
	 */
	public double accountBalance( Long acountId) throws Exception {
		Optional<Account> a = accountRepository.findById(acountId);
		
		if(a.isPresent()) {
			return a.get().getSolde(); 
		} else {
			throw new Exception("compte non trouvé");
		}
	}
	
	/**
	 * permet de consulter l'historique des transaction
	 * @return  message recapitulatif
	 * @throws Exception
	 */
	public String transactionHistory(Long acountId) throws Exception { 
		Optional<Account> a = accountRepository.findById(acountId);
		
		if(a.isPresent()) {
			Account account = a.get();
			
			if(!account.getTransactions().isEmpty()) {
				String history = "Voici l'historique de vos transactions: ";
				for(Transaction transaction : account.getTransactions()) {
					history += transaction.getAction() + " - ";
				}
				return history ;			
			} else {
				return "Aucune transaction";
			}
		} else {
			throw new Exception("compte non trouvé");
		}
		
	}
	
}
