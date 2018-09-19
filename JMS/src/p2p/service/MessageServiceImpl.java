package p2p.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.jms.JMSException;
import javax.jms.Message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessagePostProcessor;
import org.springframework.stereotype.Service;

import p2p.model.MessageDto;
import p2p.model.MessageStatus;

@Service("messageService")
public class MessageServiceImpl implements MessageService {
	public static final Map<String, List<MessageDto>> messageMap = new ConcurrentHashMap<String, List<MessageDto>>();
	public static int messageID = 0;

	@Autowired
	JmsTemplate jmsTemplate;

	@Override
	public void sendMessage(MessageDto messageDto) {
		messageDto.setMessageID(messageID++);
		messageDto.setStatus(MessageStatus.CREATED);

		String correlId = messageDto.getToUser();
		jmsTemplate.convertAndSend(messageDto, new MessagePostProcessor() {

			@Override
			public Message postProcessMessage(Message message) throws JMSException {
				message.setJMSCorrelationIDAsBytes(correlId.getBytes());
				return message;
			}
		});
	}

	@Override
	public MessageDto receiveMessage(String currentUser) {
		String selector = "JMSCorrelationID='" + currentUser + "'";
		MessageDto messageDto = (MessageDto) jmsTemplate.receiveSelectedAndConvert(selector);
		if (null != messageDto) {
			messageDto.setStatus(MessageStatus.CONFIRMED);
		}
		System.out.println("MessageServiceImpl.getMessage()::" + messageDto);
		return messageDto;
	}
	
	@Override
	public void putMessage(MessageDto messageDto) {

		String key = messageDto.getFromUser() + "-" + messageDto.getToUser();

		List<MessageDto> msgList = null;
		if (null == messageMap.get(key)) {
			msgList = new ArrayList<>();
		} else {
			msgList = messageMap.get(key);
		}
		msgList.add(messageDto);
		messageMap.put(key, msgList);
	}

	

	@Override
	public Map<String, List<MessageDto>> getAllMessages() {
		return messageMap;
	}
}
