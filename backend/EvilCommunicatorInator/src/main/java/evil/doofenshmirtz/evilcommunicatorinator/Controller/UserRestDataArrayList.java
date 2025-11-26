package evil.doofenshmirtz.evilcommunicatorinator.Controller;

import evil.doofenshmirtz.evilcommunicatorinator.Models.User;
import org.bson.types.ObjectId;

import java.util.ArrayList;

public class UserRestDataArrayList {
    private static final ArrayList<User> users = new ArrayList<>();

    public static String add(User signup) {
        if (users.stream().filter(i -> i.getUser_id().equals(signup.getUser_id())).findFirst().orElse(null) == null) {
            users.add(signup);
            return "Sign Up Added";
        } else {
            return "Id Conflict";
        }
    }

    // Returns Authenticated successfully instead of ObjectId for testing purposes, DO NOT USE THIS IN PRODUCTION
    public static String authenticate(User user) {
        User temp = users.stream().filter(i -> i.getUser_id().equals(user.getUser_id())).findFirst().orElse(null);
        if (temp != null) {
            if (temp.getPassword().equals(user.getPassword())) {
                return "Authenticated successfully";
            }
        }
        return "Bad Credentials";
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
