package ing.kata.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ing.kata.business.BankBusiness;
import ing.kata.business.OperationDeposit;
import ing.kata.business.OperationWithdrawal;
import ing.kata.repository.TransactionRepository;
import ing.kata.service.dto.HistoryDTO;
import ing.kata.service.dto.TransactionDTO;

@RestController
public class BankController {
	
	// users/{id}/accounts/{id}/deposit
	
	
	@Autowired
	private TransactionRepository transactionRepository;
	
	@Autowired
	private BankBusiness bankBusiness;
	
	/**
	 * Permet le dépot d'argent d'un client vers son compte, s'il est supérieur à 0,01€
	 * @return un message à l'utilisateur
	 */
	@PostMapping("/customers/{customerId}/accounts/{accountId}/deposit")
	//@PreAuthorize("hasAuthority('bank_edit')")
	public ResponseEntity<String> deposit(@PathVariable Long customerId, @PathVariable Long accountId, @RequestBody TransactionDTO transactionDTO) {
		
		String depositMessage = bankBusiness.operate(customerId, accountId, new OperationDeposit(transactionDTO.getAmount(),transactionRepository));
		return ResponseEntity.ok(depositMessage);
		
	}
	
	/**
	 *  Permet le retrait d'argent d'un client depuis son compte, s'il n'utilise pas le découvert
	 * @return un message a l'utilisateur
	 */
	@PostMapping("/customers/{customerId}/accounts/{accountId}/withdrawal")
	public ResponseEntity<String> withdraw(@PathVariable Long customerId, @PathVariable Long accountId, @RequestBody TransactionDTO transactionDTO) {
		String withdrawMessage = bankBusiness.operate(customerId, accountId, new OperationWithdrawal(transactionDTO.getAmount(),transactionRepository));
		return ResponseEntity.ok(withdrawMessage);
	}
	
	/**
	 * Permet de consulter l'historique des transactions sur son compte
	 * @return un message à l'utilisateur
	 */
	@GetMapping("/customers/{customerId}/accounts/{accountId}/history")
	public ResponseEntity<HistoryDTO> history(@PathVariable Long customerId, @PathVariable Long accountId){
		HistoryDTO transactionHistory = bankBusiness.operationsHistory(customerId, accountId);
		return  ResponseEntity.ok(transactionHistory);
	}
	
}
