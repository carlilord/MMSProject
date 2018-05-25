package at.jku.commands;

import at.jku.Filter;
import at.jku.data.DataManager;
import at.jku.data.UserDialogChooser;
import at.jku.filters.SepiaFilter;
import at.jku.misc.PropertyType;

import javax.swing.*;
import java.awt.image.BufferedImage;
import java.util.Properties;

public class FilterCommand implements Command {
    private Filter filter;

    public FilterCommand(Filter filter) {
        this.filter = filter;
    }

    @Override
    public void execute(DataManager dm) {
        if (dm.baseImage == null) {
            JOptionPane.showMessageDialog(dm.frame, "SELECT AN IMAGE FIRST", "ERROR", JOptionPane.ERROR_MESSAGE);
            return;
        }

        PropertyType mandatoryProperties = filter.getMandatoryProperties();
        Properties properties = new UserDialogChooser(
                mandatoryProperties.getProperties(),
                mandatoryProperties.getUserInput())
                    .askValue(dm.frame);
        filter.applyFilter(dm.baseImage, properties);
    }

    @Override
    public String toString() {
        return filter == null ? "Filter" : filter.toString();
    }
}
