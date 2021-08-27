package ing.kata.business;

import ing.kata.service.dto.HistoryDTO;

public interface BankBusiness {
	
	/**
	 * Après controle, permet de faire une action (ou operation) sur le compte boncaire
	 * @return message
	 */
	public String operate(Long custumerId, Long acountId, Operation operation);
	
	/**
	 * Après controle @return historique des operations ainsi que le solde du client
	 * @param custumerId
	 * @param acountId
	 */
	public HistoryDTO operationsHistory(Long custumerId, Long acountId);
	
}
