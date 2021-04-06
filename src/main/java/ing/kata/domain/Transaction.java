package ing.kata.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Transaction {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	 
	private String action; 
	 
	@ManyToOne
	@JoinColumn(name="account_id")
	private Account account;
	
	public Transaction(String action, Account account) {
		this.action = action;
		this.account = account;
	}
	 
}
