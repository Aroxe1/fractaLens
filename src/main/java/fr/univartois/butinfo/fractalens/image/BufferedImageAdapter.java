package fr.univartois.butinfo.fractalens.image;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Adapts a {@link BufferedImage} to the {@link IFractalImage} interface.
 */
public class BufferedImageAdapter implements IFractalImage{
    /**
     * The adapted image, to which all operations are delegated.
     */
    private final BufferedImage image;

    /**
     * Creates a new instance of {@code BufferedImageAdapter}.
     *
     * @param image The image to adapt.
     */
    public BufferedImageAdapter(BufferedImage image) {
        this.image =image;
    }

    /**
     * Gives the height of this image.
     *
     * @return The height of the image, in pixels.
     */
    @Override
    public int getHeight() {
        return image.getHeight();
    }

    /**
     * Gives the width of this image.
     *
     * @return The width of the image, in pixels.
     */
    @Override
    public int getWidth() {
        return image.getWidth();
    }

    /**
     * Gives the adapted BufferedImage.
     *
     * @return The adapted image.
     */
    public BufferedImage getImage(){
        return image;
    }
    /**
     * Updates the color of the pixel at the given position in this image.
     *
     * @param row The row of the pixel to update.
     * @param column The column of the pixel to update.
     * @param color The new color for the pixel.
     */
    @Override
    public void setColor(int row, int column, Color color) {
        image.setRGB(column, row, color.getRGB());
    }

    /**
     * Saves this image in a file.
     *
     * @param path The path of the file in which to save this image.
     *
     * @throws IOException If an I/O error occurs when writing the file.
     */
    @Override
    public void saveAs(String path) throws IOException {
        String[] parts = path.split("\\.");
        String format = parts[parts.length - 1];
        if (!ImageIO.write(image, format, new File(path))) {
            throw new IOException("Unsupported image format: " + format);
        }
    }

    /**
     * Gives the adapted image.
     *
     * @return The {@link BufferedImage} adapted by this object.
     *
     * @see IFractalImage#asBufferedImage()
     */
    @Override
    public BufferedImage asBufferedImage() {
        return image;
    }

}
