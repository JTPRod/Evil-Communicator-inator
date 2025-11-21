package evil.doofenshmirtz.evilcommunicatorinator.Controller;

import evil.doofenshmirtz.evilcommunicatorinator.Models.User;

import java.util.ArrayList;

public class SignUpRestDataArrayList {
    private static final ArrayList<User> SIGN_UPS = new ArrayList<>();

    public static String add(User signup) {
        if (SIGN_UPS.stream().filter(i -> i.getUser_id() == signup.getUser_id()).findFirst().orElse(null) == null) {
            SIGN_UPS.add(signup);
            return "Sign Up Added";
        } else {
            return "Id Conflict";
        }
    }

    public static ArrayList<User> getAll() {
        return SIGN_UPS;
    }

    public static User getById(int id) {
        return SIGN_UPS.stream().filter(signup -> signup.getUser_id() == id).findFirst().orElse(null);
    }

    public static User update(User signup) {
        User temp_signup = SIGN_UPS.stream().filter(i -> i.getUser_id() == signup.getUser_id()).findFirst().orElse(null);
        if (temp_signup != null) {
            SIGN_UPS.remove(temp_signup);
            SIGN_UPS.add(signup);
            return signup;
        }

        return null;
    }

    public static String deleteById(int id) {
        return SIGN_UPS.removeIf(signup -> signup.getUser_id() == id) ? "Removed" : "Not Found";
    }
}
