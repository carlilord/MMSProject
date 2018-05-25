package at.jku.data;

import at.jku.data.userdialogs.SimpleSliderDialog;
import at.jku.misc.UserInput;

import javax.swing.*;
import java.awt.*;

public class UserDialogFactory {
    public static String getValueForUserInputType(Component parent, String message, UserInput userInput, int min, int max) {
        String value;
        switch (userInput) {
            case NONE:
                return "";
            case SIMPLE_NUMBER:
                value = JOptionPane.showInputDialog(parent, message, 0);
                return value;
            case SLIDER:
                value = SimpleSliderDialog.getSlider(parent, message, min, max).getInputValue().toString();
                return value;

        }

        return "";
    }
}
