package evil.doofenshmirtz.evilcommunicatorinator.Controller;

public class Settings {
    public static enum DBStatus {
        ARRAYLIST,
        JSON,
        MONGO,
        JPA
    }

    public static DBStatus dbStatus = DBStatus.ARRAYLIST;
    public static String dbURL = "mongodb://localhost:27017";
    public static String username = "root";
    public static String password = "abc123";

}
