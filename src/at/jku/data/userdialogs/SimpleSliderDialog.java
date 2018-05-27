package at.jku.data.userdialogs;

import javax.swing.*;
import javax.swing.event.ChangeListener;
import java.awt.*;

// Returns a simple slider dialog
public class SimpleSliderDialog {
    public static JOptionPane getSlider(Component parent, String message, int min, int max) {
        JOptionPane optionPane = new JOptionPane();
        JSlider slider = buildSlider(optionPane);
        slider.setMinimum(min);
        slider.setMaximum(max);
        optionPane.setMessage(new Object[] { "Select a value: ", slider });
        optionPane.setMessageType(JOptionPane.QUESTION_MESSAGE);
        optionPane.setOptionType(JOptionPane.OK_CANCEL_OPTION);
        JDialog dialog = optionPane.createDialog(parent,message);
        dialog.setVisible(true);
        return optionPane;
    }

    private static JSlider buildSlider(final JOptionPane optionPane) {
        JSlider slider = new JSlider();
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        ChangeListener changeListener = changeEvent -> {
            JSlider theSlider = (JSlider) changeEvent.getSource();
            if (!theSlider.getValueIsAdjusting()) {
                optionPane.setInputValue(new Integer(theSlider.getValue()));
            }
        };
        slider.addChangeListener(changeListener);
        return slider;
    }
}
