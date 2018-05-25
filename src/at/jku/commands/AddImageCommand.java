/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.jku.commands;

import at.jku.data.DataManager;
import at.jku.misc.ImageHelper;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author Kern
 */
public class AddImageCommand implements Command{

    @Override
    public BufferedImage execute(DataManager dm) {
        
        if (dm.baseImage == null) {
            JOptionPane.showMessageDialog(dm.frame, "SELECT AN IMAGE FIRST", "ERROR", JOptionPane.ERROR_MESSAGE);
            return dm.baseImage;
        }
        
        BufferedImage addImage = ImageHelper.chooseImage(dm.frame);
        
        String xPosition = JOptionPane.showInputDialog(dm.frame, "Position x coordinate for image: " + "(Image width is " + dm.baseImage.getWidth());
        String yPosition = JOptionPane.showInputDialog(dm.frame, "Position y coordinate for image: " + "(Image height is " + dm.baseImage.getHeight());
        BufferedImage firstImage = dm.baseImage;
        
        try{
            int xPos = Integer.parseInt(xPosition);
            int yPos = Integer.parseInt(yPosition);
            
            Graphics g = firstImage.getGraphics();
            g.drawImage(firstImage, 0, 0, null);
            g.drawImage(addImage, xPos, yPos, null);
            g.dispose();
            dm.baseImageLabel = new JLabel(new ImageIcon(firstImage));
            dm.frame.remove(dm.baseImageLabel);
            dm.frame.add(dm.baseImageLabel);
            dm.frame.pack();
            dm.frame.validate();
            dm.frame.repaint();
            
        } catch (ClassCastException e) {
            JOptionPane.showMessageDialog(dm.frame, "ONLY NUMBERS ALLOWED", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        
        return firstImage;
    }
    
    @Override
    public String toString() {
        return "Add Image";
    }
    
}
