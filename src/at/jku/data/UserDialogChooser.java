package at.jku.data;

import at.jku.misc.UserInput;

import java.awt.*;
import java.util.Properties;


public class UserDialogChooser {
    private String[] config;

    private String name;
    private char type;
    private int min;
    private int max;
    private int def;
    private UserInput userInput;
    private Properties properties;

    public UserDialogChooser(String[] config, UserInput userInput) {
        this.config = config;
        min = -1;
        max = -1;
        this.userInput = userInput;
        properties = new Properties();
    }

    /** Ask the user for a value */
    public Properties askValue(Component parent) {
        double value;
        for (int i = 0; i < config.length; i++) {
            parseConfig(config[i]);
            StringBuffer buffer = new StringBuffer();
            buffer.append("Enter a value for " + name);
            if(min != -1 && max != -1) {
                buffer.append(" (");
                buffer.append(min);
                buffer.append(" - ");
                buffer.append(max);
                buffer.append(")");
            }

            String val = UserDialogFactory.getValueForUserInputType(parent, "Enter a value for " + name, userInput, min, max);
            try {
                double doubleValue = Double.parseDouble(val);
                if(i < min) {
                    value = min;
                } else if(i > max) {
                    value =  max;
                } else {
                    value = doubleValue;
                }
            } catch (Exception e) {
                value =  def;
            }

            properties.setProperty(name, String.valueOf(value));
        }

        return properties;
    }

    /**
     * Retrieves the boundries, name and tyoe of a parameter
     * @param configuration the parameter in String format
     */
    private void parseConfig(String configuration) {
        String[] options = configuration.split(":");
        name = options[0];
        type = options[1].charAt(0);
        if(options.length > 2) {
            String[] minmax = options[2].split("#");
            min = Integer.parseInt(minmax[0]);
            max = Integer.parseInt(minmax[1]);
            if(options.length > 3) {
                def = Integer.parseInt(options[3]);
            } else{
                def = min;
            }
        }
    }
}
