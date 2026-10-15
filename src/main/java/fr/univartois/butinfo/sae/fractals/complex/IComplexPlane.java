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

package fr.univartois.butinfo.sae.fractals.complex;

/**
 * L'interface IComplexPlane permet de faire le lien entre une position sur une image et
 * un nombre dans le plan complexe.
 *
 * @author Romain Wallon
 *
 * @version 0.1.0
 */
public interface IComplexPlane {

    /**
     * Détermine le nombre complexe à la position donnée dans une image.
     *
     * @param row La ligne dans l'image.
     * @param column La colonne dans l'image.
     *
     * @return Le nombre complexe à la position donnée.
     */
    IComplex asComplex(int row, int column);

}
