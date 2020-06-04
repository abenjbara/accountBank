package ing.kata.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

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
	@JoinColumn(name="costumer_id", nullable=false)
	private Custumer owner;
}
