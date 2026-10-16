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

package fr.univartois.butinfo.fractalens.generator;

import java.awt.Color;

import fr.univartois.butinfo.fractalens.image.IFractalImage;

/**
 * L'interface IFractalGenerator définit les méthodes nécessaires aux différentes étapes
 * de la génération d'une image de fractale.
 *
 * @author Romain Wallon
 *
 * @version 0.1.0
 */
public interface IFractalGenerator {

    /**
     * Génère l'image de fractale dans sa totalité.
     *
     * @return L'image générée.
     */
    IFractalImage generateImage();

    /**
     * Détermine la couleur d'un pixel donné de l'image.
     * Cette méthode itère sur la suite considérée par ce générateur pour évaluer le
     * nombre d'itérations nécessaires pour décider si la suite diverge ou non.
     *
     * @param row La ligne du pixel.
     * @param column La colonne du pixel.
     *
     * @return La couleur calculée pour le pixel.
     */
    Color computePixelColor(int row, int column);

    /**
     * Sauvegarde l'image générée dans le fichier de sortie.
     */
    void saveImage();

}
