package Tests;

import Algorithm.SBox;
import DataStructures.BitStructure;
import org.junit.Test;

import static org.junit.Assert.*;

public class SBoxTest {

    @Test
    public void doSBox() {
        assertEquals(14,
                SBox.doSBox(2,
                        new BitStructure(
                                new int[]{1, 1, 1, 0, 1, 0})).getValue());


        // assertArrayEquals(
        //         new int[] {1,0,1,0},
        //         SBox.doSBox(0,
        //                 new BitStructure(
        //                         new int[]{0,1,1,0,1,1})).toArray());

    }
}