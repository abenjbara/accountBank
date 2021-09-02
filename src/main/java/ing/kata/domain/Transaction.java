package ing.kata.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import ing.kata.common.BankUtils.transactionType;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Transaction {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	 
	private transactionType type;
	
	private Double amount;
	 
	@ManyToOne
	@JoinColumn(name="account_id")
	private Account account;
	
	public Transaction(transactionType transactionType, Account account, Double amount) {
		this.type = transactionType;
		this.account = account;
		this.amount = amount;
	}
	 
}
