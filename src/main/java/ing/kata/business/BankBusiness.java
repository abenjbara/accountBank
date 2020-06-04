package ing.kata.business;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ing.kata.domain.Account;
import ing.kata.domain.Custumer;
import ing.kata.repository.AccountRepository;
import ing.kata.repository.CustumerRepository;

@Service
public class BankBusiness {

	@Autowired
	private CustumerRepository custumerRepository;
	
	@Autowired
	private AccountRepository accountRepository;
	
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
					account.setSolde(account.getSolde() + amount);
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
					account.setSolde(account.getSolde() - amount);
					result = "retrait avec succes!";
				} else {
					result = "vous ne devrez pas etre a decouvert pour pouvoir retirer de l'argent";
				}
				
	   } else {
		   result = "client ou compte non trouvé";
	   }
		
		return result;
	}
	
}
