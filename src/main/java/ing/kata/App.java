package ing.kata;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import ing.kata.domain.Account;
import ing.kata.domain.Customer;
import ing.kata.repository.AccountRepository;
import ing.kata.repository.CustumerRepository;
import lombok.extern.slf4j.Slf4j;


@SpringBootApplication
@Slf4j
public class App {
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}
	
	// INITIALISATION DES DONNEES
	@Bean
	public CommandLineRunner commandLineRunner(CustumerRepository custumerR, AccountRepository accountR) {
		return args -> {
			
			//clients
			Customer c1 = new Customer("jean", "pierre");		
			Customer c2 = new Customer("adam", "benjbara"); 			
			Customer c3 = new Customer("celine", "barreau"); 
			
			//comptes
			Account account1 = new Account(256.5);
			Account account2 = new Account(1000.5);
			Account account3 = new Account();
			Account account4 = new Account(79999.6);
			
			custumerR.save(c1);
			custumerR.save(c2);
		    custumerR.save(c3);
		    
		    account1.setOwner(c1);
		    account2.setOwner(c2);
		    account3.setOwner(c3);
		    account4.setOwner(c3);
		    
		    accountR.save(account1);
		    accountR.save(account2);
		    accountR.save(account3);
		    accountR.save(account4);
		    
			log.info("liste des clients" );
			for ( Customer c : custumerR.findAll()) {
				log.info(c.toString());
			}
			
			log.info("liste des comptes" );
			for ( Account a : accountR.findAll()) {
				log.info(a.toString());
			}
			
		};
	}
	
	/**
	 * Configuration CORS
	 */
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedOrigins("http://localhost:4200");
			}
		};
	}
}
