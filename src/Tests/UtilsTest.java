package Tests;

import Util.Utils;
import org.junit.Test;

import java.util.BitSet;

import static org.junit.Assert.*;

public class UtilsTest {

    @Test
    public void bin2int() {
        assertEquals(0, Utils.bin2int(false));
        assertEquals(1, Utils.bin2int(true));
    }

    @Test
    public void bin2int1() {
        assertEquals(1, Utils.bin2int(new boolean[] {true, false, false}));
        assertEquals(0b1010, Utils.bin2int(new boolean[] {false, true, false, true}));
    }

    @Test
    public void concat() {
        assertArrayEquals(
                new int[]{0, 1, 1, 0},
                Utils.dumpArray(Utils.concat(
                        Utils.loadFromArray(new int[]{0, 1}), 2,
                        Utils.loadFromArray(new int[]{1, 0}), 2), 4)
        );
    }

    @Test
    public void int2bin() {
    }

    @Test
    public void loadFromArray() {
        assertEquals(Utils.loadFromArray(new int[]{0, 1}).length(), 2);
        assertEquals(Utils.loadFromArray(new int[]{1, 1}).length(), 2);
        assertEquals(true, Utils.loadFromArray(new int[]{1, 1}).get(0));
        assertEquals(false, Utils.loadFromArray(new int[]{0, 1}).get(0));
    }

    @Test
    public void dumpArray() {
        BitSet bs = new BitSet(3);
        bs.set(0, true);
        bs.set(1, false);
        bs.set(2, false);
        assertArrayEquals(new int[] {1, 0, 0}, Utils.dumpArray(bs, 3));
    }
}