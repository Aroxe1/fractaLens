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
     * The complex number added after raising the term to the power.
     */
    private final IComplex constant;

    /**
     * The current term of the sequence.
     */
    private final IComplex current;

    /**
     * Creates a new instance of {@link GenericComplexSequence}.
     *
     * @param function the function computing the next term
     * @param constant the constant used by the function
     * @param current the first term of the sequence
     */
    public GenericComplexSequence(BinaryOperator<IComplex> function, IComplex constant, IComplex current){
        this.function = function;
        this.constant = constant;
        this.current = current;
    }


    /**
     * {@inheritDoc}
     * <p>
     * The next term is obtained by applying the function to the previous term
     * and the constant.
     */
    @Override
    public IComplex computeNext(IComplex previous) {
        return function.apply(previous, constant);
    }

    /**
     * Gets an iterator over the (infinite) terms of the sequence, starting
     * with the first term.
     *
     * @return an iterator over the terms of the sequence
     */
    @Override
    public Iterator<IComplex> iterator(){
        return new Iterator<IComplex>() {

            private IComplex nextTerm = current;

            @Override
            public boolean hasNext() {
                return true;
            }

            @Override
            public IComplex next() {
                IComplex result = nextTerm;
                nextTerm = computeNext(nextTerm);
                return result;
            }
        };
    }
}
