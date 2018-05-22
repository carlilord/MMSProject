package at.jku.filters;

import at.jku.Filter;
import at.jku.misc.PropertyType;
import at.jku.misc.UserInput;
import at.jku.pixels.Pixel;

import java.awt.image.BufferedImage;
import java.util.Properties;

public class SepiaFilter implements Filter{
    @Override
    public void applyFilter(BufferedImage image, Properties settings) {
        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {
                Pixel p = new Pixel(image.getRGB(x,y));
                int sRed = (int)((p.getR() * .393) + (p.getG() *.769) + (p.getB() * .189));
                int sGreen = (int)((p.getR() * .349) + (p.getG() *.686) + (p.getB() * .168));
                int sBlue = (int)((p.getR() * .272) + (p.getG() *.534) + (p.getB() * .131));
                p.setR(sRed);
                p.setG(sGreen);
                p.setB(sBlue);

                image.setRGB(x,y, p.getRawRGBA());
            }
        }
    }

    @Override
    public PropertyType getMandatoryProperties() {
        return new PropertyType(new String[] {}, UserInput.NONE);
    }

    @Override
    public String toString() {
        return SepiaFilter.class.getTypeName();
    }
}
