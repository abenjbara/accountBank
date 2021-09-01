package ing.kata.business;

import ing.kata.domain.Account;

public interface Operation {

	/**
	 * Réalise une operation sur le compte en question
	 * @param account
	 */
	public String operate(Account account);
	
}
