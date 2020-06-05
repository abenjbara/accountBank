package ing.kata.service;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ing.kata.business.BankBusiness;
import ing.kata.service.dto.TransactionDTO;

@RestController
@RequestMapping("/bank")
public class BankController {
	
	@Autowired
	private BankBusiness bankBusiness;
	
	/**
	 * US1 : En tant que banque, j'accepte le dépôt d'argent d'un client vers son compte, s'il est supérieur à 0,01€
	 * @return un message a l'utilisateur
	 */
	@PostMapping("/deposit")
	public String deport(@RequestBody TransactionDTO transactionDTO) {
		return bankBusiness.depositMoney(transactionDTO.getCustumerId(), transactionDTO.getAccountId(), transactionDTO.getAmount());
	}
	
	/**
	 * US2 : En tant que banque, j'accepte le dépôt d'argent d'un client vers son compte, s'il est supérieur à 0,01€
	 * @return un message a l'utilisateur
	 */
	@PostMapping("/withdraw")
	public String withdraw(@RequestBody TransactionDTO transactionDTO) {
		return bankBusiness.withdrawMoney(transactionDTO.getCustumerId(), transactionDTO.getAccountId(), transactionDTO.getAmount());
	}
	
	/**
	 * US3: En tant que banque, j'offre la possibilité à mon client de consulter le solde de son compte
	 * @return un message à l'utilisateur
	 * @throws Exception
	 */
	@GetMapping("/balance/{idAccount}")
	public String balance(@PathVariable Long idAccount) throws Exception {
		double balance = bankBusiness.accountBalance(idAccount);
		return "Votre solde est : " + balance;
	}
	
	/**
	 * US4 : En tant que banque, j'offre la possibilité à mon client de consulter l'historique des transactions sur son compte
	 * @return un message à l'utilisateur
	 * @throws Exception
	 */
	@GetMapping("/history/{idAccount}")
	public String history(@PathVariable Long idAccount) throws Exception {
		return bankBusiness.transactionHistory(idAccount);
	}
}
