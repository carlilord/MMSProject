package at.jku.data;

import at.jku.data.userdialogs.SimpleSliderDialog;
import at.jku.misc.UserInput;

import javax.swing.*;
import java.awt.*;

public class UserDialogFactory {
    public static int[] getValuesForUserInputType(Component parent, String message, UserInput userInput) {
        String value;
        switch (userInput) {
            case NONE:
                return new int[0];
            case SIMPLE_NUMBER:
                value = JOptionPane.showInputDialog(parent, message, 0);
                return new int[] { Integer.parseInt(value) };
            case SLIDER:
                value = SimpleSliderDialog.getSlider(parent, message).getInputValue().toString();
                return new int[] { Integer.parseInt(value) };

        }

        return new int[0];
    }
}
