package ing.kata.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
	 * US1 : En tant que banque, j'accepte le dépot d'argent d'un client vers son compte, s'il est supérieur à 0,01€
	 * @return un message à l'utilisateur
	 */
	@PostMapping("/deposit")
	public ResponseEntity<String> deposit(@RequestBody TransactionDTO transactionDTO) {
		String depositMessage =  bankBusiness.depositMoney(transactionDTO.getAccountId(), transactionDTO.getAmount());
		return ResponseEntity.ok(depositMessage);
	}
	
	/**
	 * US2 : En tant que banque, j'accepte le retrait d'argent d'un client depuis son compte, s'il n'utilise pas le découvert
	 * @return un message a l'utilisateur
	 */
	@PostMapping("/withdraw")
	public ResponseEntity<String> withdraw(@RequestBody TransactionDTO transactionDTO) {
		String withdrawMessage = bankBusiness.withdrawMoney(transactionDTO.getAccountId(), transactionDTO.getAmount());
		return ResponseEntity.ok(withdrawMessage);
	}
	
	/**
	 * US3: En tant que banque, j'offre la possibilité à mon client de consulter le solde de son compte
	 * @return un message à l'utilisateur
	 */
	@GetMapping("/balance/{idAccount}")
	public ResponseEntity<String> balance(@PathVariable Long idAccount){
		double balance = bankBusiness.accountBalance(idAccount);
		return  ResponseEntity.ok("Votre solde est : " + balance);
	}
	
	/**
	 * US4 : En tant que banque, j'offre la possibilité à mon client de consulter l'historique des transactions sur son compte
	 * @return un message à l'utilisateur
	 */
	@GetMapping("/history/{idAccount}")
	public ResponseEntity<String> history(@PathVariable Long idAccount){
		String transactionHistory = bankBusiness.transactionHistory(idAccount);
		return  ResponseEntity.ok(transactionHistory);
	}
}
