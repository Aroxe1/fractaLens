package fr.univartois.butinfo.fractalens.complex;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.awt.Color;
import java.util.List;

import org.junit.jupiter.api.Test;

/**
 * Tests color selection from a fixed, repeating palette.
 */
class FixedColorStrategyTest {

    private final FixedColorStrategy strategy =
            new FixedColorStrategy(List.of(Color.RED, Color.GREEN, Color.BLUE));

    /**
     * Checks that iteration counts select colors by their palette index.
     */
    @Test
    void testSelectsPaletteColorByIteration() {
        assertEquals(Color.RED, strategy.getColor(0));
        assertEquals(Color.GREEN, strategy.getColor(1));
        assertEquals(Color.BLUE, strategy.getColor(2));
    }

    /**
     * Checks that the palette repeats after its last color.
     */
    @Test
    void testPaletteRepeatsAfterLastColor() {
        assertEquals(Color.RED, strategy.getColor(3));
        assertEquals(Color.GREEN, strategy.getColor(4));
    }

    /**
     * Checks that negative iteration counts also select a palette color safely.
     */
    @Test
    void testNegativeIterationUsesFirstColor() {
        assertEquals(Color.RED, strategy.getColor(-1));
    }

    /**
     * Checks that an empty palette is rejected.
     */
    @Test
    void testEmptyPaletteIsRejected() {
        assertThrows(IllegalArgumentException.class,
                () -> new FixedColorStrategy(List.of()));
    }
}
