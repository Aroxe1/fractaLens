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
        double re = real + other.getRealPart();
        double im = imaginary + other.getImaginaryPart();
        return new Complex(re, im);
    }

    @Override
    public IComplex subtract(IComplex other){
        double re = real - other.getRealPart();
        double im = imaginary - other.getImaginaryPart();
        return new Complex(re, im);
    }

    @Override
    public IComplex multiply(double value){
        double re = real * value;
        double im = imaginary * value;
        return new Complex(re, im);
    }

    @Override
    public IComplex multiply(IComplex other){
        double c = real * other.getRealPart();
        double d = imaginary * other.getImaginaryPart();
        double re = real * c - imaginary * d;
        double im = real * d + imaginary * c;
        return new Complex(re, im);
    }

    public IComplex divide(IComplex other){
        double c = other.getRealPart();
        double d = other.getImaginaryPart();
        double denominator = c * c + d * d;
        return multiply(other.conjugate()).multiply(1 / denominator);
    }

}
