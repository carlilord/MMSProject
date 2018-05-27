package at.jku.commands;

import at.jku.data.DataManager;
import at.jku.misc.ImageHelper;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class ScaleImageCommand implements Command {


    @Override
    // asks the user for a scaling factor and scales the base images by this factor
    public BufferedImage execute(DataManager dm) {

        if (dm.baseImage == null) {
            JOptionPane.showMessageDialog(dm.frame, "SELECT AN IMAGE FIRST", "ERROR", JOptionPane.ERROR_MESSAGE);
            return dm.baseImage;
        }


        String scalingFactor = JOptionPane.showInputDialog(dm.frame, "Image scale factor : ");
        BufferedImage scaledImage = dm.baseImage;
        try {
            double scaling = Double.parseDouble(scalingFactor);
            scaledImage = ImageHelper.scaleImage(dm.baseImage, (int) (dm.baseImage.getWidth() * scaling), (int) (dm.baseImage.getHeight() * scaling), Image.SCALE_SMOOTH);
            dm.baseImage = scaledImage;
            dm.frame.remove(dm.baseImageLabel);
            dm.baseImageLabel = new JLabel(new ImageIcon(scaledImage));
            dm.frame.add(dm.baseImageLabel);
            dm.frame.pack();
            dm.frame.validate();
            dm.frame.repaint();
        } catch (ClassCastException | NumberFormatException e) {
            JOptionPane.showMessageDialog(dm.frame, "ONLY DOUBLE NUMBERS ALLOWED", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return scaledImage;
    }

    @Override
    public String toString() {
        return "Scale Image";
    }
}
