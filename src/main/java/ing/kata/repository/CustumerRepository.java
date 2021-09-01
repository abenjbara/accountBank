package ing.kata.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ing.kata.domain.Customer;

@Repository
public interface CustumerRepository extends JpaRepository<Customer, Long> {

}
