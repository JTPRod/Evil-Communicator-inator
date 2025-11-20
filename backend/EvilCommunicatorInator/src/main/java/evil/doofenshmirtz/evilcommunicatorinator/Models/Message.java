package evil.doofenshmirtz.evilcommunicatorinator.Models;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.types.ObjectId;

import java.io.Serializable;

public class Message implements Serializable {
    @BsonId
    @JsonProperty("message_id")
    private ObjectId message_id;
    private ObjectId user_id;
    private String content;

    public Message() {}

    public Message(String content, ObjectId user_id) {
        this.content = content;
        this.user_id = user_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public ObjectId getUser_id() {
        return user_id;
    }

    public void setUser_id(ObjectId user_id) {
        this.user_id = user_id;
    }

    public ObjectId getMessage_id() {
        return message_id;
    }

    public void setMessage_id(ObjectId message_id) {
        this.message_id = message_id;
    }
}
