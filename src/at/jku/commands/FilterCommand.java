package at.jku.commands;

import at.jku.Filter;
import at.jku.data.DataManager;

import javax.swing.*;
import java.awt.image.BufferedImage;
import java.util.Properties;

public class FilterCommand implements Command {
    private Filter filter;

    public void FilterCommand(Filter filter) {
        this.filter = filter;
    }

    @Override
    public BufferedImage execute(DataManager dm) {
        if (dm.baseImage == null) {
            JOptionPane.showMessageDialog(dm.frame, "SELECT AN IMAGE FIRST", "ERROR", JOptionPane.ERROR_MESSAGE);
            return dm.baseImage;
        }

        // ToDo (carl): Parse settings
        filter.applyFilter(dm.baseImage, null);
        return dm.baseImage;
    }
}
