package at.jku.pixels;

import java.awt.image.ColorModel;
import java.awt.image.DirectColorModel;

public class Pixel {
    private int rawRGBA;

    private int alpha;
    private int r, g, b;

    private int y, cb, cr;

    /** One color model per JVM is enough */
    private static DirectColorModel colorModel = null;

    /**
     * Initialize with a raw RGB value
     * @param rawRBGA
     */
    public Pixel(int rawRGBA) {
        setRawRGBA(rawRGBA);
    }

    /**
     * Initialize with R G B and Alpha values
     * @param r
     * @param g
     * @param b
     * @param alpha
     */
    public Pixel(int r, int g, int b, int alpha) {
        this.r = byteRange(r);
        this.g = byteRange(g);
        this.b = byteRange(b);
        this.alpha = byteRange(alpha);
        updateRawFromRGB();
        updateYCbCrFromRGB();
    }

    /**
     * Initialize with YCbCr Model
     * @param y
     * @param cb
     * @param cr
     */
    public Pixel(int y, int cb, int cr) {
        this.y = byteRange(y);
        this.cb = byteRange(cb);
        this.cr = byteRange(cr);
        updateRGBFromYCbCr();
        updateRawFromRGB();
    }

    /** Extract raw pixels red value */
    public static int getRed(int rgba) {
        return getColorModel().getRed(rgba);
    }

    /** Extract raw pixels green value */
    public static int getGreen(int rgba) {
        return getColorModel().getGreen(rgba);
    }

    /** Extract raw pixels blue value */
    public static int getBlue(int rgba) {
        return getColorModel().getBlue(rgba);
    }

    /** Extract raw pixels alpha channel */
    public static int getAlpha(int rgba) {
        return getColorModel().getAlpha(rgba);
    }

    /** Create a raw pixel from rgba Values */
    public static int generateRGBAPixel(int r, int g, int b, int a) {
        a = a << 24;
        r = r << 16;
        g = g << 8;
        return a | r | g | b;
    }

    public int getRawRGBA() {
        return rawRGBA;
    }

    public void setRawRGBA(int rawRGBA) {
        this.rawRGBA = rawRGBA;
        updateRGBFromInt();
        updateYCbCrFromRGB();
    }

    public int getAlpha() {
        return alpha;
    }

    public void setAlpha(int alpha) {
        this.alpha = byteRange(alpha);
        updateRawFromRGB();
        updateYCbCrFromRGB();
    }

    public int getR() {
        return r;
    }

    public void setR(int r) {
        this.r = byteRange(r);
        updateRawFromRGB();
        updateYCbCrFromRGB();
    }

    public int getG() {
        return g;
    }

    public void setG(int g) {
        this.g = byteRange(g);
        updateRawFromRGB();
        updateYCbCrFromRGB();
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = byteRange(b);
        updateRawFromRGB();
        updateYCbCrFromRGB();
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = byteRange(y);
        updateRGBFromYCbCr();
        updateRawFromRGB();
    }

    public int getCb() {
        return cb;
    }

    public void setCb(int cb) {
        this.cb = byteRange(cb);
        updateRGBFromYCbCr();
        updateRawFromRGB();
    }

    public int getCr() {
        return cr;
    }

    public void setCr(int cr) {
        this.cr = byteRange(cr);
        updateRGBFromYCbCr();
        updateRawFromRGB();
    }

    public static void setColorModel(DirectColorModel colorModel) {
        Pixel.colorModel = colorModel;
    }

    /** Inline init of color model */
    private static DirectColorModel getColorModel() {
        if (colorModel == null) {
            colorModel = (DirectColorModel) ColorModel.getRGBdefault();
        }
        return colorModel;
    }

    /** Create a raw Integer value from single rgb a values */
    private void updateRawFromRGB() {
        rawRGBA = generateRGBAPixel(r, g, b, alpha);
    }

    /** Create single rgb values form a raw int */
    private void updateRGBFromInt() {
        r = byteRange(Pixel.getRed(rawRGBA));
        g = byteRange(Pixel.getGreen(rawRGBA));
        b = byteRange(Pixel.getBlue(rawRGBA));
        alpha = byteRange(Pixel.getAlpha(rawRGBA));
    }

    /** Generate YCbCr values from rgb
     * @see http://en.wikipedia.org/wiki/YCbCr and lecture slides */
    private void updateYCbCrFromRGB() {
        y = byteRange((int)(0.299*r+0.587*g+0.114*b));
        cb = byteRange((int)(128-0.169*r-0.331*g+0.500*b));
        cr = byteRange((int)(128+0.500*r-0.419*g-0.081*b));
    }

    /** Genrate RGB from YCbCr values
     * @see http://en.wikipedia.org/wiki/YCbCr and lecture slides */
    private void updateRGBFromYCbCr() {
        // we assume full alpha
        alpha = 255;
        r = byteRange((int)((298.082*y/256) + (408.583*cr/256) - 222.921));
        g = byteRange((int)((298.082*y/256) - (100.291*cb/256) - (208.12*cr/256) + 135.576));
        b = byteRange((int)((298.082*y/256) + (516.412*cb/256) - 276.836));
    }

    /**
     * Puts integers in range
     *
     * @param input
     * @param lower
     * @param upper
     * @return
     */
    private int range(int input, int lower, int upper) {
        if(input > upper) {
            return upper;
        }else if(input < lower) {
            return lower;
        }
        return input;
    }

    private int byteRange(int input) {
        return range(input, 0, 255);
    }
}
