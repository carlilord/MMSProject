/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.jku.commands;

import at.jku.data.DataManager;
import at.jku.misc.ImageHelper;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author Kern
 */
public class AddFrameCommand implements Command {

    @Override
    public BufferedImage execute(DataManager dm) {
        
        if (dm.baseImage == null) {
            JOptionPane.showMessageDialog(dm.frame, "SELECT AN IMAGE FIRST", "ERROR", JOptionPane.ERROR_MESSAGE);
            return dm.baseImage;
        }
        BufferedImage framedImage = dm.baseImage;
        BufferedImage addFrame = ImageHelper.chooseImage(dm.frame);
        
        try{
            Image temp = addFrame.getScaledInstance(framedImage.getWidth(), framedImage.getHeight(), Image.SCALE_SMOOTH);
            BufferedImage scaledImage = new BufferedImage(framedImage.getWidth(), framedImage.getHeight(), BufferedImage.TYPE_INT_ARGB);
            Graphics g = scaledImage.getGraphics();
            g.drawImage(temp, 0, 0, null);
            g.dispose();
            Graphics g2 = framedImage.getGraphics();
            g2.drawImage(temp, 0, 0, null);
            g2.dispose();
            dm.frame.remove(dm.baseImageLabel);
            dm.baseImageLabel = new JLabel(new ImageIcon(framedImage));
            dm.frame.add(dm.baseImageLabel);
            dm.frame.pack();
            dm.frame.validate();
            dm.frame.repaint();
            
        } catch (ClassCastException e) {
            JOptionPane.showMessageDialog(dm.frame, "ONLY NUMBERS ALLOWED", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        
        return framedImage;
    }
    
         @Override
    public String toString() {
        return "Add Frame";
    }
}
