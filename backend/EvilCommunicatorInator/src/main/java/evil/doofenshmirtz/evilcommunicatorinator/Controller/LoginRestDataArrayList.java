package evil.doofenshmirtz.evilcommunicatorinator.Controller;

import evil.doofenshmirtz.evilcommunicatorinator.Models.Login;

import java.util.ArrayList;

public class LoginRestDataArrayList {
    private static final ArrayList<Login> LOGINS = new ArrayList<>();

    public static String add(Login login) {
        if (LOGINS.stream().filter(i -> i.getUserId() == login.getUserId()).findFirst().orElse(null) == null) {
            LOGINS.add(login);
            return "Login Added";
        } else {
            return "Id Conflict";
        }
    }

    public static ArrayList<Login> getAll() {
        return LOGINS;
    }

    public static Login getById(int id) {
        return LOGINS.stream().filter(login -> login.getUserId() == id).findFirst().orElse(null);
    }

    public static Login update(Login login) {
        Login temp_login = LOGINS.stream().filter(i -> i.getUserId() == login.getUserId()).findFirst().orElse(null);
        if (temp_login != null) {
            LOGINS.remove(temp_login);
            LOGINS.add(login);
            return login;
        }

        return null;
    }

    public static String deleteById(int id) {
        return LOGINS.removeIf(login -> login.getUserId() == id) ? "Removed" : "Not Found";
    }
}
