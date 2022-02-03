package contentsite;

import java.util.Objects;

public class User {

    private String userName;
    private int password;
    private boolean isLogIn;
    private boolean isPremiumMember;

    public User(String userName, String password) {
        this.userName = userName;
        this.password = (userName + password).hashCode();
    }

    public String getUserName() {
        return userName;
    }

    public int getPassword() {
        return password;
    }

    public boolean isLogIn() {
        return isLogIn;
    }

    public boolean isPremiumMember() {
        return isPremiumMember;
    }

    public void setLogIn(boolean logIn) {
        isLogIn = logIn;
    }

    public void upgradeForPremium() {
        isPremiumMember = true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return userName.equals(user.userName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userName);
    }
}
