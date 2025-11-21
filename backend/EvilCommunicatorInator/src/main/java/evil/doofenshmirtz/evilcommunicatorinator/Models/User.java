package evil.doofenshmirtz.evilcommunicatorinator.Models;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.types.ObjectId;

import java.io.Serializable;

public class User implements Serializable {
    @BsonId
    @JsonProperty("user_id")
    private ObjectId user_id;
    private String username;
    private String password;
    private String bio;

    public User() {}

    public User(String username, String password, ObjectId user_id, String bio) {
        this.username = username;
        this.password = password;
        this.user_id = user_id;
        this.bio = bio;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ObjectId getUser_id() {
        return user_id;
    }

    public void setUser_id(ObjectId user_id) {
        this.user_id = user_id;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }
}
