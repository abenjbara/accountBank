package ing.kata;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import ing.kata.domain.Account;
import ing.kata.domain.Custumer;
import ing.kata.repository.AccountRepository;
import ing.kata.repository.CustumerRepository;

/**
 * Hello world!
 *
 */
@SpringBootApplication
public class App {
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}
	
	@Bean
	public CommandLineRunner commandLineRunner(CustumerRepository custumerR, AccountRepository accountR) {
		return args -> {
			
			Custumer c1 = new Custumer("jean", "pierre");
			c1.addAccount(new Account(15552.4));
			
			Custumer c2 = new Custumer("adam", "benjbara"); 
			c2.addAccount(new Account());
			
			Custumer c3 = new Custumer("celine", "barreau"); 
			c3.addAccount(new Account(666.3));
			c3.addAccount(new Account(10000));
			
			custumerR.save(custumerR.save(c1));
			custumerR.save(custumerR.save(c2));
		    custumerR.save(custumerR.save(c3));
			
			System.out.println("liste des custumer" );
			for ( Custumer c : custumerR.findAll()) {
				System.out.println(c);
			}
			
			System.out.println("liste des compte" );
			for ( Account a : accountR.findAll()) {
				System.out.println(a);
			}
			
		};
	}
}
