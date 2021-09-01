package ing.kata.service.dto;

import ing.kata.common.BankUtils.transactionType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Utilisée pour le retrait et depot d'argent
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDTO {
	
	public TransactionDTO(double amount) {
		this.amount = amount;
	}
	
	private double amount;
	
	private transactionType type;
}
