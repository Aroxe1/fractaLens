package fr.univartois.butinfo.fractalens.complex;

import java.awt.Color;

/** Available palettes; iteration-dependent palettes are created at build time. */
public enum ColorPaletteType {
    GRAYSCALE,
    GRADIENT,
    FIXED;

    public IColorStrategy create(int maxIterations) {
        return switch (this) {
            case GRAYSCALE -> new GrayScaleColorStrategy(maxIterations);
            case GRADIENT -> new GradientColorStrategy(Color.BLUE, Color.WHITE, maxIterations);
            case FIXED -> new FixedColorStrategy(Color.BLACK);
        };
    }
}
