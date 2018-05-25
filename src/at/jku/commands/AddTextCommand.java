/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.jku.commands;

import at.jku.Main;
import at.jku.data.DataManager;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JColorChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author Kern
 */
public class AddTextCommand implements Command{

    @Override
    public void execute(DataManager dm) {
        
        if (dm.baseImage == null) {
            JOptionPane.showMessageDialog(dm.frame, "SELECT AN IMAGE FIRST", "ERROR", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        String text = JOptionPane.showInputDialog(dm.frame, "Text you want to add: ");
        String xPosition = JOptionPane.showInputDialog(dm.frame, "Position x coordinate for text: " + "(Image width is " + dm.baseImage.getWidth());
        String yPosition = JOptionPane.showInputDialog(dm.frame, "Position y coordinate for text: " + "(Image height is " + dm.baseImage.getHeight());
        String size = JOptionPane.showInputDialog(dm.frame, "Text size: ");
        
        try{
            int xPos = Integer.parseInt(xPosition);
            int yPos = Integer.parseInt(yPosition);
            int textSize = Integer.parseInt(size);
            
            Graphics g = dm.baseImage.getGraphics();
            Color color = JColorChooser.showDialog(null, "Choose color", null);
            
            //g.drawImage(dm.baseImage, 0, 0, null);
            g.setColor(color);
            g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, textSize));
            g.drawString(text, xPos, yPos);
            dm.baseImageLabel = new JLabel(new ImageIcon(dm.baseImage));
//            dm.frame.remove(dm.baseImageLabel);
//            dm.frame.add(dm.baseImageLabel);
            dm.frame.pack();
            dm.frame.validate();
            dm.frame.repaint();
            
        } catch (ClassCastException e) {
            JOptionPane.showMessageDialog(dm.frame, "ONLY NUMBERS ALLOWED", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    @Override
    public String toString() {
        return "Add Text";
    }
}
