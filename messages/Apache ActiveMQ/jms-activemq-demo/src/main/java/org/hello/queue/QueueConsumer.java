package org.hello.queue;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

public class QueueConsumer{
	
	private static final String BROKER_URL = ActiveMQConnection.DEFAULT_BROKER_URL;
	
	public static void main(String[] args) {
		try {
			new QueueConsumer().doReceive();
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
	
	public void doReceive() throws JMSException {
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(BROKER_URL);
		Connection connection = connectionFactory.createConnection();
		connection.start();
		Session session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
		Queue destination = session.createQueue(QueueProducer.QUEUE_NAME);
		MessageConsumer messageConsumer = session.createConsumer(destination);
		messageConsumer.setMessageListener(message -> {
			if(message instanceof TextMessage) {
				TextMessage textMessage = (TextMessage) message;
				try {
					System.out.println("Received:" + textMessage.getText());
					textMessage.acknowledge();
				} catch (JMSException e) {
					e.printStackTrace();
				}
			}
		});
	}
	
}
