package fr.univartois.butinfo.fractalens.complex;

/**
 * Stores the iteration budget shared by color strategies.
 * @author Maxence Royon (implementation)
 */
public abstract class AbstractColorStrategy {
    /**
     * The maximum number of iterations.
     */
    protected final int maxIteration;

    /**
     * Creates a new instance of {@link AbstractColorStrategy}.
     *
     * @param maxIteration the maximum number of iterations
     */
    protected AbstractColorStrategy(int maxIteration) {
        this.maxIteration = maxIteration;
    }

    /**
     * Gets the maximum number of iterations.
     *
     * @return the maximum number of iterations
     */
    public int getMaxIteration(){
        return maxIteration;
    }
}
