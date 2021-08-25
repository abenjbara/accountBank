package ing.kata.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ing.kata.common.exception.BankException;
import ing.kata.common.exception.NotFoundException;
import ing.kata.domain.Account;
import ing.kata.repository.AccountRepository;

@Service
public class BankBusinessImpl implements BankBusiness {
	
	private static final String COMPTE_INTROUVABLE = "Account not found";

	@Autowired
	private AccountRepository accountRepository;
	
	/**
	 * Après controle, permet de faire une action (ou operation) sur le compte boncaire
	 * @return message
	 */
	@Override
	public String operate(Long custumerId, Long acountId, Operation operation) {		
		// controle
		Account account =  accountRepository.findById(acountId)
											.orElseThrow(() -> new NotFoundException(COMPTE_INTROUVABLE));
		this.checkAcount(custumerId, account);
		
		// operate
		return operation.operate(account);
	}
	
	
	/**
	 * Verifie si le client possède bien le compte 
	 * @throws BankException
	 */
	private void checkAcount(long custumerId, Account account) throws BankException{
		if(!account.getOwner().getId().equals(custumerId)) {
			throw new BankException("The custumer does not manage this account");
		}
	}
	
}
