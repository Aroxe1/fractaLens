package fr.univartois.butinfo.fractalens;


/**
 * Lists the static and animated image formats supported by the command.
 * @author Maxence Royon (implementation)
 */
public enum ImageType {
    /**
     * A single static image.
     */
    SINGLE,

    /**
     * An animated GIF zooming progressively on the fractal.
     */
    ZOOM,

    /**
     * An animated GIF rotating the fractal.
     */
    ROTATION
}
