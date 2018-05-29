package org.hello.topic;


import java.util.Date;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

public class TopicConsumer<T extends IProperty>{
	
	private T consumerProperty;
	
	private static final String BROKER_URL = ActiveMQConnection.DEFAULT_BROKER_URL;
	
	public TopicConsumer(T consumerProperty) {
		this.setConsumerProperty(consumerProperty);
	}

	public static void main(String[] args) {
		try {
			Property consumer1 = new Property("consumer1", new Date());
			new TopicConsumer<Property>(consumer1).doReceive();
			
			Property consumer2 = new Property("consumer2", new Date());
			new TopicConsumer<Property>(consumer2).doReceive();
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
	
	public void doReceive() throws JMSException {
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(BROKER_URL);
		Connection connection = connectionFactory.createConnection();
		connection.start();
		Session session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
		Topic destination = session.createTopic(TopicProducer.TOPIC_NAME);
		MessageConsumer messageConsumer = session.createConsumer(destination);
		messageConsumer.setMessageListener(message -> {
			if(message instanceof TextMessage) {
				TextMessage textMessage = (TextMessage) message;
				try {
					String consumer = getConsumerProperty().getName() + " " + this.consumerProperty.getCreateTime();
					System.out.println(consumer + " | Received:" + textMessage.getText());
					textMessage.acknowledge();
				} catch (JMSException e) {
					e.printStackTrace();
				}
			}
		});
	}

	public T getConsumerProperty() {
		return consumerProperty;
	}

	public void setConsumerProperty(T consumerProperty) {
		this.consumerProperty = consumerProperty;
	}
}
