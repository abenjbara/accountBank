package ing.kata.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Data;

@Entity
@Data
public class Account {
	
	public Account(double solde) {
		this.solde = solde;
		transactions = new ArrayList<>();
	}
	
	public Account(double solde,Customer owner) {
		this.solde = solde;
		this.owner = owner;
		this.transactions = new ArrayList<>();
	}
	
	public Account() {
		this.transactions = new ArrayList<>();
	}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	 
	private double solde; 

	@ManyToOne
	@JoinColumn(name="costumer_id")
	private Customer owner;
	
	@OneToMany(mappedBy="account" , cascade = CascadeType.ALL , fetch = FetchType.EAGER)
	private List<Transaction> transactions;
	
}
