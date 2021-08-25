package ing.kata;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import ing.kata.domain.Account;
import ing.kata.domain.Custumer;
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
			Custumer c1 = new Custumer("jean", "pierre");		
			Custumer c2 = new Custumer("adam", "benjbara"); 			
			Custumer c3 = new Custumer("celine", "barreau"); 
			
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
			for ( Custumer c : custumerR.findAll()) {
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
