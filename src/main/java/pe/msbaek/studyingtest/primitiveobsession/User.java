package pe.msbaek.studyingtest.primitiveobsession;

public class User {
    private final UserName userName = new UserName();

    public String getName() {
        return userName.getName();
    }

    public void setName(String name) {
        userName.setName(name);
    }

    public User(String name) {
        this.userName.setName(name);
    }
}
