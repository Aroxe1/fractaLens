package fr.univartois.butinfo.fractalens.complex.sequence;

import fr.univartois.butinfo.fractalens.complex.IComplex;
import java.util.function.BinaryOperator;


import java.util.Iterator;

public class GenericComplexSequence implements IComplexSequence{
    /**
     * The function computing the next term from the current term and the constant.
     */
    private final BinaryOperator<IComplex> function;

    /**
     * The current term of the sequence.
     */
    private final IComplex current;

    /**
     * The maximum of iterations.
     */
    private int maxIterations;

    /**
     * Creates a new instance of {@link GenericComplexSequence}.
     *
     * @param function the function computing the next term
     * @param current the first term of the sequence
     * @param maxIterations
     */
    public GenericComplexSequence(BinaryOperator<IComplex> function, IComplex current, int maxIterations){
        this.function = function;
        this.current = current;
        this.maxIterations = maxIterations;
    }


    /**
     * {@inheritDoc}
     * <p>
     * The next term is obtained by applying the function to the previous term
     * and the constant.
     */
    @Override
    public IComplex computeNext(IComplex previous) {
        return function.apply(previous, current);
    }

    /**
     * Gets an iterator over the (infinite) terms of the sequence, starting
     * with the first term.
     *
     * @return an iterator over the terms of the sequence
     */
    @Override
    public Iterator<IComplex> iterator(){
        return new ComplexSequenceIterator(this, current, maxIterations);
    }
}
