package fr.univartois.butinfo.fractalens.complex;

public class ScaledComplexPlane implements IComplexPlane{
    private final IComplexPlane plane;
    private final double zoom;

    public ScaledComplexPlane(IComplexPlane plane, double zoom){
        this.plane = plane;
        this.zoom = zoom;
    }

    @Override
    public IComplex asComplex(int row, int column) {
        IComplex c = plane.asComplex(row, column);
        return c.multiply(zoom);
    }
}
