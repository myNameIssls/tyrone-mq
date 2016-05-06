import javax.jms.JMSException;
import javax.jms.Message;

import org.junit.Test;

import cn.tyrone.mq.demo.ActiveMQUtil;


public class ActiveMQUtilTest {
	
	@Test
	public void sendMessageTest() throws JMSException{
		
		ActiveMQUtil activeMQUtil = new ActiveMQUtil();
		
		activeMQUtil.sendMessage();
		
		System.out.println("成功发送第一条信息");
		
	}
	
	@Test
	public void receiveMessageTest() throws JMSException {
		ActiveMQUtil activeMQUtil = new ActiveMQUtil();
		Message message = activeMQUtil.receiveMessage();
		System.out.println(message);
	}
	
}
