package fr.univartois.butinfo.fractalens.complex.sequence;

import fr.univartois.butinfo.fractalens.complex.IComplex;

import java.util.Iterator;

public class JuliaSequence implements  IComplexSequence
{
    private IComplex z0;
    private IComplex c;
    private int maxIterations;

    public JuliaSequence(IComplex z0, IComplex c, int maxIterations) {
        this.z0 = z0;
        this.c = c;
        this.maxIterations = maxIterations;
    }

    @Override
    public IComplex computeNext(IComplex previous) {
        return previous.multiply(previous).add(c);
    }

    @Override
    public Iterator<IComplex> iterator() {
        return new ComplexSequenceIterator(this, z0, maxIterations);
/*        return new Iterator<IComplex>() {
            private IComplex actual = z0;
            private int iteration = 0;

            @Override
            public boolean hasNext() {
                return iteration < maxIterations && actual.abs() <= 2;
            }
            @Override
            public IComplex next() {

                IComplex result = actual;
                actual = computeNext(actual);
                iteration++;

                return result;
            }
        };*/
    }

}
