package pe.msbaek.learningtest.primitiveobsession;

public class User {
    private final UserName userName;

    public String getName() {
        return userName.getName();
    }

    public void setName(String name) {
        userName.setName(name);
    }

    public User(String name) {
        this.userName = new UserName(name);
    }
}