package br.com.spedroza.PaymentHub.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.spedroza.PaymentHub.model.Account;
import br.com.spedroza.PaymentHub.service.AccountService;

@Controller
public class AccountController {

	@Autowired
	private AccountService accountService;

	@RequestMapping("accounts")
	public ModelAndView getAccounts() {
		System.out.println("Begin of AccountController.getAccounts");
		ModelAndView mView = new ModelAndView("accountlist");
		Iterable<Account> accounts = accountService.getAll();
		mView.addObject("accounts", accounts);
		System.out.println("End of AccountController.getAccounts");
		return mView;
	}

	@RequestMapping(value = "accountSave", method = RequestMethod.POST)
	public ModelAndView save(Account account, RedirectAttributes redirectAttributes) {
		System.out.println("Begin of AccountController.save");
		StringBuilder success = new StringBuilder();
		StringBuilder fail = new StringBuilder();

		// save the account and message the customer
		try {
			accountService.save(account);
			success.append("Account has been saved successfully!");
			
			accountService.sendMail(account);
			success.append("Mail has been sent to the customer!");
		} catch (Exception e) {
			fail.append("Failed: " + e.getMessage());
			e.printStackTrace();
		}
		
		// set return messages
		redirectAttributes.addFlashAttribute("success", success);
		redirectAttributes.addFlashAttribute("fail", fail);
		System.out.println("End of AccountController.save");
		return new ModelAndView("redirect:/accounts");
	}

	public List<Account> getAccount(int id) {
		return accountService.getAccount(id);
	}
}
