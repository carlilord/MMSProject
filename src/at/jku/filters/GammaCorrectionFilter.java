package at.jku.filters;

import at.jku.Filter;
import at.jku.misc.PropertyType;
import at.jku.misc.UserInput;
import at.jku.pixels.Pixel;

import java.awt.image.BufferedImage;
import java.util.Properties;

public class GammaCorrectionFilter implements Filter {
    @Override
    public void applyFilter(BufferedImage image, Properties settings) {
        double gamma = Double.parseDouble(settings.getProperty("gamma"));

        int red, green, blue;

        double gamma_new = gamma;
        int[] gamma_LUT = gamma_LUT(gamma_new);

        for (int i = 0; i < image.getWidth(); i++) {
            for (int j = 0; j < image.getHeight(); j++) {
                Pixel pixel = new Pixel(image.getRGB(i, j));

                red = gamma_LUT[pixel.getR()];
                green = gamma_LUT[pixel.getG()];
                blue = gamma_LUT[pixel.getB()];

                pixel.setR(red);
                pixel.setG(green);
                pixel.setB(blue);

                // Write pixels into image
                image.setRGB(i, j, pixel.getRawRGBA());

            }

        }
    }

    @Override
    public PropertyType getMandatoryProperties() {
        return new PropertyType(new String[] { "gamma:s:0#2:0" }, UserInput.SIMPLE_NUMBER);
    }

    @Override
    public String toString() {
        return "Gamma Correction";
    }

    private static int[] gamma_LUT(double gamma_new) {
        int[] gamma_LUT = new int[256];

        for (int i = 0; i < gamma_LUT.length; i++) {
            gamma_LUT[i] = (int) (255 * (Math.pow((double) i / (double) 255, gamma_new)));
        }

        return gamma_LUT;
    }
}
