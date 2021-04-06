package ing.kata.service.dto;

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
	
	
	private Long accountId;
	
	private double amount;
}
