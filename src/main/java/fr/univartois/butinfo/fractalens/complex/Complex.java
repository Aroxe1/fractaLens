package fr.univartois.butinfo.fractalens.complex;

public class Complex implements IComplex{
    private double real;
    private double imaginary;

    public Complex(double real, double imaginary){
        this.real = real;
        this.imaginary = imaginary;
    }

    public double getRealPart(){
        return real;
    }

    public double getImaginaryPart(){
        return imaginary;
    }
    @Override
    public double abs(){
        return Math.sqrt(real * real + imaginary * imaginary);
    }

    @Override
    public IComplex negate(){
        return new Complex(-real, -imaginary);
    }

    @Override
    public IComplex conjugate(){
        return new Complex(real, -imaginary);
    }

    @Override
    public IComplex add(IComplex other){
        return null;
    }

    @Override
    public IComplex subtract(IComplex other){
        return null;
    }

    @Override
    public IComplex multiply(double value){
        return null;
    }

    @Override
    public IComplex multiply(IComplex other){
        return null;
    }

    public IComplex divide(IComplex other){
        return null;
    }

}
