/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.jku.commands;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 *
 * @author Kern
 */
public class Emojis {
    
    private ArrayList<BufferedImage> emojis;
    //TODO
    public Emojis(){
        emojis = new ArrayList<BufferedImage>();
    }
    
    public BufferedImage[] getEmojis() {
        return emojis.toArray(new BufferedImage[emojis.size()]);
    }
}
