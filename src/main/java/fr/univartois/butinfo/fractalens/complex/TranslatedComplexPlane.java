package fr.univartois.butinfo.fractalens.complex;

public class TranslatedComplexPlane implements IComplexPlane{
    /**
     * The complex plane to decorate.
     */
    private final IComplexPlane plane;
    /**
     * The offset added to the complex numbers of the plane.
     */
    private final IComplex offset;


    /**
     * Creates a new instance of {@link TranslatedComplexPlane}.
     *
     * @param plane the complex plane to decorate
     * @param offset the offset added to the complex numbers of the plane
     */
    public TranslatedComplexPlane(IComplexPlane plane, IComplex offset){
        this.plane = plane;
        this.offset = offset;
    }

    /**
     * Adds the offset to the complex number obtained from the decorated plane.
     *
     * @param row the row of the point in the plane
     * @param column the column of the point in the plane
     * @return the corresponding complex number, shifted by the offset
     */
    @Override
    public IComplex asComplex(int row, int column) {
        IComplex c = plane.asComplex(row, column);
        return c.add(offset);
    }
}
