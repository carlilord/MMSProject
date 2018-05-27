package at.jku.filters;

import at.jku.Filter;
import at.jku.misc.PropertyType;
import at.jku.misc.UserInput;
import at.jku.pixels.Pixel;

import java.awt.image.BufferedImage;
import java.util.Properties;

// Changes the RGB composition based on percentages
public class RGBFilter implements Filter {
    @Override
    public void applyFilter(BufferedImage image, Properties settings) {
        int redPercentage = (int) Double.parseDouble(settings.getProperty("redPercentage"));
        int greenPercentage = (int) Double.parseDouble(settings.getProperty("greenPercentage"));
        int bluePercentage = (int) Double.parseDouble(settings.getProperty("bluePercentage"));
        for (int h = 0; h < image.getHeight(); h++) {
            for (int w = 0; w < image.getWidth(); w++) {
                Pixel p = new Pixel(image.getRGB(w,h));
                p.setR(p.getR()*(redPercentage/100));
                p.setG(p.getG()*(greenPercentage/100));
                p.setB(p.getB()*(bluePercentage/100));
                image.setRGB(w,h, p.getRawRGBA());
            }
        }
    }

    @Override
    public PropertyType getMandatoryProperties() {
        return new PropertyType(new String[] { "redPercentage:s:0#100:100", "greenPercentage:s:0#100:100", "bluePercentage:s:0#100:100" }, UserInput.SLIDER);
    }

    @Override
    public String toString() {
        return "RGB Filter";
    }
}
