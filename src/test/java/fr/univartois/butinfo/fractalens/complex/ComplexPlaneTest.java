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

package fr.univartois.butinfo.fractalens.complex;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * La classe ComplexPlaneTest permet de tester votre implémentation de la classe
 * {@link IComplexPlane}.
 *
 * @author Romain Wallon
 *
 * @version 0.1.0
 */
class ComplexPlaneTest {

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
     * Crée un nouveau plan complexe.
     *
     * @param height La hauteur du plan complexe.
     * @param width La largeur du plan complexe.
     *
     * @return Le plan complexe créé.
     */
    private static IComplexPlane createPlane(int height, int width) {
        // TODO Créez ici une instance de votre classe implémentant IComplexPlane.
        return null;
    }

    /**
     * Méthode de test pour
     * {@link fr.univartois.butinfo.fractalens.complex.IComplexPlane#asComplex(int, int)}.
     */
    @Test
    void testAsComplex() {
        IComplexPlane plane = createPlane(600, 800);

        IComplex topLeft = createComplex(-400, 300);
        assertEquals(topLeft, plane.asComplex(0, 0));

        IComplex top = createComplex(0, 300);
        assertEquals(top, plane.asComplex(0, 400));

        IComplex topRight = createComplex(399, 300);
        assertEquals(topRight, plane.asComplex(0, 799));

        IComplex right = createComplex(-400, 0);
        assertEquals(right, plane.asComplex(300, 0));

        IComplex zero = createComplex(0, 0);
        assertEquals(zero, plane.asComplex(300, 400));

        IComplex left = createComplex(399, 0);
        assertEquals(left, plane.asComplex(300, 799));

        IComplex bottomLeft = createComplex(-400, -299);
        assertEquals(bottomLeft, plane.asComplex(599, 0));

        IComplex bottom = createComplex(0, -299);
        assertEquals(bottom, plane.asComplex(599, 400));

        IComplex bottomRight = createComplex(399, -299);
        assertEquals(bottomRight, plane.asComplex(599, 799));
    }

}
