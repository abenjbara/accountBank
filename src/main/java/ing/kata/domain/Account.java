package ing.kata.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Account {

	 @Id
	 @GeneratedValue(strategy=GenerationType.AUTO)
	 private Long id;
	 
	 private double solde; 
	 
	 public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public double getSolde() {
		return solde;
	}

	public void setSolde(double solde) {
		this.solde = solde;
	}

	public Custumer getOwner() {
		return owner;
	}

	public void setOwner(Custumer owner) {
		this.owner = owner;
	}

	@ManyToOne
	@JoinColumn(name="costumer_id")
	private Custumer owner;
	
	@OneToMany(mappedBy="account" , cascade = CascadeType.ALL)
	private List<Transaction> transactions;

	public Account() {
		transactions = new ArrayList<>();
	}
	
	public Account(double solde) {
		this.solde = solde;
		transactions = new ArrayList<>();
	}
	
	@Override
	public String toString() {
	   return String.format(
	       "Account[id=%d, solde='%f']",
	       id, solde);
	}

	public List<Transaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}
	
	
}
