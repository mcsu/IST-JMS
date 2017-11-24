package receiver;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

public class Listener implements javax.jms.MessageListener{//监听器

	public void onMessage(Message message) {
		 TextMessage textMsg = (TextMessage) message;  
	        System.out.print("监听-");  
	        try {  
	            System.out.println("消息内容是：" + textMsg.getText());  
	        } catch (JMSException e) {  
	            e.printStackTrace();  
	        }  
	}
}
