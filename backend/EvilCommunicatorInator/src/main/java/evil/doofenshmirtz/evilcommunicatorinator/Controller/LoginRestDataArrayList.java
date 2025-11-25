package evil.doofenshmirtz.evilcommunicatorinator.Controller;

import evil.doofenshmirtz.evilcommunicatorinator.Models.User;
import org.bson.types.ObjectId;

import java.util.ArrayList;

public class LoginRestDataArrayList {
    private static final ArrayList<User> LOGINS = new ArrayList<>();

    public static String add(User login) {
        if (LOGINS.stream().filter(i -> i.getUser_id().equals(login.getUser_id())).findFirst().orElse(null) == null) {
            LOGINS.add(login);
            return "Login Added";
        } else {
            return "Id Conflict";
        }
    }

    public static ArrayList<User> getAll() {
        return LOGINS;
    }

    public static User getById(ObjectId id) {
        return LOGINS.stream().filter(login -> login.getUser_id().equals(id)).findFirst().orElse(null);
    }

    public static User update(User login) {
        User temp_login = LOGINS.stream().filter(i -> i.getUser_id().equals(login.getUser_id())).findFirst().orElse(null);
        if (temp_login != null) {
            LOGINS.remove(temp_login);
            LOGINS.add(login);
            return login;
        }

        return null;
    }

    public static String deleteById(ObjectId id) {
        return LOGINS.removeIf(login -> login.getUser_id().equals(id)) ? "Removed" : "Not Found";
    }
}
