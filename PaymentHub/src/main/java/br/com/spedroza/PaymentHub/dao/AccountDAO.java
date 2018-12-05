package br.com.spedroza.PaymentHub.dao;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.spedroza.PaymentHub.model.Account;

@Repository
@Transactional
public class AccountDAO {

	@PersistenceContext
	private EntityManager manager;	
	
	public void saveMock() {
		System.out.println("Inside saveMock()");
		Account account = new Account();
		account.setBank("001");
		account.setBranch("002");
		account.setAccountNumber("00012345");
		account.setBalance(BigDecimal.ZERO);
		manager.persist(account);
		System.out.println("End of saveMock()");
	}
	
	
	public void save(Account account) {
		System.out.println("Inside save...");
		manager.persist(account);
		System.out.println("End of save...");
	}
	
	
	public List<Account> findAll() {
		System.out.println("Inside findAll...");
		List<Account> resultSet = manager.createQuery("select a from Account a", Account.class).getResultList();
		System.out.println("End of findAll...");
		return resultSet;
	}
	
	public List<Account> findByID(int id) {
		System.out.println("Inside save...");
		System.out.println("Account id = "+id);
		List<Account> resultSet = manager.createQuery("select a from Account where p.id = :id", Account.class)
									.setParameter("id", id)	
									.getResultList();
		System.out.println("End of save...");
		return resultSet;
	}
	
	public List<Account> findByBBA(String bank, String branch, String account) {
		System.out.println("Inside save...");
		System.out.println("Looking for account bank= "+bank+" branch="+branch+" account="+account);
		List<Account> resultSet = manager.createQuery("select a from Account where p.bank = :bank "
				+ " and branch = :branch"
				+ " and account = :account", Account.class)
									.setParameter("bank", bank)
									.setParameter("branch", branch)
									.setParameter("account", account)
									.getResultList();
		System.out.println("End of save...");
		return resultSet;
	}
	
}