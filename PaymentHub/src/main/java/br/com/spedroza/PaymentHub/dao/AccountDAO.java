package br.com.spedroza.PaymentHub.dao;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.spedroza.PaymentHub.model.Account;
import br.com.spedroza.PaymentHub.repository.AccountRepository;

public class AccountDAO {

	@Autowired
	private AccountRepository repository;
	
	public void saveMock() {
		System.out.println("Inside saveMock()");
		Account account = new Account();
		account.setBank("001");
		account.setBranch("002");
		account.setAccountNumber("00012345");
		account.setBalance(BigDecimal.ZERO);
		repository.save(account);
		System.out.println("End of saveMock()");
	}
}