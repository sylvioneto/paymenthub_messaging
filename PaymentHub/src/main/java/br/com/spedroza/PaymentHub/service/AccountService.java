package br.com.spedroza.PaymentHub.service;

import java.util.List;

import org.apache.commons.mail.EmailException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.spedroza.PaymentHub.dao.AccountDAO;
import br.com.spedroza.PaymentHub.infra.MailService;
import br.com.spedroza.PaymentHub.model.Account;

@Service
public class AccountService {

	@Autowired
	private AccountDAO accountDao;

	/*
	 * Return the list of all accounts
	 */
	public Iterable<Account> getAll() {
		System.out.println("Inside of AccountService.getAll");
		return accountDao.findAll();
	}

	/*
	 * This method save the account
	 */
	public void save(Account account) {
		System.out.println("Inside of AccountService.save");
		this.validate(account);
		accountDao.save(account);
	}

	/*
	 * Get account by account number
	 */
	public List<Account> getAccount(int id) {
		System.out.println("Inside of AccountService.getAccounts");
		return accountDao.findByID(id);
	}

	/*
	 * send the message after saving the account
	 */
	public void sendMail(Account account) throws EmailException {
		System.out.println("Inside of AccountService.sendMail");
		MailService mailService = new MailService();
		System.out.println("Sending mail...");
		/*
		mailService.sendMail("Account has been created", "sid.neto@yahoo.com.br", "Payment Hub", "sylvio.pedroza@gmail.com",
				"The account " + account.getAccountNumber() + " from " + account.getBank() + " bank has been created successfully",
				"sid.neto@yahoo.com.br", "");
		*/
	}
	

	/*
	 * This method validates account data
	 */
	private void validate(Account account) {
		System.out.println("Inside of AccountService.validate");
		if(account.getBank() == null || account.getBank().isEmpty()) {
			throw new RuntimeException("Bank cannot be null!");
		}
		if(account.getBranch() == null || account.getBranch().isEmpty()) {
			throw new RuntimeException("Branch cannot be null!");
		}
		if(account.getAccountNumber() == null || account.getAccountNumber().isEmpty()) {
			throw new RuntimeException("Account cannot be null!");
		}
		System.out.println("End of AccountService.validate");
	}

}
