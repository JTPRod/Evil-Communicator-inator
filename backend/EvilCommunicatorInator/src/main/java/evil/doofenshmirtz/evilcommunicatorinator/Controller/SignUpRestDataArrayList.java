package evil.doofenshmirtz.evilcommunicatorinator.Controller;

import evil.doofenshmirtz.evilcommunicatorinator.Models.SignUp;

import java.util.ArrayList;

public class SignUpRestDataArrayList {
    private static final ArrayList<SignUp> SIGN_UPS = new ArrayList<>();

    public static String add(SignUp signup) {
        if (SIGN_UPS.stream().filter(i -> i.getUserId() == signup.getUserId()).findFirst().orElse(null) == null) {
            SIGN_UPS.add(signup);
            return "Sign Up Added";
        } else {
            return "Id Conflict";
        }
    }

    public static ArrayList<SignUp> getAll() {
        return SIGN_UPS;
    }

    public static SignUp getById(int id) {
        return SIGN_UPS.stream().filter(signup -> signup.getUserId() == id).findFirst().orElse(null);
    }

    public static SignUp update(SignUp signup) {
        SignUp temp_signup = SIGN_UPS.stream().filter(i -> i.getUserId() == signup.getUserId()).findFirst().orElse(null);
        if (temp_signup != null) {
            SIGN_UPS.remove(temp_signup);
            SIGN_UPS.add(signup);
            return signup;
        }

        return null;
    }

    public static String deleteById(int id) {
        return SIGN_UPS.removeIf(signup -> signup.getUserId() == id) ? "Removed" : "Not Found";
    }
}
