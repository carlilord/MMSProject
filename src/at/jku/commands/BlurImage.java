/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.jku.commands;

import at.jku.data.DataManager;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author Kern
 */
public class BlurImage implements Command {

    @Override
    public BufferedImage execute(DataManager dm) {
        
        if (dm.baseImage == null) {
            JOptionPane.showMessageDialog(dm.frame, "SELECT AN IMAGE FIRST", "ERROR", JOptionPane.ERROR_MESSAGE);
            return dm.baseImage;
        }
        
        BufferedImage sharpImage = dm.baseImage;
        Kernel kernel = new Kernel(3, 3, new float[] { 1f / 9f, 1f / 9f, 1f / 9f,
                                                       1f / 9f, 1f / 9f, 1f / 9f,
                                                       1f / 9f, 1f / 9f, 1f / 9f });
        
        try{
            
        Graphics g = sharpImage.getGraphics();
        g.drawImage(sharpImage, 0, 0, null);
        g.dispose();
        BufferedImageOp biop = new ConvolveOp(kernel);
        sharpImage = biop.filter(sharpImage, null);
        
        dm.baseImageLabel = new JLabel(new ImageIcon(sharpImage));
            dm.frame.remove(dm.baseImageLabel);
            dm.frame.add(dm.baseImageLabel);
            dm.frame.pack();
            dm.frame.validate();
            dm.frame.repaint();
        
        } catch (ClassCastException e) {
            JOptionPane.showMessageDialog(dm.frame, "ONLY NUMBERS ALLOWED", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        
        return sharpImage;
        
    }
    
     @Override
    public String toString() {
        return "Blur Image";
    }
}
