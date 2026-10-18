/*
 * Ce logiciel est distribué à des fins éducatives.
 *
 * Il est fourni "tel quel", sans garantie d'aucune sorte, explicite
 * ou implicite, notamment sans garantie de qualité marchande, d'adéquation
 * à un usage particulier et d'absence de contrefaçon.
 * En aucun cas, les auteurs ou titulaires du droit d'auteur ne seront
 * responsables de tout dommage, réclamation ou autre responsabilité, que ce
 * soit dans le cadre d'un contrat, d'un délit ou autre, en provenance de,
 * consécutif à ou en relation avec le logiciel ou son utilisation, ou avec
 * d'autres éléments du logiciel.
 *
 * (c) 2022-2026 Romain Wallon - Université d'Artois.
 * Tous droits réservés.
 */

package fr.univartois.butinfo.fractalens.complex.sequence;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import fr.univartois.butinfo.fractalens.complex.IComplex;

/**
 * La classe ComplexSequenceTest permet de tester votre implémentation de l'interface
 * {@link IComplexSequence} avec la suite de Julia.
 *
 * @author Romain Wallon
 *
 * @version 0.1.0
 */
class ComplexSequenceTest {

    /**
     * Crée un nouveau nombre complexe.
     *
     * @param realPart La partie réelle du nombre complexe.
     * @param imaginaryPart La partie imaginaire du nombre complexe.
     *
     * @return Le nombre complexe créé.
     */
    private static IComplex createComplex(double realPart, double imaginaryPart) {
        // TODO Créez ici une instance de votre classe implémentant IComplex.
        return null;
    }

    /**
     * Crée une nouvelle suite de Julia.
     *
     * @param first La valeur du premier terme de la suite.
     * @param constant La valeur de la constante dans l'expression définissant la suite de
     *        Julia.
     * @param nbTerms Le nombre de termes de la suite à considérer.
     *
     * @return La suite de Julia créée
     */
    private static IComplexSequence createJuliaSequence(
            IComplex first, IComplex constant, int nbTerms) {
        // TODO Créez ici une instance de votre classe implémentant IComplexSequence pour la suite de Juia.
        return null;
    }

    /**
     * Méthode de test pour
     * {@link fr.univartois.butinfo.fractalens.complex.sequence.IComplexSequence#computeNext(fr.univartois.butinfo.fractalens.complex.IComplex)}.
     */
    @Test
    void testComputeNext() {
        IComplex z0 = createComplex(0.5, 0);
        IComplex c = createComplex(0, 0);
        IComplexSequence julia = createJuliaSequence(z0, c, 50);

        IComplex z1 = createComplex(0.25, 0);
        assertEquals(z1, julia.computeNext(z0));

        IComplex z2 = createComplex(0.0625, 0);
        assertEquals(z2, julia.computeNext(z1));

        IComplex z3 = createComplex(0.00390625, 0);
        assertEquals(z3, julia.computeNext(z2));
    }

    /**
     * Méthode de test pour
     * {@link fr.univartois.butinfo.fractalens.complex.sequence.IComplexSequence#iterator()}
     * dans le cas d'une convergence de la suite.
     */
    @Test
    void testIteratorOnConvergence() {
        IComplex z0 = createComplex(0.5, 0);
        IComplex c = createComplex(0, 0);
        int nbTerms = 50;

        IComplexSequence julia = createJuliaSequence(z0, c, nbTerms);
        int nbIterations = 0;

        for (var _ : julia) {
            nbIterations++;
        }

        assertEquals(nbTerms, nbIterations);
    }

    /**
     * Méthode de test pour
     * {@link fr.univartois.butinfo.fractalens.complex.sequence.IComplexSequence#iterator()}
     * dans le cas d'une divergence de la suite.
     */
    @Test
    void testIteratorOnDivergence() {
        IComplex z0 = createComplex(2, 0);
        IComplex c = createComplex(0.5, 0.5);
        int nbTerms = 50;

        IComplexSequence julia = createJuliaSequence(z0, c, nbTerms);
        int nbIterations = 0;

        for (var _ : julia) {
            nbIterations++;
        }

        assertTrue(nbTerms > nbIterations);
    }

}
