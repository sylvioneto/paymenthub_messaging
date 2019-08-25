package br.com.spedroza.PaymentHub.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.com.spedroza.PaymentHub.model.Account;

public interface AccountRepository extends CrudRepository<Account, Long> {

	public List<Account> findByAccountNumber(String accountNumber);
}