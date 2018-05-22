package at.jku.misc;

public class PropertyType {
    private String[] properties;
    private UserInput userInput;

    public PropertyType(String[] properties, UserInput userInput) {
        this.properties = properties;
        this.userInput = userInput;
    }

    public String[] getProperties() {
        return properties;
    }

    public UserInput getUserInput() {
        return userInput;
    }
}
