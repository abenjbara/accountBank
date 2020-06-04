package ing.kata.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ing.kata.business.BankBusiness;
import ing.kata.service.dto.TransactionDTO;

@RestController
@RequestMapping("/bank")
public class BankController {
	
	@Autowired
	private BankBusiness bankBusiness;
	
	@PostMapping("/deposit")
	public String deport(@RequestBody TransactionDTO transactionDTO) {
		bankBusiness.depositMoney(transactionDTO.getAccountId(), transactionDTO.getCustumerId(), transactionDTO.getAmount());
		return "bien déposé";
	}

}
