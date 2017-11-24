package sender;

import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.QueueConnectionFactory;
import javax.jms.Session;
import javax.jms.Topic;

import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TopicSender {

	public static void main(String[] args) {// Topic Sender
		Connection connection = null;
		try{
			
			ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContextJMS.xml");
			QueueConnectionFactory factory = (QueueConnectionFactory) applicationContext.getBean("connectionFactory");
			// Create a connection. See  https://docs.oracle.com/javaee/7/api/javax/jms/package-summary.html
			 connection = factory.createQueueConnection();
			Session session = connection.createSession(true, 1);
			Topic topic = session.createTopic("JavaTopic");
			// Open a session
			// Start the connection
			connection.start();
			// Create a sender
			MessageProducer producer = session.createProducer(topic);
			producer.setTimeToLive(100000);
			// Create a message
			for (int i = 0; i < 10; i++) {
				Message message = session.createTextMessage("只要莫妮卡"+i);
				producer.send(message);
			}
			
			// Send the message
			
	        session.commit();
			//session.commit();
			// Close the session
			session.close();
			// Close the connection
			connection.close();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		finally {
			try {
				connection.close();
			} catch (JMSException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
