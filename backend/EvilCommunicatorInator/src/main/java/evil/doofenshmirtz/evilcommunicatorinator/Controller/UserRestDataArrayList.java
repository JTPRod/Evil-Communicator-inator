package evil.doofenshmirtz.evilcommunicatorinator.Controller;

import evil.doofenshmirtz.evilcommunicatorinator.Models.User;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.Map;

public class UserRestDataArrayList {
    private static final ArrayList<User> users = new ArrayList<>();

    public static Map<String, Object> add(User signup) {
        if (users.stream().filter(i -> i.getUser_id().equals(signup.getUser_id())).findFirst().orElse(null) == null) {
            users.add(signup);
            return Map.of(
                    "status", "success",
                    "message", "User successfully added"
            );
        } else {
            return Map.of(
                    "status", "error",
                    "message", "User add failed"
            );
        }
    }

    // Returns Authenticated successfully instead of ObjectId for testing purposes, DO NOT USE THIS IN PRODUCTION
    public static Map<String, Object> authenticate(User user) {
        User temp = users.stream().filter(i -> i.getUser_id().equals(user.getUser_id())).findFirst().orElse(null);
        if (temp != null) {
            if (temp.getPassword().equals(user.getPassword())) {
                return Map.of(
                        "status", "success",
                        "message", "Authenticated successfully"
                );
            }
        }
        return Map.of(
                "status", "error",
                "message", "Bad credentials"
        );
    }

    public static ArrayList<User> getAll() {
        return users;
    }

    public static User getById(ObjectId id) {
        return users.stream().filter(signup -> signup.getUser_id().equals(id)).findFirst().orElse(null);
    }

    public static User update(User signup) {
        User temp_signup = users.stream().filter(i -> i.getUser_id().equals(signup.getUser_id())).findFirst().orElse(null);
        if (temp_signup != null) {
            users.remove(temp_signup);
            users.add(signup);
            return signup;
        }

        return null;
    }

    public static String deleteById(ObjectId id) {
        return users.removeIf(signup -> signup.getUser_id().equals(id)) ? "Removed" : "Not Found";
    }
}
