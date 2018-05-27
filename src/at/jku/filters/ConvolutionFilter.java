package at.jku.filters;

import at.jku.Filter;
import at.jku.misc.PropertyType;
import at.jku.misc.UserInput;
import at.jku.pixels.Pixel;

import java.awt.image.BufferedImage;
import java.util.Properties;

/**
 *  The Convolution Filter Algorithm
 */
public class ConvolutionFilter implements Filter {
    //Blur Filtermatrix
    double meanFilter[][] =
            {
                    {1, 1, 1},
                    {1, 1, 1},
                    {1, 1, 1}
            };

    //Blur Filtermatrix
    double blurFilter[][] =
            {
                    {0, 0, 1, 0, 0},
                    {0, 1, 1, 1, 0},
                    {1, 1, 1, 1, 1},
                    {0, 1, 1, 1, 0},
                    {0, 0, 1, 0, 0}
            };

    //Motion Blur Filtermatrix
    double motionBlurFilter[][] =
            {
                    {1, 0, 0, 0, 0, 0, 0, 0},
                    {0, 1, 0, 0, 0, 0, 0, 0},
                    {0, 0, 1, 0, 0, 0, 0, 0},
                    {0, 0, 0, 1, 0, 0, 0, 0},
                    {0, 0, 0, 0, 1, 0, 0, 0},
                    {0, 0, 0, 0, 0, 1, 0, 0},
                    {0, 0, 0, 0, 0, 0, 1, 0},
                    {0, 0, 0, 0, 0, 0, 0, 1},
            };

    // The name which will be displayed
    String filterName;

    // The filter which will be used
    double currentFilter[][];

    int filterHeight;
    int filterWidth;

    double factor = 0.0;
    double bias = 0.0;


    public ConvolutionFilter(ConvolutionType type) {
        setFilter(type);
    }

    @Override
    public void applyFilter(BufferedImage image, Properties settings) {
        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {
                Pixel p = new Pixel(image.getRGB(x,y));
                int newValueR = 0;
                int newValueG = 0;
                int newValueB = 0;

                // Calculating the new value for the pixel
                for (int i = 0; i < filterWidth; i++) {
                    for (int j = 0; j < filterHeight; j++) {
                        int newX = x - ((filterWidth / 2) - i);
                        int newY = y - ((filterHeight / 2) - j);
                        if(newX >= 0 && newY >= 0 && newX < image.getWidth()
                                && newY < image.getHeight()) {
                            Pixel temp = new Pixel(image.getRGB(newX,newY));
                            newValueR += currentFilter[i][j] * temp.getR() * factor;
                            newValueG += currentFilter[i][j] * temp.getG() * factor;
                            newValueB += currentFilter[i][j] * temp.getB() * factor;
                        }
                    }
                }

                p.setR(MapToByte(newValueR));
                p.setG(MapToByte(newValueG));
                p.setB(MapToByte(newValueB));
                image.setRGB(x, y, p.getRawRGBA());
            }
        }
    }

    @Override
    public PropertyType getMandatoryProperties() {
        return new PropertyType(new String[] { }, UserInput.NONE);
    }

    @Override
    public String toString() {
        return filterName;
    }

    private void setFilter(ConvolutionType type){
        switch (type) {
            case MEAN:
                currentFilter = meanFilter;
                filterName = "Mean Filter";
                break;
            case BLUR:
                currentFilter = blurFilter;
                filterName = "Blur Filter";
                break;
            case MOTION:
                currentFilter = motionBlurFilter;
                filterName = "Motion Blur Filter";
                break;
        }

        filterHeight = currentFilter[0].length;
        filterWidth = currentFilter.length;

        //calculate factor
        double countFilterValues = 0.0;

        for(int filterY = 0; filterY < filterHeight; filterY++) {
            for (int filterX = 0; filterX < filterWidth; filterX++) {
                if (currentFilter[filterY][filterX] > 0) {
                    countFilterValues++;
                }
            }
        }

        factor = 1.0/countFilterValues;
        bias = 0;

    }

    private int MapToByte(int i) {
        return Math.max(Math.min(255,i), 0);
    }
}
