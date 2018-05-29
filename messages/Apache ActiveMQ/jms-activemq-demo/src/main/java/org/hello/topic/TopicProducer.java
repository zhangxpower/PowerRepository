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
		 * 1.�������ӹ���<br>
		 * ���캯���ж�����أ�Ĭ�����ӱ���MQ��������Ҳ�����ֶ������û��������롢���ӵ�ַ��Ϣ<br>
		 * new ActiveMQConnectionFactory(userName, password, brokerURL)
		 */
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(BROKER_URL);
		/**
		 * 2.��������
		 */
		Connection connection = connectionFactory.createConnection();
		/**
		 * 3.��������
		 */
		connection.start();
		/**
		 * 4.�����Ự<br>
		 * param1:�Ƿ�֧��������Ϊtrue�������Եڶ���������Ĭ��ΪSESSION_TRANSACTED<br>
		 * param2:ȷ����Ϣģʽ������һ������Ϊfalseʱ���ò��������¼���״̬<br>
		 * -Session.AUTO_ACKNOWLEDGE���Զ�ȷ�ϡ��ͻ��˷��ͺͽ�����Ϣ����Ҫ������Ĺ�������ʹ���ն˷����쳣��
		 * Ҳ�ᱻ�����������ͳɹ� <br>
		 * -Session.CLIENT_ACKNOWLEDGE���ͻ���ȷ�ϡ��ͻ��˽��յ���Ϣ�󣬱������message.
		 * acknowledge() ���������յ�������JMS�������Ż�Ѹ���Ϣ�������ͳɹ�����ɾ��<br>
		 * -Session.DUPS_OK_ACKNOWLEDGE������ȷ�ϡ�һ�����ն�Ӧ�ó���ķ������ôӴ�����Ϣ�����أ�
		 * �Ự����ͻ�ȷ����Ϣ�Ľ��գ����������ظ�ȷ�ϡ�
		 */
		Session session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
		/**
		 * 5.���������ͣ���ϢĿ�ĵأ���Topic������ΪTopic����
		 */
		Topic destination = session.createTopic(TOPIC_NAME);
		/**
		 * 6.����һ����Ϣ�����ߣ���ָ��Ŀ�ĵ�
		 */
		MessageProducer messageProducer = session.createProducer(destination);
		/**
		 * ���������� ���������ߵ�����ģʽ��Ĭ��Ϊ�־û�<br>
		 * ��������������״̬��<br>
		 * -DeliveryMode.NON_PERSISTENT����Ϣ���־û�����Ϣ������֮����߳�ʱ֮�󽫴Ӷ�����ɾ��
		 * -DeliveryMode.PERSISTENT����Ϣ��־û�����ʹ���ն�������Ϣ֮����Ȼ�ᱣ��
		 */
		messageProducer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
		/**
         * ����������������Ϣ�Ĵ��ʱ�䣨��λ�����룩
         */
		messageProducer.setTimeToLive(60000);
		
		/**
		 * �����ı���Ϣ
		 */
		for (int i = 0; i < 5; i++) {
			TextMessage message = session.createTextMessage("Send content:" + i);
			messageProducer.send(message);
		}
		
		System.out.println("��Ϣ�������!");
		
		session.commit();
		
		messageProducer.close();
		
		/**
         * 10.�ر����ӣ�����رճ���
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
