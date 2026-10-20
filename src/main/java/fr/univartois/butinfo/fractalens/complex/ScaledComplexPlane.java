package fr.univartois.butinfo.fractalens.complex;

/**
 * Represents a scaled complex plane.
 */
public class ScaledComplexPlane implements IComplexPlane {

    /**
     * The complex plane.
     */
    private final IComplexPlane plane;

    /**
     * The zoom factor applied to the complex numbers of the plane.
     */
    private final double zoom;

    /**
     * Creates a new instance of {@link ScaledComplexPlane}.
     *
     * @param plane the complex plane to decorate
     * @param zoom the zoom factor used to multiply the complex numbers
     */
    public ScaledComplexPlane(IComplexPlane plane, double zoom){
        this.plane = plane;
        this.zoom = zoom;
    }

    /**
     * Multiplies the complex number obtained from the plane by the zoom factor.
     *
     * @param row the row of the point in the plane
     * @param column the column of the point in the plane
     * @return the corresponding complex number scaled by the zoom factor
     */
    @Override
    public IComplex asComplex(int row, int column) {
        IComplex c = plane.asComplex(row, column);
        return c.multiply(zoom);
    }
}