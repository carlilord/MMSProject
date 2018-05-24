package at.jku.data;

import at.jku.misc.UserInput;

import javax.swing.*;
import java.awt.*;

public class UserDialogChooser {
    private String[] config;

    private String name;
    private char type;
    private int min;
    private int max;
    private int def;
    private UserInput userInput;

    public UserDialogChooser(String[] config, UserInput userInput) {
        this.config = config;
        min = -1;
        max = -1;
        this.userInput = userInput;
    }

    /** Ask the user for a value */
    public int[] askValue(Component parent) {
        int[] results = new int[config.length];
        for (int i = 0; i < config.length; i++) {
            StringBuffer buffer = new StringBuffer();
            buffer.append("Enter a value for " + name);
            if(min != -1 && max != -1) {
                buffer.append(" (");
                buffer.append(min);
                buffer.append(" - ");
                buffer.append(max);
                buffer.append(")");
            }
            String msg = buffer.toString();

            String val = JOptionPane.showInputDialog(parent, msg, "" + def);

            try {
                int intValue = Integer.parseInt(val);
                if(i < min) {
                    results[i] = min;
                } else if(i > max) {
                    results[i] =  max;
                } else {
                    results[i] = intValue;
                }
            } catch (Exception e) {
                results[i] =  def;
            }
        }

        return results;
    }

    public String getName() {
        return name;
    }

    private void parseConfig(String configuration) {
        String[] options = configuration.split(":");
        name = options[0];
        type = options[1].charAt(0);
        if(options.length > 2) {
            String[] minmax = options[2].split("-");
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
