package at.jku.commands;

import at.jku.data.DataManager;
import at.jku.misc.ImageHelper;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class ScaleImageCommand implements Command {


    @Override
    public void execute(DataManager dm) {

        if (dm.baseImage == null) {
            JOptionPane.showMessageDialog(dm.frame, "SELECT AN IMAGE FIRST", "ERROR", JOptionPane.ERROR_MESSAGE);
            return;
        }


        String scalingFactor = JOptionPane.showInputDialog(dm.frame, "Image scale factor : ");
        try {
            double scaling = Double.parseDouble(scalingFactor);
            dm.frame.remove(dm.baseImageLabel);
            dm.baseImage = ImageHelper.scaleImage(dm.baseImage, (int)(dm.baseImage.getWidth() * scaling), (int) (dm.baseImage.getHeight() * scaling), Image.SCALE_SMOOTH);
            dm.baseImageLabel = new JLabel(new ImageIcon(dm.baseImage));
            dm.frame.add(dm.baseImageLabel);
            dm.frame.pack();
            dm.frame.validate();
            dm.frame.repaint();
        } catch (ClassCastException e) {
            JOptionPane.showMessageDialog(dm.frame, "ONLY NUMBERS ALLOWED", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return;
    }

    @Override
    public String toString() {
        return "Scale Image";
    }
}
