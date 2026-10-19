package fr.univartois.butinfo.fractalens.complex;

public class ComplexPlane implements IComplexPlane {
    private int width;
    private int height;

    public ComplexPlane(int width , int height) {
        this.width = width;
        this.height = height;
    }

    /**
     * Determines the complex number corresponding to a pixel at a given position in an
     * image.
     *
     * @param row The row of the pixel in the image.
     * @param column The column of the pixel in the image.
     *
     * @return The complex number at the given position.
     */
    @Override
    public IComplex asComplex(int row, int column) {
        double a = column - (width/2.0);
        double b = (height/2.0)-row;
        return new Complex(a,b);
    }



}
