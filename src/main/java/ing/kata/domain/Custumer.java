package ing.kata.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Custumer {

	  @Id
	  @GeneratedValue(strategy=GenerationType.AUTO)
	  private Long id;
	  private String firstName;
	  private String lastName;
	  private String phone;
	  
	  
	  public Custumer(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	  }	  
	
}
