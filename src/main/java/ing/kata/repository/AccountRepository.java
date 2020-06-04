package ing.kata.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ing.kata.domain.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {

}
