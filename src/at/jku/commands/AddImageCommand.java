/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.jku.commands;

import at.jku.data.DataManager;
import at.jku.misc.ImageHelper;
import at.jku.misc.UserInput;

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
    private int x;
    private int y;
    private BufferedImage addImage;
    private UserInput userInput;

    public AddImageCommand() {
        userInput = UserInput.SIMPLE_NUMBER;
    }

    public AddImageCommand(int x, int y, BufferedImage addImage) {
        this.x = x;
        this.y = y;
        this.addImage = addImage;
        userInput = UserInput.NONE;
    }

    @Override
    public BufferedImage execute(DataManager dm) {
        
        if (dm.baseImage == null) {
            JOptionPane.showMessageDialog(dm.frame, "SELECT AN IMAGE FIRST", "ERROR", JOptionPane.ERROR_MESSAGE);
            return dm.baseImage;
        }
        
        //User input

        if(userInput == UserInput.SIMPLE_NUMBER) {
            addImage = ImageHelper.chooseImage(dm.frame);
            x = Integer.parseInt(JOptionPane.showInputDialog(dm.frame, "Position x coordinate for image: " + "(Image width is " + dm.baseImage.getWidth()));
            y = Integer.parseInt(JOptionPane.showInputDialog(dm.frame, "Position y coordinate for image: " + "(Image height is " + dm.baseImage.getHeight()));
        }
        BufferedImage firstImage = dm.baseImage;

        
        try{
            //Get Graphics of base Image and draw the first Image, then draw the second Image you want to add
            Graphics g = firstImage.getGraphics();
            g.drawImage(firstImage, 0, 0, null);
            g.drawImage(addImage, x, y, null);
            g.dispose();
            dm.frame.remove(dm.baseImageLabel);
            dm.baseImageLabel = new JLabel(new ImageIcon(firstImage));
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
