package evil.doofenshmirtz.evilcommunicatorinator.Models;

public class User {
    private String username;
    private String password;
    private int user_id;
    private String bio;

    public User() {}

    public User(String username, String password, int user_id, String bio) {
        this.username = username;
        this.password = password;
        this.user_id = user_id;
        this.bio = bio;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }
}
