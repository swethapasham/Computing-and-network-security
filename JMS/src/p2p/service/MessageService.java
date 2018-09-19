package p2p.service;

import java.util.List;
import java.util.Map;

import p2p.model.MessageDto;

public interface MessageService {
	public void sendMessage(MessageDto messageDto);

	public MessageDto receiveMessage(String currentUser);

	public Map<String, List<MessageDto>> getAllMessages();

	void putMessage(MessageDto messageDto);

}
