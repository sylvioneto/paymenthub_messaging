package br.com.spedroza.PaymentHub.queue;

import java.math.BigDecimal;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

import br.com.spedroza.PaymentHub.model.IncomingMessage;
import br.com.spedroza.PaymentHub.service.IncomingMessageService;

@Component
public class IncomingConsumer {

	private final static String QUEUE_NAME = "phub.incoming";
	
	@Autowired
	private IncomingMessageService incomingMessageService;

	public void consume() throws Exception {

		// create a connection to the server
		ConnectionFactory factory = new ConnectionFactory();
	    factory.setHost("192.168.99.100");
	    Connection connection = factory.newConnection();
	    Channel channel = connection.createChannel();

		// queue declare
	    channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		
	    //deliver call back
	    DeliverCallback deliverCallback = (consumerTag, delivery) -> {
	        String message = new String(delivery.getBody(), "UTF-8");
	        System.out.println(" [x] Received '" + message + "'");
	        
	        try {
	        	// parse message to json
		        JSONObject obj = new JSONObject(message);
		        String fromBank = obj.getJSONObject("from").getString("bank");
		        String fromBranch = obj.getJSONObject("from").getString("branch");
		        String fromAccount = obj.getJSONObject("from").getString("account");
		        String toBank = obj.getJSONObject("to").getString("bank");
		        String toBranch = obj.getJSONObject("to").getString("branch");
		        String toAccount = obj.getJSONObject("to").getString("account");
		        BigDecimal amount = obj.getBigDecimal("amount");
		        IncomingMessage incMsg = new IncomingMessage(fromBank, fromBranch, fromAccount,
		        											toBank, toBranch, toAccount, amount);
		        incomingMessageService.save(incMsg);	
	        }catch (Exception e) {
	        	System.out.println(e.getMessage());
				e.printStackTrace();
			}
	        
	    };
	    
	    channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> { });
	}

}
