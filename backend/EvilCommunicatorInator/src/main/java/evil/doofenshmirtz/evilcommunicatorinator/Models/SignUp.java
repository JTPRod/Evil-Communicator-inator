package evil.doofenshmirtz.evilcommunicatorinator.Models;

public class SignUp {
    private String username;
    private String password;
    private int userId;
    private String bio;

    public SignUp() {}

    public SignUp(String username, String password, int userId, String bio) {
        this.username = username;
        this.password = password;
        this.userId = userId;
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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }
}
