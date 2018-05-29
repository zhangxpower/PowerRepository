package org.hello.topic;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

public class TopicProducer {

	public final static String TOPIC_NAME = "activemq-queue";
	private static final String BROKER_URL = ActiveMQConnection.DEFAULT_BROKER_URL;

	public void doSend() throws JMSException {
		/**
		 * 1.创建连接工厂<br>
		 * 构造函数有多个重载，默认连接本地MQ服务器，也可以手动设置用户名、密码、连接地址信息<br>
		 * new ActiveMQConnectionFactory(userName, password, brokerURL)
		 */
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(BROKER_URL);
		/**
		 * 2.创建连接
		 */
		Connection connection = connectionFactory.createConnection();
		/**
		 * 3.启动连接
		 */
		connection.start();
		/**
		 * 4.创建会话<br>
		 * param1:是否支持事务，若为true，则会忽略第二个参数，默认为SESSION_TRANSACTED<br>
		 * param2:确认消息模式，若第一个参数为false时，该参数有以下几种状态<br>
		 * -Session.AUTO_ACKNOWLEDGE：自动确认。客户端发送和接收消息不需要做额外的工作，即使接收端发生异常，
		 * 也会被当作正常发送成功 <br>
		 * -Session.CLIENT_ACKNOWLEDGE：客户端确认。客户端接收到消息后，必须调用message.
		 * acknowledge() 方法给予收到反馈，JMS服务器才会把该消息当做发送成功，并删除<br>
		 * -Session.DUPS_OK_ACKNOWLEDGE：副本确认。一旦接收端应用程序的方法调用从处理消息处返回，
		 * 会话对象就会确认消息的接收，而且允许重复确认。
		 */
		Session session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
		/**
		 * 5.创建（发送）消息目的地，即Topic，参数为Topic名称
		 */
		Topic destination = session.createTopic(TOPIC_NAME);
		/**
		 * 6.创建一个消息生产者，并指定目的地
		 */
		MessageProducer messageProducer = session.createProducer(destination);
		/**
		 * 其他操作： 设置生产者的生产模式，默认为持久化<br>
		 * 参数有以下两种状态：<br>
		 * -DeliveryMode.NON_PERSISTENT：消息不持久化，消息被消费之后或者超时之后将从队列中删除
		 * -DeliveryMode.PERSISTENT：消息会持久化，即使接收端消费消息之后仍然会保存
		 */
		messageProducer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
		/**
         * 其他操作：设置消息的存活时间（单位：毫秒）
         */
		messageProducer.setTimeToLive(60000);
		
		/**
		 * 发送文本消息
		 */
		for (int i = 0; i < 5; i++) {
			TextMessage message = session.createTextMessage("Send content:" + i);
			messageProducer.send(message);
		}
		
		System.out.println("消息发送完成!");
		
		session.commit();
		
		messageProducer.close();
		
		/**
         * 10.关闭连接（将会关闭程序）
         */
		if(connection != null) {
			connection.close();
		}
	}
	
	public static void main(String[] args) {
		try {
			new TopicProducer().doSend();
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
}
