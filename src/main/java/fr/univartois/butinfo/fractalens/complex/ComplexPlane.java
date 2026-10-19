package fr.univartois.butinfo.fractalens.complex;

public class ComplexPlane implements IComplexPlane {
    private int width;
    private int height;

    public ComplexPlane(int width, int height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public IComplex asComplex(int row, int column) {
        double a = column - (width/2.0);
        double b = (height/2.0)-row;
        return new Complex(a,b);

    }

}
