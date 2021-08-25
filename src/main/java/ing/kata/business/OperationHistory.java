package ing.kata.business;

import ing.kata.domain.Account;
import ing.kata.domain.Transaction;

public class OperationHistory implements Operation{

	/**
	 * permet de consulter l'historique des transaction
	 * @return  message recapitulatif
	 */
	@Override
	public String operate(Account account) {
		
		if(!account.getTransactions().isEmpty()) {
			// Mettre tout l'historique dans un seul message (String)
			StringBuilder history = new StringBuilder("Your balance: " + account.getSolde() + ";   Your transactions history: ");			
			for(Transaction transaction : account.getTransactions()) {
				history.append(transaction.getAction() + " - ");
			}
			return history.toString();		
		} 
			
		return  "No transaction yet on this account";
	}

}
