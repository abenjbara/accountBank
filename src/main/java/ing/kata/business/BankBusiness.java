package ing.kata.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ing.kata.repository.AccountRepository;
import ing.kata.repository.CustumerRepository;

@Service
public class BankBusiness {

	@Autowired
	private CustumerRepository custumerRepository;
	
	@Autowired
	private AccountRepository accountRepository;
	
	
	public void depositMoney(Long CustumerId, Long acountId, double amount) {
		System.out.println("nous sommes dans le busines, le montant " + amount + " du client " + CustumerId + " est bien recu");
	}
	
}
