package at.jku.data;

import at.jku.commands.Command;
import at.jku.commands.CommandManager;
import at.jku.data.userdialogs.SimpleSliderDialog;
import at.jku.misc.ImageHelper;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class DataManager {

    public JFrame frame;

    public BufferedImage baseImage;
    public JLabel baseImageLabel;


    private JButton openBtn;


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
        openBtn = new JButton(openIcon);
        openBtn.setPreferredSize(new Dimension(35, 35));
        openBtn.addActionListener(a -> {
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
            JOptionPane.showMessageDialog(frame, "Image saved succesfully ", "SAVED", JOptionPane.INFORMATION_MESSAGE);

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

        return frame;
    }
}
