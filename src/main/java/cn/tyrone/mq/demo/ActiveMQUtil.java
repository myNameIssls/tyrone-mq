package cn.tyrone.mq.demo;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

public class ActiveMQUtil {
	
	private static final String BROKER_URL = "tcp://localhost:61616";
	
	private static final String QUEUE_NAME = "tyrone";
	
	public void sendMessage() throws JMSException{
		
		
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(BROKER_URL);	
		
		System.out.println(ActiveMQConnection.DEFAULT_PASSWORD +"\t"+ ActiveMQConnection.DEFAULT_BROKER_URL + "\t" + ActiveMQConnection.DEFAULT_USER);
		
//		ConnectionFactory cf = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_USER, 
//				ActiveMQConnection.DEFAULT_PASSWORD, ActiveMQConnection.DEFAULT_BROKER_URL);
		
		
		Connection connection = connectionFactory.createConnection();
		
		connection.start();
		
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		
		Destination destination = session.createQueue(QUEUE_NAME);
		
		MessageProducer messageProducer = session.createProducer(destination);
		
		messageProducer.setDeliveryMode(DeliveryMode.PERSISTENT);
		
		Message message = session.createTextMessage("Hello ActiveMQ!! Sending the first message successfully!!!");
		
		messageProducer.send(message);
		
	}
	
	public Message receiveMessage() throws JMSException{
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(BROKER_URL);	
		
		Connection connection = connectionFactory.createConnection();
		
		connection.start();
		
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		
		Destination destination = session.createQueue(QUEUE_NAME);
		
		MessageConsumer messageConsumer = session.createConsumer(destination);
		
		Message message = messageConsumer.receive();
		
		return message;
		
	}
	
}
