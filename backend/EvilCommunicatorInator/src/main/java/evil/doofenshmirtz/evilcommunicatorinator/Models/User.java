package evil.doofenshmirtz.evilcommunicatorinator.Models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.types.ObjectId;
import org.mindrot.jbcrypt.BCrypt;

import java.io.Serializable;
import java.util.Map;

public class User implements Serializable {
    @BsonId
    @JsonProperty("user_id")
    @JsonSerialize(using = ToStringSerializer.class)
    private ObjectId user_id;
    private String username;
    private String password;
    private String bio;

    public User() {
    }

    public User(String username, String password, ObjectId user_id, String bio) {
        this.username = username;
        this.password = password;
        this.user_id = user_id;
        this.bio = bio;
    }

    public static String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt(12));
    }

    public static boolean validatePassword(String raw, String hashed) {
        return BCrypt.checkpw(raw, hashed);
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

    public static Map<String, Object> checkUsername(String username) {
        //no null usernames
        if (username == null || username.isEmpty()) {
            return Map.of(
                    "status", "error",
                    "message", "Username cannot be empty."
            );
        }

        int length = username.length();

        //min length
        if (length < 2) {
            return Map.of(
                    "status", "error",
                    "message", "Username has to be at least 2 characters."
            );
        }

        //max length
        if (length > 20) {
            return Map.of(
                    "status", "error",
                    "message", "Username has to be fewer than 20 characters."
            );
        }

        //valid!! yayy!
        return Map.of(
                    "status", "success",
                    "message", "Username valid, checking for duplicates."
            );
    }

    public static Map<String, Object> checkPasswordStrength(String password) {
        //no null passwords
        if (password == null || password.isEmpty()) {
            return Map.of(
                    "status", "error",
                    "message", "Password cannot be empty."
            );
        }

        int length = password.length();

        //min length
        if (length < 8) {
            return Map.of(
                    "status", "error",
                    "message", "Password has to be at least 8 characters."
            );
        }

        int upperChars = 0;
        int lowerChars = 0;
        int specialChars = 0;
        int digits = 0;

        //character types
        for (int i = 0; i < length; i++) {
            char ch = password.charAt(i);
            if (Character.isUpperCase(ch)) upperChars++;
            else if (Character.isLowerCase(ch)) lowerChars++;
            else if (Character.isDigit(ch)) digits++;
            else specialChars++;
        }

        //strength checker
        if (upperChars > 0 && lowerChars > 0 && digits > 0 && specialChars > 0) {
            return Map.of(
                    "status", "success",
                    "message", "Password strength: Strong."
            );
        }
        else if ((upperChars > 0 || lowerChars > 0) && digits > 0 && length >= 10) {
            return Map.of(
                    "status", "success",
                    "message", "Password strength: Medium."
            );
        }
        else {
            StringBuilder missing = new StringBuilder();

            if (upperChars == 0) missing.append("\n- Missing uppercase character.");
            if (lowerChars == 0) missing.append("\n- Missing lowercase character.");
            if (digits == 0) missing.append("\n- Missing digit.");
            if (specialChars == 0) missing.append("\n- Missing special character.");

            return Map.of(
                    "status", "error",
                    "message", "Password strength: Weak." + missing
            );
        }
    }
}