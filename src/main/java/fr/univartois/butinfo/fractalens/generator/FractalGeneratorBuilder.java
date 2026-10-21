package fr.univartois.butinfo.fractalens.generator;

import java.util.Iterator;

import fr.univartois.butinfo.fractalens.complex.IComplex;


public class FractalGeneratorBuilder {
    private int width;
    private int height;
    private int maxIterations;
    private IComplex c;

    public FractalGeneratorBuilder setWidth(int width) {
        this.width = width;
        return this;
    }

    public FractalGeneratorBuilder setHeight(int height) {
        this.height = height;
        return this;
    }

    public FractalGeneratorBuilder setMaxIterations(int maxIterations) {
        this.maxIterations = maxIterations;
        return this;
    }

    public FractalGeneratorBuilder setC(IComplex c) {
        this.c = c;
        return this;
    }

    public FractalGenerator build() {
        return new FractalGenerator(width, height, maxIterations, c);
    }
}
