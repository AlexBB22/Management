package nl.tudelft.oopp.communication;

public class User {
    private int userId;

    private String email;

    private String userName;

    private String userPassword;

    private Role role;

    /**
     * The constructor of a User.
     * @param userId - the users id
     * @param email - the users email
     * @param userName - the users username
     * @param userPassword - the users passowrd
     * @param role - the role of the user
     */
    public User(int userId, String email, String userName, String userPassword, Role role) {
        this.userId = userId;
        this.email = email;
        this.userName = userName;
        this.userPassword = userPassword;
        this.role = role;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String toString() {
        return "User{" + "userId=" + userId + ", email='" + email + '\'' + ", userName='" + userName
                + '\'' + ", userPassword='" + userPassword + '\'' + ", role=" + role + '}';
    }

}
