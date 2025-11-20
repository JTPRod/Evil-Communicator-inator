package evil.doofenshmirtz.evilcommunicatorinator.Controller;

import evil.doofenshmirtz.evilcommunicatorinator.Models.User;

import java.util.ArrayList;

public class UserRestDataArrayList {
    private static final ArrayList<User> users = new ArrayList<>();

    public static String add(User user) {
        if (users.stream().filter(i -> i.getUserId() == user.getUserId()).findFirst().orElse(null) == null) {
            users.add(user);
            return "User Added";
        } else {
            return "Id Conflict";
        }
    }

    public static ArrayList<User> getAll() {
        return users;
    }

    public static User getById(int id) {
        return users.stream().filter(user -> user.getUserId() == id).findFirst().orElse(null);
    }

    public static User update(User user) {
        User temp_user = users.stream().filter(i -> i.getUserId() == user.getUserId()).findFirst().orElse(null);
        if (temp_user != null) {
            users.remove(temp_user);
            users.add(user);
            return user;
        }

        return null;
    }

    public static String deleteById(int id) {
        return users.removeIf(user -> user.getUserId() == id) ? "Removed" : "Not Found";
    }
}
