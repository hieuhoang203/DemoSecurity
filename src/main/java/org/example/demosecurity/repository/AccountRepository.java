package org.example.demosecurity.repository;

import org.example.demosecurity.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, String > {

    Optional<Account> findAccountByLogin(String login);

    Boolean existsAccountByLogin(String login);

}
