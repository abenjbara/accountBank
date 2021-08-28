package ing.kata.service.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

/**
 * Utilisée pour afficher l'historique des transcations ainsi que le solde global
 */
@Data
public class HistoryDTO {
	
	public HistoryDTO() {
		balance = 0.0;
		transactions = new ArrayList<>();
	}
	
	
	private Double balance;
	
	private List<TransactionDTO> transactions;

}
