package p2p.model;

import java.io.Serializable;

public class MessageDto implements Serializable, Comparable<MessageDto> {

	private static final long serialVersionUID = 1L;
	private int messageID;
	private String message;
	private String fromUser;
	private String toUser;
	private MessageStatus status;

	public int getMessageID() {
		return messageID;
	}

	public void setMessageID(int messageID) {
		this.messageID = messageID;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String productName) {
		this.message = productName;
	}

	public MessageStatus getStatus() {
		return status;
	}

	public void setStatus(MessageStatus status) {
		this.status = status;
	}

	public String getFromUser() {
		return fromUser;
	}

	public void setFromUser(String fromUser) {
		this.fromUser = fromUser;
	}

	public String getToUser() {
		return toUser;
	}

	public void setToUser(String toUser) {
		this.toUser = toUser;
	}

	@Override
	public String toString() {
		return "MessageDto [messageID=" + messageID + ", message=" + message + ", fromUser=" + fromUser + ", toUser="
				+ toUser + ", status=" + status + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fromUser == null) ? 0 : fromUser.hashCode());
		result = prime * result + ((message == null) ? 0 : message.hashCode());
		result = prime * result + messageID;
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((toUser == null) ? 0 : toUser.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MessageDto other = (MessageDto) obj;
		if (fromUser == null) {
			if (other.fromUser != null)
				return false;
		} else if (!fromUser.equals(other.fromUser))
			return false;
		if (message == null) {
			if (other.message != null)
				return false;
		} else if (!message.equals(other.message))
			return false;
		if (messageID != other.messageID)
			return false;
		if (status != other.status)
			return false;
		if (toUser == null) {
			if (other.toUser != null)
				return false;
		} else if (!toUser.equals(other.toUser))
			return false;
		return true;
	}

	@Override
	public int compareTo(MessageDto o) {

		if (this.messageID == o.getMessageID())
			return 0;
		else if (this.messageID > o.getMessageID())
			return 1;
		else
			return -1;
	}

}