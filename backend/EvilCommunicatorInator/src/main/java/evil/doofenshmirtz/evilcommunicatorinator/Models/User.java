package evil.doofenshmirtz.evilcommunicatorinator.Models;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.types.ObjectId;
import org.mindrot.jbcrypt.BCrypt;

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

    public String checkPasswordStrength(String password) {
        int upperChars = 0;
        int lowerChars = 0;
        int specialChars = 0;
        int digits = 0;
        int length = password.length();

        StringBuilder result = new StringBuilder();

        //min length
        if (length < 8) {
            return "Password must be at least 8 characters long.";
        }

        //character types
        for (int i = 0; i < length; i++) {
            char ch = password.charAt(i);
            if (Character.isUpperCase(ch)) upperChars++;
            else if (Character.isLowerCase(ch)) lowerChars++;
            else if (Character.isDigit(ch)) digits++;
            else specialChars++;
        }

        //strength
        if (upperChars > 0 && lowerChars > 0 && digits > 0 && specialChars > 0) {
            return "Password strength: Strong";
        }
        else if ((upperChars > 0 || lowerChars > 0) && digits > 0 && length >= 10) {
            return "Password strength: Medium";
        }
        else {
            result.append("Password strength: Weak\n");
            if (upperChars == 0) result.append("  - Missing uppercase character.\n");
            if (lowerChars == 0) result.append("  - Missing lowercase character.\n");
            if (digits == 0) result.append("  - Missing digit.\n");
            if (specialChars == 0) result.append("  - Missing special character. (!, @, #, $, %, ^, &, *, (, ), -, _, +, =, [, ], {, }, |, ;, :, ', \", ,, ., /, ?, ~)\n");
        }

        return result.toString();
    }
}
