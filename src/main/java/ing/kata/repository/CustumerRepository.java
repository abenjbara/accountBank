package ing.kata.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ing.kata.domain.Custumer;

@Repository
public interface CustumerRepository extends CrudRepository<Custumer, Long> {

}
