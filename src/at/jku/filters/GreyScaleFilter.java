package at.jku.filters;

import at.jku.Filter;
import at.jku.misc.PropertyType;
import at.jku.misc.UserInput;
import at.jku.pixels.Pixel;

import java.awt.image.BufferedImage;
import java.util.Properties;

// Calcullates the average of RGB values
public class GreyScaleFilter implements Filter {
    @Override
    public void applyFilter(BufferedImage image, Properties settings) {
        for (int h = 0; h < image.getHeight(); h++) {
            for (int w = 0; w < image.getWidth(); w++) {
                Pixel p = new Pixel(image.getRGB(w,h));
                int average = (p.getR() + p.getG() + p.getB()) / 3;
                p.setR(average);
                p.setG(average);
                p.setB(average);
                image.setRGB(w,h, p.getRawRGBA());
            }
        }
    }

    @Override
    public PropertyType getMandatoryProperties() {
        return new PropertyType(new String[] { }, UserInput.NONE);
    }

    @Override
    public String toString() {
        return "Grey Scale Filter";
    }
}
