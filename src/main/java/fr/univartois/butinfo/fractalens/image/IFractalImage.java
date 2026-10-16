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

package fr.univartois.butinfo.fractalens.image;

import java.awt.Color;
import java.io.IOException;

/**
 * L'interface IFractalImage fournit les méthodes permettant de manipuler l'image sur
 * laquelle représenter des fractales.
 *
 * @author Romain Wallon
 *
 * @version 0.1.0
 */
public interface IFractalImage {

    /**
     * Donne la hauteur de cette image, mesurée en pixels.
     *
     * @return La hauteur de cette image.
     */
    int getHeight();

    /**
     * Donne la largeur de cette image, mesurée en pixels.
     *
     * @return La largeur de cette image.
     */
    int getWidth();

    /**
     * Met à jour la couleur du pixel à la position donnée.
     *
     * @param row La ligne du pixel à mettre à jour.
     * @param column La couleur du pixel à mettre à jour.
     * @param color La nouvelle couleur du pixel.
     */
    void setColor(int row, int column, Color color);

    /**
     * Sauvegarde cette image dans un fichier.
     *
     * @param path Le chemin du fichier où sauvegarder l'image.
     *
     * @throws IOException Si une erreur se produit lors de l'écriture.
     */
    void saveAs(String path) throws IOException;

}
