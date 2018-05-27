package at.jku;

import at.jku.data.DataManager;

import javax.swing.*;

public class Main {

    // stores all the necessary data
    public DataManager dm;

    public Main() {
        dm = new DataManager();
    }

    public static void main(String[] args) {

        Main main = new Main();


        SwingUtilities.invokeLater(() -> {
            main.dm.frame.pack();
            main.dm.frame.setLocation(200, 200);
            main.dm.frame.setVisible(true);

        });
    }







    }

