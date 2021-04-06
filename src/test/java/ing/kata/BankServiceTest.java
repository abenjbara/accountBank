package ing.kata;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import ing.kata.service.dto.TransactionDTO;

@SpringBootTest
@AutoConfigureMockMvc
class BankServiceTest {


	private static final long ExistingAccountNumber = 4L;
	
	private static final long NonExistingAccountNumber = 100L;
	
	@Autowired
	private MockMvc mockMvc;

	// US1
	@Test
	void shouldDepositMoney() throws Exception {
		TransactionDTO dto = new TransactionDTO(ExistingAccountNumber, 95.6);
		
		this.mockMvc.perform( MockMvcRequestBuilders 			
					.post("/bank/deposit")
					.content(asJsonString(dto))
					.contentType(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON))
					.andExpect(status().isOk());
	}
	
	// US1
	@Test
	void shouldNotDepositMoney() throws Exception{
		TransactionDTO dto = new TransactionDTO(ExistingAccountNumber, 0);
		
		this.mockMvc.perform( MockMvcRequestBuilders 			
				.post("/bank/deposit")
				.content(asJsonString(dto))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest());	// BankException		
	}
	
	// US2
	@Test
	void shouldWithdrawtMoney() throws Exception {
		TransactionDTO dto = new TransactionDTO(ExistingAccountNumber, 95.6);
		this.mockMvc.perform( MockMvcRequestBuilders 
				
				
		.post("/bank/withdraw")
		.content(asJsonString(dto))
		.contentType(MediaType.APPLICATION_JSON)
		.accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk());
	}
	
	// US3
	@Test
	void shouldShowBalance() throws Exception {
		this.mockMvc.perform(get("/bank/balance/"+ ExistingAccountNumber))
			.andDo(print())
			.andExpect(status().isOk());
	}
	
	// US3
	@Test
	void shouldNotShowBalance() throws Exception {
		this.mockMvc.perform(get("/bank/balance/"+ NonExistingAccountNumber))
			.andDo(print())
			.andExpect(status().isNotFound());  // NotFoundException
	}
	
	// US4
	@Test
	void shouldshowTransactionHistory() throws Exception {
		this.mockMvc.perform(get("/bank/history/"+ ExistingAccountNumber))
			.andDo(print())
			.andExpect(status().isOk());
	}
	
	public static String asJsonString(final Object obj) {
	    try {
	        return new ObjectMapper().writeValueAsString(obj);
	    } catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	}

}
