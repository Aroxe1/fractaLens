package fr.univartois.butinfo.fractalens.generator;

import fr.univartois.butinfo.fractalens.complex.Complex;
import fr.univartois.butinfo.fractalens.complex.IComplex;

import java.util.Objects;

/**
 * Rotates a complex constant to generate different Julia fractals.
 */
public class RotationFractalGenerator {

    private final IComplex initialconst;

    /**
     * Creates a rotation generator.
     *
     * @param initialconst the initial Julia constant
     */

    public RotationFractalGenerator(IComplex initialconst) {
        this.initialconst = Objects.requireNonNull(initialconst);
    }

    /**
     * Calculates the rotated complex constant.
     *
     * @param angle the rotation angle in radians
     * @return the rotated constant
     */

    public IComplex rotate(double angle){
        IComplex rotation = new Complex(Math.cos(angle), Math.sin(angle));
        return initialconst.multiply(rotation);
    }
}

