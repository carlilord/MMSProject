/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.jku.commands;

import at.jku.data.DataManager;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import javax.swing.JComboBox;

/**
 *
 * @author Kern
 */
public class AddEmojiCommand implements Command {
    //TODO
    @Override
    public BufferedImage execute(DataManager dm) {
        Emojis emojis = new Emojis();
       JComboBox<BufferedImage> emojiComboBox = new JComboBox(emojis.getEmojis());
       emojiComboBox.setPreferredSize(new Dimension(100,100));
       
       return dm.baseImage;
    }
    
}
