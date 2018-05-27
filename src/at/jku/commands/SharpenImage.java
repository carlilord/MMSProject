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
import java.awt.image.BufferedImageOp;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;

/**
 *
 * @author Kern
 */
public class SharpenImage implements Command {

    @Override
    public BufferedImage execute(DataManager dm) {
        
        if (dm.baseImage == null) {
            JOptionPane.showMessageDialog(dm.frame, "SELECT AN IMAGE FIRST", "ERROR", JOptionPane.ERROR_MESSAGE);
            return dm.baseImage;
        }
        
        BufferedImage sharpImage = dm.baseImage;
        
        //Matrix with values to sharpen an Image
        Kernel kernel = new Kernel(3, 3, new float[] { -1, -1, -1, 
                                                       -1,  9, -1, 
                                                       -1, -1, -1 });
        
        try{
        //Get Graphics of base Image and draw it
        Graphics g = sharpImage.getGraphics();
        g.drawImage(sharpImage, 0, 0, null);
        g.dispose();
        //Creating BufferedImageOp with needed Matrix and filter the Image
        BufferedImageOp biop = new ConvolveOp(kernel);
        sharpImage = biop.filter(sharpImage, null);

            dm.frame.remove(dm.baseImageLabel);
            dm.baseImageLabel = new JLabel(new ImageIcon(sharpImage));
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
        return "Sharpen Image";
    }
}
