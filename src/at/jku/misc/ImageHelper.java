package at.jku.misc;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ImageHelper {
    public static BufferedImage scaleImage(BufferedImage image, int maxWidth,
      int maxHeight, int quality) {
        BufferedImage scaled;
        int type = BufferedImage.TYPE_INT_ARGB;

        float s = 1.0f;
        float wFactor = (float) maxWidth / (float) image.getWidth();
        float hFactor = (float) maxHeight / (float) image.getHeight();
        if (wFactor < hFactor) {
            s = wFactor;
        } else {
            s = hFactor;
        }

        int w = (int) (image.getWidth() * s), h = (int) (image.getHeight() * s);

        Image tmp = image.getScaledInstance(w, h, quality);
        scaled = new BufferedImage(w, h, type);
        Graphics2D g = scaled.createGraphics();
        g.drawImage(tmp, 0, 0, null);
        g.dispose();
        return scaled;
    }

    public static BufferedImage convertToBufferedImage(Image img) {
        BufferedImage bufferedImage = new BufferedImage(
                img.getWidth(null),
                img.getHeight(null),
                BufferedImage.TYPE_INT_ARGB);
        bufferedImage.getGraphics()
                .drawImage(img, 0, 0, null);
        return bufferedImage;
    }
}