package Tests;

import DataStructures.BitStructure;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class BitStructureTest {

    @Test
    public void getBitSet() {
    }

    @Test
    public void get() {
    }

    @Test
    public void get1() {
    }

    @Test
    public void set() {
    }

    @Test
    public void set1() {
    }

    @Test
    public void xor() {
    }

    @Test
    public void length() {
    }

    @Test
    public void toArray() {
    }

    @Test
    public void extend() {
        BitStructure bs1 = new BitStructure(new int[]{0, 1});
        BitStructure bs2 = new BitStructure(new int[]{1, 0});
        bs1.extend(bs2);

        assertArrayEquals(
                new int[]{0, 1, 1, 0},
                bs1.toArray()
        );

        bs2 = new BitStructure(0);
        bs1 = new BitStructure(new int[]{1, 0, 1});
        assertEquals(0, bs2.length());
        assertEquals(3, bs1.length());

        bs2.extend(bs1);

        assertArrayEquals(
                bs1.toArray(),
                bs2.toArray()
        );
    }

    @Test
    public void getValue() {
        BitStructure bs1 = new BitStructure(new int[]{0, 1});
        assertEquals(1, bs1.getValue());

        bs1 = new BitStructure(new int[]{1, 0});
        assertEquals(2, bs1.getValue());
    }
}

