package at.jku;

import at.jku.misc.PropertyType;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Properties;

public interface Filter {
    void applyFilter(BufferedImage image, Properties settings);
    PropertyType getMandatoryProperties();
}
