package p2p.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import p2p.model.LoginDto;
import p2p.model.MessageDto;
import p2p.service.MessageService;
import p2p.util.Utility;

@Controller
public class MessageController {

	@Autowired
	MessageService messageService;

	@RequestMapping(value = { "/login" })
	public String showLogin(ModelMap model) {
		model.addAttribute("command", new LoginDto());
		return "login";
	}

	@RequestMapping(value = { "/doLogin" })
	public String doLogin(LoginDto loginDto, ModelMap model, HttpServletRequest request) {

		if (Utility.getUsers().contains(loginDto.getUserName())
				&& loginDto.getUserName().equalsIgnoreCase(loginDto.getPassword())) {
			request.getSession().setAttribute("userName", loginDto.getUserName());
			request.getSession().setAttribute("users", Utility.getUsers());

			model.addAttribute("info", loginDto.getUserName() + " Successfully Logged in");
			model.addAttribute("command", new MessageDto());
			return "msgpanel";
		} else {
			model.addAttribute("info", loginDto.getUserName() + " invalid User");
			model.addAttribute("command", new LoginDto());
			return "login";
		}
	}

	@RequestMapping(value = { "/sendMessage" }, method = RequestMethod.POST)
	public String sendMessage(MessageDto messageDto, ModelMap model, HttpServletRequest request) {
		String encryptionPass = null;
		String fromUser = (String) request.getSession().getAttribute("userName");
		messageDto.setFromUser(fromUser);
		System.out.println("Before Encryption : " + messageDto.toString());
		encryptionPass = messageDto.getFromUser().toLowerCase() + "xyz" + messageDto.getToUser().toLowerCase();
		
		System.out.println("Encrypting password : "+encryptionPass);
		messageDto.setMessage(Utility.encryptDES(messageDto.getMessage(), encryptionPass));
		messageService.sendMessage(messageDto);
		System.out.println("After Encryption : " + messageDto.toString());

		model.addAttribute("info", "Message - " + messageDto.getMessage());
		model.addAttribute("command", new MessageDto());
		return "msgpanel";
	}

	@RequestMapping(value = { "/getMessage" }, method = RequestMethod.GET)
	public String checkIncomingMessages(ModelMap model, HttpServletRequest request) {
		String encryptionPass = null;
		String decryptedMsg = null;
		String fromUser = (String) request.getSession().getAttribute("userName");
		MessageDto receivedMsg = messageService.receiveMessage(fromUser);

		if (null != receivedMsg) {
			System.out.println("Before Decryption : " + receivedMsg.toString());
			encryptionPass = receivedMsg.getFromUser().toLowerCase() + "xyz" + receivedMsg.getToUser().toLowerCase();
			System.out.println("Decrypting password : "+encryptionPass);
			decryptedMsg = Utility.decryptDES(receivedMsg.getMessage(), encryptionPass);
			receivedMsg.setMessage(decryptedMsg);
			messageService.putMessage(receivedMsg);
			System.out.println("After Decryption : " + receivedMsg.toString());
		}

		List<MessageDto> messageList = new ArrayList<>();
		Map<String, List<MessageDto>> messageMap = messageService.getAllMessages();
		for (String key : messageMap.keySet()) {
			messageList.addAll(messageMap.get(key));
		}
		Collections.sort(messageList);
		model.addAttribute("messageMapList", messageList);
		return "chathistory";
	}

	@RequestMapping(value = { "/logout" }, method = RequestMethod.GET)
	public String logout(ModelMap model, HttpServletRequest request) {
		request.getSession().invalidate();
		model.addAttribute("command", new LoginDto());
		model.addAttribute("info", "Successfully Logged out");
		return "login";
	}

}