package fr.univartois.butinfo.fractalens.complex;

public class ScaledComplexPlane implements IComplexPlane{
    /**
     * Le plan complexe.
     */
    private final IComplexPlane plane;
    /**
     * Le facteur de zoom appliqué aux nombres complexes du plan.
     */
    private final double zoom;

    /**
     * Crée une nouvelle instance de {@link ScaledComplexPlane}.
     *
     * @param plane Le plan complexe à décorer.
     * @param zoom Le facteur de zoom par lequel multiplier les nombres complexes.
     */
    public ScaledComplexPlane(IComplexPlane plane, double zoom){
        this.plane = plane;
        this.zoom = zoom;
    }

    /**
     * Le nombre complexe obtenu depuis le plan est multiplié par le
     * facteur de zoom.
     *
     * @param row La ligne du point dans le plan.
     * @param column La colonne du point dans le plan.
     *
     * @return Le nombre complexe correspondant, mis à l'échelle par le facteur de zoom.
     */
    @Override
    public IComplex asComplex(int row, int column) {
        IComplex c = plane.asComplex(row, column);
        return c.multiply(zoom);
    }
}
