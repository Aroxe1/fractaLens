package fr.univartois.butinfo.fractalens.complex;

import java.util.Objects;

/**
 * Represents a complex number composed of a real part and an imaginary part.
 */
public class Complex implements IComplex{
    private double real;
    private double imaginary;

    /**
     * Creates a complex number.
     *
     * @param real real part
     * @param imaginary imaginary part
     */
    public Complex(double real, double imaginary){
        this.real = real;
        this.imaginary = imaginary;
    }

    /**
     * Returns the real part of the complex number.
     *
     * @return real part
     */
    public double getRealPart(){
        return real;
    }

    /**
     * Returns the imaginary part of the complex number.
     *
     * @return imaginary part
     */
    public double getImaginaryPart(){
        return imaginary;
    }

    /**
     * Calculates the absolute value (modulus) of the complex number.
     *
     * @return modulus of the complex number
     */
    @Override
    public double abs(){
        return Math.sqrt(real * real + imaginary * imaginary);
    }

    /**
     * Returns the opposite of the complex number.
     *
     * @return opposite of the complex number
     */
    @Override
    public IComplex negate(){
        return new Complex(-real, -imaginary);
    }

    /**
     * Returns the complex conjugate.
     *
     * @return complex conjugate
     */
    @Override
    public IComplex conjugate(){
        return new Complex(real, -imaginary);
    }

    /**
     * Adds another complex number to this one.
     *
     * @param other complex number to add
     * @return result of the addition
     */
    @Override
    public IComplex add(IComplex other){
        double re = real + other.getRealPart();
        double im = imaginary + other.getImaginaryPart();
        return new Complex(re, im);
    }

    /**
     * Subtracts another complex number from this one.
     *
     * @param other complex number to subtract
     * @return result of the subtraction
     */
    @Override
    public IComplex subtract(IComplex other){
        double re = real - other.getRealPart();
        double im = imaginary - other.getImaginaryPart();
        return new Complex(re, im);
    }

    /**
     * Multiplies this complex number by a real value.
     *
     * @param value real multiplier
     * @return result of the multiplication
     */
    @Override
    public IComplex multiply(double value){
        double re = real * value;
        double im = imaginary * value;
        return new Complex(re, im);
    }

    /**
     * Multiplies this complex number by another complex number.
     *
     * @param other complex number multiplier
     * @return result of the multiplication
     */
    @Override
    public IComplex multiply(IComplex other){
        double c = other.getRealPart();
        double d = other.getImaginaryPart();
        double re = real * c - imaginary * d;
        double im = real * d + imaginary * c;
        return new Complex(re, im);
    }

    /**
     * Divides this complex number by another complex number.
     *
     * @param other complex number divisor
     * @return result of the division
     */
    public IComplex divide(IComplex other){
        double c = other.getRealPart();
        double d = other.getImaginaryPart();
        double denominator = c * c + d * d;
        return multiply(other.conjugate()).multiply(1 / denominator);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The power is computed by multiplying this complex number by itself
     * {@code exponent} times, starting from 1.
     *
     * @throws IllegalArgumentException if the exponent is negative
     */
    @Override
    public IComplex pow(int exponent){
        if(exponent < 0){
            throw new IllegalArgumentException("The exponent must not be negative");
        }
        IComplex result = new Complex(1,0);
        for(int i = 0; i<exponent; i++){
            result = result.multiply(this);
        }
        return result;
    }

    /**
     * Checks whether this object is equal to another complex number.
     *
     * @param obj object to compare
     * @return true if the real and imaginary parts are equal, false otherwise
     */
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



    /**
     * Calculates the hash code of the complex number.
     *
     * @return hash code
     */
    @Override
    public int hashCode() {
        return Objects.hash(real + 0.0, imaginary + 0.0);
    }

    /**
     * Returns a textual representation of the complex number.
     *
     * @return complex number in the form a + bi or a - bi
     */
    @Override
    public String toString(){
        if(imaginary < 0){
            return real + " - " + (-imaginary) + "i";
        }
        return real + " + " + imaginary + "i";
    }

}