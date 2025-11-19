package evil.doofenshmirtz.evilcommunicatorinator.Models;

public class Message {
    private String content;
    private int userId;
    private int messageId;
    private int timestamp;

    public Message() {}

    public Message(String content, int userId, int messageId, int timestamp) {
        this.content = content;
        this.userId = userId;
        this.messageId = messageId;
        this.timestamp = timestamp;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }

    public int getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(int timestamp) {
        this.timestamp = timestamp;
    }
}
