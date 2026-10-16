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

import fr.univartois.butinfo.fractalens.complex.IComplex;

/**
 * L'interface IComplexSequence définit les opérations permettant d'itérer sur les termes
 * d'une suite de nombres complexes.
 *
 * @author Romain Wallon
 *
 * @version 0.1.0
 */
public interface IComplexSequence extends Iterable<IComplex> {

    /**
     * Calcule la prochaine valeur de la suite à partir de la valeur précédente.
     *
     * @param previous La valeur précédente de la suite.
     *
     * @return La prochaine valeur de la suite.
     */
    IComplex computeNext(IComplex previous);

}
