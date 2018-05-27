/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.jku.commands;

import at.jku.data.DataManager;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 *
 * @author Kern
 */
public class AddTextCommand implements Command{

    @Override
    public BufferedImage execute(DataManager dm) {
        
        if (dm.baseImage == null) {
            JOptionPane.showMessageDialog(dm.frame, "SELECT AN IMAGE FIRST", "ERROR", JOptionPane.ERROR_MESSAGE);
            return dm.baseImage;
        }
        
        //User input
        String text = JOptionPane.showInputDialog(dm.frame, "Text you want to add: ");
        String xPosition = JOptionPane.showInputDialog(dm.frame, "Position x coordinate: " + "(Image width is " + dm.baseImage.getWidth());
        String yPosition = JOptionPane.showInputDialog(dm.frame, "Position x coordinate: " + "(Image height is " + dm.baseImage.getHeight());
        String size = JOptionPane.showInputDialog(dm.frame, "Text size: ");
        BufferedImage textImage = dm.baseImage;

        try{
            //Parse String to int
            int xPos = Integer.parseInt(xPosition);
            int yPos = Integer.parseInt(yPosition);
            int textSize = Integer.parseInt(size);

            Graphics g = textImage.getGraphics();
            
            //ColorChooser to choose color
            Color color = JColorChooser.showDialog(null, "Choose color", null);
            
            //Draw Image,set the Color + Font of String and finally draw String
            g.drawImage(textImage, 0, 0, null);
            g.setColor(color);
            g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, textSize));
            g.drawString(text, xPos, yPos);
            dm.frame.remove(dm.baseImageLabel);
            dm.baseImageLabel = new JLabel(new ImageIcon(textImage));
            dm.frame.add(dm.baseImageLabel);
            dm.frame.pack();
            dm.frame.validate();
            dm.frame.repaint();

        } catch (ClassCastException e) {
            JOptionPane.showMessageDialog(dm.frame, "ONLY NUMBERS ALLOWED", "ERROR", JOptionPane.ERROR_MESSAGE);
        }

        return textImage;
    }
    
    @Override
    public String toString() {
        return "Add Text";
    }
}
