package at.jku.misc;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageChooser {



    public BufferedImage chooseImage(JFrame frame){

        JFileChooser jfc = new JFileChooser();
        jfc.setDialogTitle("Please choose your base image");
        jfc.setFileFilter(new FileNameExtensionFilter("jpg or png images","jpg","png"));
        int dialogValue = jfc.showOpenDialog(frame);

        File file;
        BufferedImage image = null;

        if (dialogValue == JFileChooser.APPROVE_OPTION){
            file = jfc.getSelectedFile();

            try {
                image = ImageIO.read(file);
            } catch (IOException e) {
                e.printStackTrace();
            }


        }

        return image;
    }

    public void saveImage(BufferedImage image, JFrame frame) {
        JFileChooser jfc = new JFileChooser();
        int dialogValue = jfc.showSaveDialog(frame);

        if (dialogValue == JFileChooser.APPROVE_OPTION) {

            File out = jfc.getSelectedFile();
            try {
                ImageIO.write(image, "jpg", out);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }

}
