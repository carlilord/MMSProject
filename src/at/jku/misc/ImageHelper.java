package at.jku.misc;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageHelper {
    public static BufferedImage scaleImage(BufferedImage image, int maxWidth,
      int maxHeight, int quality) {
        BufferedImage scaled;
        int type = BufferedImage.TYPE_INT_ARGB;

        float s;
        float wFactor = (float) maxWidth / (float) image.getWidth();
        float hFactor = (float) maxHeight / (float) image.getHeight();
        if (wFactor < hFactor) {
            s = wFactor;
        } else {
            s = hFactor;
        }

        int w = (int) (image.getWidth() * s), h = (int) (image.getHeight() * s);

        Image tmp = image.getScaledInstance(w, h, quality);
        scaled = new BufferedImage(w, h, type);
        Graphics2D g = scaled.createGraphics();
        g.drawImage(tmp, 0, 0, null);
        g.dispose();
        return scaled;
    }

    public static BufferedImage convertToBufferedImage(Image img) {
        BufferedImage bufferedImage = new BufferedImage(
                img.getWidth(null),
                img.getHeight(null),
                BufferedImage.TYPE_INT_ARGB);
        bufferedImage.getGraphics()
                .drawImage(img, 0, 0, null);
        return bufferedImage;
    }

    public static BufferedImage chooseImage(JFrame frame) {

        JFileChooser jfc = new JFileChooser();
        jfc.setDialogTitle("Please choose your base image");
        jfc.setFileFilter(new FileNameExtensionFilter("jpg or png images", "jpg", "png"));
        int dialogValue = jfc.showOpenDialog(frame);

        File file;
        BufferedImage image = null;

        if (dialogValue == JFileChooser.APPROVE_OPTION) {
            file = jfc.getSelectedFile();

            try {
                image = ImageIO.read(file);
            } catch (IOException e) {
                e.printStackTrace();
            }


        }

        if (image.getHeight() < 600 || image.getHeight() < 600) {
            return image;
        }

        return ImageHelper.scaleImage(image, 600, 600, Image.SCALE_SMOOTH);
    }

    public static void saveImage(BufferedImage image, JFrame frame) {
        JFileChooser jfc = new JFileChooser();
        int dialogValue = jfc.showSaveDialog(frame);

        if (dialogValue == JFileChooser.APPROVE_OPTION) {

            File out = jfc.getSelectedFile();
            try {
                ImageIO.write(image, "jpg", out);
                JOptionPane.showMessageDialog(frame, "Image saved succesfully ", "SAVED", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            JOptionPane.showMessageDialog(frame, "Image not saved ", "NOT SAVED", JOptionPane.INFORMATION_MESSAGE);
        }


    }
}