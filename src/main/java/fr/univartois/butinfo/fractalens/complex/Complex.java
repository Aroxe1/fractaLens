package fr.univartois.butinfo.fractalens.complex;

import java.util.Objects;

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
        double c = other.getRealPart();
        double d = other.getImaginaryPart();
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

    @Override
    public boolean equals(Object obj){
        if (this == obj){
            return true;
        }
        if(!(obj instanceof IComplex)){
            return false;
        }
        IComplex other = (IComplex) obj;
        return real == other.getRealPart() && imaginary == other.getImaginaryPart();
    }

    @Override
    public int hashCode() {
        return Objects.hash(real + 0.0, imaginary + 0.0);
    }

    @Override
    public String toString(){
        if(imaginary < 0){
            return real + " - " + (-imaginary) + "i";
        }
        return real + " + " + imaginary + "i";
    }

}
