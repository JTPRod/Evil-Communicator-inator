package evil.doofenshmirtz.evilcommunicatorinator.Controller;

import evil.doofenshmirtz.evilcommunicatorinator.Models.User;
import org.bson.types.ObjectId;

import java.util.ArrayList;

public class UserRestDataArrayList {
    private static final ArrayList<User> users = new ArrayList<>();

    public static String add(User user) {
        if (users.stream().filter(i -> i.getUser_id() == user.getUser_id()).findFirst().orElse(null) == null) {
            users.add(user);
            return "User Added";
        } else {
            return "Id Conflict";
        }
    }

    public static ArrayList<User> getAll() {
        return users;
    }

    public static User getById(ObjectId id) {
        return users.stream().filter(user -> user.getUser_id() == id).findFirst().orElse(null);
    }

    public static User update(User user) {
        User temp_user = users.stream().filter(i -> i.getUser_id() == user.getUser_id()).findFirst().orElse(null);
        if (temp_user != null) {
            users.remove(temp_user);
            users.add(user);
            return user;
        }

        return null;
    }

    public static String deleteById(ObjectId id) {
        return users.removeIf(user -> user.getUser_id() == id) ? "Removed" : "Not Found";
    }
}
