package Tests;

import org.junit.Test;

import java.util.BitSet;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class BitSetTest {

    @Test
    public void testAnd() {
        BitSet bs1 = new BitSet();
        // 0b1011
        bs1.set(0);
        bs1.set(1);
        bs1.set(3);

        BitSet bs2 = new BitSet();
        // 0b1010
        bs2.set(1);
        bs2.set(3);

        BitSet bs3 = new BitSet();
        //      0b1010
        // xor
        //      0b1011
        // -----------
        //        0001
        bs3.set(0);

        bs1.xor(bs2);
        assertEquals(bs1, bs3);
    }

    @Test
    public void testDump() {
        BitSet bs1 = new BitSet();
        // 0b1101
        bs1.set(0);
        bs1.set(2);
        bs1.set(3);

        assertArrayEquals(bs1.toLongArray(), new long[] {13});
    }

    @Test
    public void testGetBit() {
        BitSet bs1 = new BitSet();
        // 0b1101
        bs1.set(0);
        bs1.set(2);
        bs1.set(3);

        assertEquals(bs1.get(0), true);
        assertEquals(bs1.get(1), false);
        assertEquals(bs1.get(2), true);
        assertEquals(bs1.get(3), true);
    }
}
