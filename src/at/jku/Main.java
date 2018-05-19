package at.jku;

import at.jku.misc.ImageChooser;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Main {

    private static JFrame frame;
    private static ImageChooser imageChooser = new ImageChooser();


    public static void main(String[] args) {



        SwingUtilities.invokeLater(() -> {
            frame = createFrame();
            frame.pack();
            frame.setLocation(200, 200);
            frame.setVisible(true);
        });
    }


        private static JFrame createFrame(){
            JFrame frame = new JFrame("73");
            frame.getContentPane().setLayout(new BorderLayout());
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


           BufferedImage image = imageChooser.chooseImage(frame);

           JPanel panel = new JPanel();
           frame.getContentPane().add(panel,BorderLayout.NORTH);

           JLabel label = new JLabel(new ImageIcon(image));
           panel.add(label);

            return  frame;
    }




    }

