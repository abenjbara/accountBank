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
	 * methode qui permet de deposer d'argent d'un client evrs on compte
	 * @return
	 */
	public String depositMoney(Long CustumerId, Long acountId, double amount) {
		Optional<Custumer> c = custumerRepository.findById(CustumerId);
		Optional<Account> a = accountRepository.findById(acountId);
		String result = "";
		
		if(c.isPresent() && a.isPresent()) {
			Account account = a.get();
				if(amount > 0.1) {
					account.setSolde(account.getSolde() + amount);
					result = "bien déposer!";
				} else {
					result = " le montant déposé doit etre superieur a 0.1 euros";
				}
				
	   } else {
		   result = "client ou compte non trouvé";
	   }
		
		return result;
	}
	
}
