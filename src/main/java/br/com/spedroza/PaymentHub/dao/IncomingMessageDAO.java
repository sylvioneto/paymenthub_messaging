package br.com.spedroza.PaymentHub.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.spedroza.PaymentHub.model.IncomingMessage;

@Repository
@Transactional
public class IncomingMessageDAO {

	@PersistenceContext
	private EntityManager manager;
	
	// save incoming message
	public void save(IncomingMessage incMsg) {
		System.out.println("Inside IncomingMessageDAO.save");
		manager.persist(incMsg);
		System.out.println("End of IncomingMessageDAO.save");
	}
}
