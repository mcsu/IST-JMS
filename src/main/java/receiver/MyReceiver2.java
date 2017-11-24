package receiver;

import javax.jms.Connection;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueReceiver;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.jms.Queue;
import javax.jms.QueueConnectionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyReceiver2 {

	public static void main(String[] args) {
		try{
			
			ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContextJMS.xml");
			QueueConnectionFactory factory = (QueueConnectionFactory) applicationContext.getBean("connectionFactory");
			
			// Create a connection. See  https://docs.oracle.com/javaee/7/api/javax/jms/package-summary.html
			Connection connection = factory.createQueueConnection();
			Session session = connection.createSession(true, 1);
			Topic topic = session.createTopic("JavaTopic");
			// Open a session
			// Start the connection
			connection.start();
			// Create a receive	
			//QueueReceiver receiver = session.createReceiver(queue);
			//receiver.setMessageListener(new Listener());
			// Receive the message
			MessageConsumer messageConsumer = session.createConsumer(topic);
			messageConsumer.setMessageListener(new Listener());
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
