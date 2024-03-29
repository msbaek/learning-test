package pe.msbaek.learningtest.primitiveobsession;

public class UserName {
    String name;

    public UserName() {
    }

    public UserName(String name) {
        validateUserName(name);
        this.name = name;
    }

    private static void validateUserName(String name) {
        if(!isValid(name))
            throw new IllegalArgumentException("Invalid name: [%s]".formatted(name));
    }

    private static boolean isValid(String name) {
        if(name == null || name.isBlank())
            return false;
        return !name.contains(" ");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}