package at.jku.commands;

import at.jku.Main;
import at.jku.misc.ImageHelper;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class ScaleImageCommand implements Command {


    @Override
    public BufferedImage execute(BufferedImage image) {

        if (Main.baseImage == null) {
            JOptionPane.showMessageDialog(Main.frame, "SELECT AN IMAGE FIRST", "ERROR", JOptionPane.ERROR_MESSAGE);
            return image;
        }


        String scalingFactor = JOptionPane.showInputDialog(Main.frame, "Image scale factor : ");
        BufferedImage scaledImage = image;
        try {
            int scaling = Integer.parseInt(scalingFactor);
            scaledImage = ImageHelper.scaleImage(image, image.getWidth() * scaling, image.getHeight() * scaling, Image.SCALE_SMOOTH);
            Main.baseImage = scaledImage;
            Main.baseImageLabel = new JLabel(new ImageIcon(scaledImage));
            Main.frame.remove(Main.baseImageLabel);
            Main.frame.add(Main.baseImageLabel);
            Main.frame.pack();
            Main.frame.validate();
            Main.frame.repaint();
        } catch (ClassCastException e) {
            JOptionPane.showMessageDialog(Main.frame, "ONLY NUMBERS ALLOWED", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return scaledImage;
    }

    @Override
    public String toString() {
        return "Scale Image";
    }
}
