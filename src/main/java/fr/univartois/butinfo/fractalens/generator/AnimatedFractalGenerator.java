package fr.univartois.butinfo.fractalens.generator;

import com.madgag.gif.fmsware.AnimatedGifEncoder;
import fr.univartois.butinfo.fractalens.image.IFractalImage;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class AnimatedFractalGenerator implements IFractalGenerator {

    private List<IFractalImage> images = new ArrayList<>();

    /**
     * The generators producing the frames of the animation, in order.
     */
    private final List<IFractalGenerator> generatorList;

    /**
     * The path of the GIF file in which to save the animation.
     */
    private final String outputPath;

    /**
     * The delay between two frames of the animation, in milliseconds.
     */
    private final int delayMS;

    /**
     * Creates a new AnimatedFractalGenerator.
     *
     * @param generatorList The generators producing the frames of the animation.
     * @param outputPath The path of the GIF file in which to save the animation.
     * @param delayMS The delay between two frames, in milliseconds.
     */
    public AnimatedFractalGenerator(List<IFractalGenerator> generatorList,
                                    String outputPath, int delayMS) {
        this.generatorList = new ArrayList<>(generatorList);
        this.outputPath = outputPath;
        this.delayMS = delayMS;
    }

    /**
     * Generates the first frame of the animation.
     *
     * @return The image of the first frame.
     *
     * @see IFractalGenerator#generateImage()
     */
    @Override
    public IFractalImage generateImage() {
        for (IFractalGenerator generator : generatorList) {
            images.add(generator.generateImage());
        }
        return images.get(0);
    }

    /**
     * Computes the color of a given pixel in the first frame of the animation.
     *
     * @param row The row of the pixel.
     * @param column The column of the pixel.
     *
     * @return The computed color for the pixel.
     *
     * @see IFractalGenerator#computePixelColor(int, int)
     */
    @Override
    public Color computePixelColor(int row, int column) {
        return generatorList.get(0).computePixelColor(row, column);
    }

    /**
     * Generates all the frames of the animation and saves them
     * as an animated GIF in the output file.
     * The animation loops indefinitely.
     *
     * @see IFractalGenerator#saveImage()
     */
    @Override
    public void saveImage() {

        AnimatedGifEncoder encoder = new AnimatedGifEncoder();
        encoder.start(outputPath);
        encoder.setDelay(delayMS);
        encoder.setRepeat(0);
        encoder.setQuality(10);

        for (IFractalImage image : images) {
            encoder.addFrame(image.asBufferedImage());
        }

        encoder.finish();
    }
}
