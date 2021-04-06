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
public class BankServiceTest {


	@Autowired
	private MockMvc mockMvc;

	// US1
	@Test
	public void shouldDepositMoney() throws Exception {
		TransactionDTO dto = new TransactionDTO(4L, 95.6);
		this.mockMvc.perform( MockMvcRequestBuilders 
				
				
		.post("/bank/deposit")
		.content(asJsonString(dto))
		.contentType(MediaType.APPLICATION_JSON)
		.accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk());
	}
	
	// US1
	@Test
	public void shouldWithdrawtMoney() throws Exception {
		TransactionDTO dto = new TransactionDTO(4L, 95.6);
		this.mockMvc.perform( MockMvcRequestBuilders 
				
				
		.post("/bank/withdraw")
		.content(asJsonString(dto))
		.contentType(MediaType.APPLICATION_JSON)
		.accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk());
	}
	
	// US3
	@Test
	public void shouldshowBalance() throws Exception {
		this.mockMvc.perform(get("/bank/balance/4")).andDo(print()).andExpect(status().isOk());
	}
	
	// US4
	@Test
	public void shouldshowTransactionHistory() throws Exception {
		this.mockMvc.perform(get("/bank/history/4")).andDo(print()).andExpect(status().isOk());
	}
	
	public static String asJsonString(final Object obj) {
	    try {
	        return new ObjectMapper().writeValueAsString(obj);
	    } catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	}

}
