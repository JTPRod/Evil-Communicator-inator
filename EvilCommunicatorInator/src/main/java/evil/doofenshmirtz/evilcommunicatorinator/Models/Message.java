package evil.doofenshmirtz.evilcommunicatorinator.Models;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.types.ObjectId;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import java.io.Serializable;
import java.util.List;
import java.util.regex.Pattern;

public class Message implements Serializable {
    @BsonId
    @JsonProperty("message_id")
    @JsonSerialize(using = ToStringSerializer.class)
    private ObjectId message_id;
    @JsonSerialize(using = ToStringSerializer.class)
    private ObjectId user_id;
    private static String content;

    public Message() {}

    public Message(String content, ObjectId user_id) {
        this.content = content;
        this.user_id = user_id;
    }

    public static String getContent() {
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
