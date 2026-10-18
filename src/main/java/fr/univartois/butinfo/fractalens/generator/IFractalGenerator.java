/*
 * FractaLens - A Fractal Generator Library Developed in Lens.
 * Copyright (c) 2022-2026 Romain Wallon - Université d'Artois.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to
 * deal in the Software without restriction, including without limitation the
 * rights to use, copy, modify, merge, publish, distribute, sublicense, and/or
 * sell copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
 * IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR
 * OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE
 * USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package fr.univartois.butinfo.fractalens.generator;

import java.awt.Color;

import fr.univartois.butinfo.fractalens.image.IFractalImage;

/**
 * The IFractalGenerator interface defines the methods corresponding to the different
 * steps of a fractal generation based on a complex number sequence.
 *
 * @author Romain Wallon
 *
 * @version 0.1.0
 */
public interface IFractalGenerator {

    /**
     * Generates a full fractal image.
     *
     * @return The generated image.
     */
    IFractalImage generateImage();

    /**
     * Computes the color of a given pixel.
     * This method iterates of the sequence considered by this generator to evaluate the
     * number of iterations required to decide whether this sequence diverges.
     *
     * @param row The row of the pixel.
     * @param column The column of the pixel.
     *
     * @return The computed color for the pixel.
     */
    Color computePixelColor(int row, int column);

    /**
     * Saves the generated image in the output file.
     */
    void saveImage();

}
