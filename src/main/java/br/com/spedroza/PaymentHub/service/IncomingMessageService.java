package br.com.spedroza.PaymentHub.service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.spedroza.PaymentHub.dao.IncomingMessageDAO;
import br.com.spedroza.PaymentHub.model.IncomingMessage;

@Service
public class IncomingMessageService {

	@Autowired
	private IncomingMessageDAO incomingMessageDAO;
	
	// save incoming message
	public void save(IncomingMessage incMsg) {
		System.out.println("Inside IncomingMessageService.save");
		this.validate(incMsg);
		System.out.println(incMsg.toString());
		if(incomingMessageDAO == null) {
			System.out.println("incomingMessageDAO is null");
		}else {
			incomingMessageDAO.save(incMsg);
		}
		
		System.out.println("End IncomingMessageService.save");
	}
	
	public void validate(IncomingMessage incMsg) {
		System.out.println("Inside IncomingMessageService.validate");
		if(incMsg.getAmount() == null || incMsg.getAmount().equals(BigDecimal.ZERO)) {
			throw new RuntimeException("Amount can not be zero!");
		}
		
		if(incMsg.getFromBank() == null || incMsg.getFromBank().isEmpty()) {
			throw new RuntimeException("FromBank can not be zero!");
		}
		if(incMsg.getFromBranch() == null || incMsg.getFromBranch().isEmpty()) {
			throw new RuntimeException("FromBranch can not be zero!");
		}
		if(incMsg.getFromAccountNumber() == null || incMsg.getFromAccountNumber().isEmpty()) {
			throw new RuntimeException("FromAccount can not be zero!");
		}
		
		if(incMsg.getToBank() == null || incMsg.getToBank().isEmpty()) {
			throw new RuntimeException("ToBank can not be zero!");
		}
		if(incMsg.getToBranch() == null || incMsg.getToBranch().isEmpty()) {
			throw new RuntimeException("ToBranch can not be zero!");
		}
		if(incMsg.getToAccountNumber() == null || incMsg.getToAccountNumber().isEmpty()) {
			throw new RuntimeException("ToAccount can not be zero!");
		}
		System.out.println("End of IncomingMessageService.validate");
	}
}
