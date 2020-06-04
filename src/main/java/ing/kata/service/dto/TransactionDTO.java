package ing.kata.service.dto;

/**
 * Utilisée pour le retrait et depot d'argent
 */
public class TransactionDTO {
	
	private Long custumerId;
	
	private Long accountId;
	
	private double amount;

	public Long getCustumerId() {
		return custumerId;
	}

	public void setCustumerId(Long clientId) {
		this.custumerId = clientId;
	}

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}
}
