package evil.doofenshmirtz.evilcommunicatorinator.Controller;

public class Settings {
    public static enum DBStatus {
        ARRAYLIST,
        JSON,
        SQL,
        JPA
    }

    public static DBStatus dbStatus = DBStatus.ARRAYLIST;
    public static String dbURL = "";
    public static String username = "root";
    public static String password = "abc123";

}
