package ing.kata.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Custumer {

	  @Id
	  @GeneratedValue(strategy=GenerationType.AUTO)
	  private Long id;
	  private String firstName;
	  private String lastName;
	  private String phone;
	  
	  @OneToMany(mappedBy="owner")
	  private List<Account> accounts;
	  
	
	  private String bankName;

	  @Override
	  public String toString() {
	    return String.format(
	        "Customer[id=%d, firstName='%s', lastName='%s']",
	        id, firstName, lastName);
	  }

	  public Long getId() {
	    return id;
	  }

	  public String getFirstName() {
	    return firstName;
	  }

	  public String getLastName() {
	    return lastName;
	  }
	  
	  public String getPhone() {
			return phone;
		}

	  public void setPhone(String phone) {
			this.phone = phone;
	  }

	  public String getBankName() {
			return bankName;
	  }
	
	  public void setBankName(String bankName) {
			this.bankName = bankName;
	 }
	
}
