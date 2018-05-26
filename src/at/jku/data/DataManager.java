package at.jku.data;

import at.jku.commands.AddImageCommand;
import at.jku.commands.Command;
import at.jku.commands.CommandManager;
import at.jku.data.userdialogs.SimpleSliderDialog;
import at.jku.misc.ImageHelper;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

public class DataManager {

    public JFrame frame;

    public BufferedImage baseImage;
    public JLabel baseImageLabel;


    private JList list;
    private ArrayList<ImageIcon> imageList;


    public JComboBox<Command> commandsComboBox;
    public CommandManager commandManager = new CommandManager();

    public DataManager() {
        createFrame();
    }

    private JFrame createFrame() {
        frame = new JFrame("Photoshop 2.0");
        frame.getContentPane().setLayout(new BorderLayout());
        frame.setPreferredSize(new Dimension(600, 600));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


        JPanel panel = new JPanel();
        frame.getContentPane().add(panel, BorderLayout.NORTH);


        // OPEN BUTTON
        ImageIcon openIcon = new ImageIcon("resources/openIcon.png");
        JButton openBtn = new JButton(openIcon);
        openBtn.setPreferredSize(new Dimension(35, 35));
        openBtn.addActionListener(a -> {

            if (baseImageLabel != null) {
                frame.remove(baseImageLabel);
            }

            baseImage = ImageHelper.chooseImage(frame);
            baseImageLabel = new JLabel(new ImageIcon(baseImage));
            frame.getContentPane().add(baseImageLabel, BorderLayout.CENTER);
            frame.pack();
            frame.validate();
            frame.repaint();
        });
        panel.add(openBtn);

        // SAVE BUTTON
        ImageIcon saveIcon = new ImageIcon("resources/saveIcon.png");
        JButton saveBtn = new JButton(saveIcon);
        saveBtn.setPreferredSize(new Dimension(35, 35));
        saveBtn.addActionListener(a -> {

            if (baseImage == null) {
                JOptionPane.showMessageDialog(frame, "Please select an image first", "ERROR", JOptionPane.ERROR_MESSAGE);
                return;
            }

            ImageHelper.saveImage(baseImage, frame);


        });
        panel.add(saveBtn);

        // Command Drop down menu
        commandsComboBox = new JComboBox<>(new DefaultComboBoxModel<>(commandManager.getCommands()));
        commandsComboBox.setPreferredSize(new Dimension(100, 35));
        commandsComboBox.addActionListener(a -> {
            Command selectedCommand = (Command) commandsComboBox.getSelectedItem();
            selectedCommand.execute(this);
            if(baseImageLabel != null) {
                baseImageLabel.updateUI();
            }
        });
        panel.add(commandsComboBox);

        addImages();
        list = new JList(imageList.toArray());
        setUpDragAndDrop();

        JScrollPane scroll = new JScrollPane(list);
        scroll.setPreferredSize(new Dimension(150, 500));

        JPanel pContainer = new JPanel();
        pContainer.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        pContainer.add(scroll, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        JButton addImageButton = new JButton("Add Custom Icon");
        addImageButton.addActionListener(l -> {
            new AddImageCommand().execute(this);
        });
        pContainer.add(addImageButton, gbc);

        frame.getContentPane().add(pContainer, BorderLayout.WEST);
        return frame;
    }

    private void addImages() {
        ArrayList<BufferedImage> bufferedImageList = new ArrayList<BufferedImage>();
        imageList = new ArrayList<ImageIcon>();

        try {
            for (int i = 1; i < 7; i++) {
                bufferedImageList.add(ImageHelper.scaleImage(
                        ImageIO.read(new FileInputStream("resources/e" + i + ".png")),
                        100,
                        100,
                        90));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (BufferedImage bufferedImage : bufferedImageList) {
            imageList.add(new ImageIcon(bufferedImage));
        }
    }

    private DataManager root = this;

    private void setUpDragAndDrop() {
        MouseAdapter ma = new MouseAdapter() {
            private ImageIcon selectedImage;
            @Override
            public void mousePressed(MouseEvent e) {
                selectedImage = (ImageIcon) list.getSelectedValue();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                double slopeX = 1.0 * (baseImageLabel.getIcon().getIconWidth()) / (baseImageLabel.getWidth());
                double slopeY = 1.0 * (baseImageLabel.getIcon().getIconHeight()) / (baseImageLabel.getHeight());

                int actualX = e.getX()- list.getWidth() -selectedImage.getIconWidth()/2;
                actualX = actualX < 0 ? 0 : actualX;
                int actualY = e.getY() - selectedImage.getIconWidth()/2;
                actualY = actualY < 0 ? 0 : actualY;

                int x = (int) Math.round(slopeX * actualX);
                int y = (int) Math.round(slopeY * actualY);
                new AddImageCommand(x, y, (BufferedImage) selectedImage.getImage()).execute(root);
                baseImageLabel.updateUI();
            }
        };

        list.addMouseListener(ma);
    }
}
