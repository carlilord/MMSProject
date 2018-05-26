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

        int[] gammaLookupTable = getGammaLookupTable(gamma);

        for (int i = 0; i < image.getWidth(); i++) {
            for (int j = 0; j < image.getHeight(); j++) {
                Pixel pixel = new Pixel(image.getRGB(i, j));

                red = gammaLookupTable[pixel.getR()];
                green = gammaLookupTable[pixel.getG()];
                blue = gammaLookupTable[pixel.getB()];

                pixel.setR(red);
                pixel.setG(green);
                pixel.setB(blue);

                image.setRGB(i, j, pixel.getRawRGBA());
            }

        }
    }

    @Override
    public PropertyType getMandatoryProperties() {
        return new PropertyType(new String[] { "gamma:s:1#2:1" }, UserInput.SIMPLE_NUMBER);
    }

    @Override
    public String toString() {
        return "Gamma Correction";
    }

    private static int[] getGammaLookupTable(double gammaNew) {
        int[] gammaLookupTable = new int[256];

        for (int i = 0; i < gammaLookupTable.length; i++) {
            gammaLookupTable[i] = (int) (255 * (Math.pow((double) i / (double) 255, gammaNew)));
        }

        return gammaLookupTable;
    }
}
