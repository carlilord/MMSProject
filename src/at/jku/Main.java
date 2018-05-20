package at.jku;

import at.jku.misc.ImageChooser;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Main {

    private static JFrame frame;
    private static ImageChooser imageChooser = new ImageChooser();
    private static BufferedImage baseImage;
    private static JButton openBtn;
    private static JButton saveBtn;

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


           JPanel panel = new JPanel();
           frame.getContentPane().add(panel,BorderLayout.NORTH);


            // OPEN BUTTON
            ImageIcon openIcon = new ImageIcon("resources/openIcon.png");
            openBtn = new JButton(openIcon);
            openBtn.setPreferredSize(new Dimension(35, 35));
            openBtn.addActionListener(a -> {
                baseImage = imageChooser.chooseImage(frame);
                JLabel label = new JLabel(new ImageIcon(baseImage));
                frame.getContentPane().add(label, BorderLayout.CENTER);
                frame.pack();
                frame.validate();
                frame.repaint();
            });
            panel.add(openBtn);

            // SAVE BUTTON
            ImageIcon saveIcon = new ImageIcon("resources/saveIcon.png");
            saveBtn = new JButton(saveIcon);
            saveBtn.setPreferredSize(new Dimension(35, 35));
            saveBtn.addActionListener(a -> {

                if (baseImage == null) {
                    JOptionPane.showMessageDialog(frame, "Please select an image first", "ERROR", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                imageChooser.saveImage(baseImage, frame);
                JOptionPane.showMessageDialog(frame, "Image saved succesfully ", "SAVED", JOptionPane.INFORMATION_MESSAGE);

            });
            panel.add(saveBtn);



            return  frame;
    }




    }

