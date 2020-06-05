package ing.kata.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ing.kata.domain.Transaction;

@Repository
public interface TransactionRepository  extends JpaRepository<Transaction, Long>{

}
