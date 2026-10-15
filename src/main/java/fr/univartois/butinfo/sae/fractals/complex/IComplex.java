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
 * L'interface IComplex définit les méthodes nécessaires pour la manipulation
 * d'un nombre complexe.
 *
 * @author Romain Wallon
 *
 * @version 0.1.0
 */
public interface IComplex {

    /**
     * Donne la partie réelle de ce nombre complexe.
     *
     * @return La partie réelle de ce nombre complexe.
     */
    double getRealPart();

    /**
     * Donne la partie imaginaire de ce nombre complexe.
     *
     * @return La partie imaginaire de ce nombre complexe.
     */
    double getImaginaryPart();

    /**
     * Donne le module de ce nombre complexe.
     *
     * @return Le module de ce nombre complexe.
     */
    double abs();

    /**
     * Donne l'opposé de ce nombre complexe.
     *
     * @return L'opposé de ce nombre complexe.
     */
    IComplex negate();

    /**
     * Donne le conjugué de ce nombre complexe.
     *
     * @return Le conjugué de ce nombre complexe.
     */
    IComplex conjugate();

    /**
     * Ajoute ce nombre complexe à un autre nombre complexe.
     *
     * @param other Le nombre complexe à ajouter à ce nombre.
     *
     * @return Le résultat de {@code this + other}.
     */
    IComplex add(IComplex other);

    /**
     * Soustrait un nombre complexe de ce nombre complexe.
     *
     * @param other Le nombre complexe à soustraire de ce nombre.
     *
     * @return Le résultat de {@code this - other}.
     */
    IComplex subtract(IComplex other);

    /**
     * Multiplie ce nombre complexe par une valeur réelle.
     *
     * @param value La valeur par laquelle multiplier ce nombre.
     *
     * @return Le résultat de {@code value * this}.
     */
    IComplex multiply(double value);

    /**
     * Multiplie ce nombre complexe par un autre nombre complexe.
     *
     * @param other Le nombre complexe à mulitplier par ce nombre.
     *
     * @return Le résultat de {@code this * other}.
     */
    IComplex multiply(IComplex other);

    /**
     * Divise ce nombre complexe par un autre nombre complexe.
     *
     * @param other Le nombre complexe qui divise ce nombre.
     *
     * @return Le résultat de {@code this / other}.
     */
    IComplex divide(IComplex other);

}
