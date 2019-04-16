package Tests;

import Algorithm.KeyScheduler;
import DataStructures.BitStructure;
import org.junit.Test;

import static org.junit.Assert.*;

public class KeySchedulerTest {

    @Test
    public void rotateLeft() {
        assertArrayEquals(
                new int[] { 0, 1, 1, 0, 0, 1, },
                KeyScheduler.rotateLeft(new BitStructure(new int[]{0, 1, 0, 1, 1, 0}), 2).toArray()
        );
    }


    @Test
    public void getEncryptionKeys() {
        KeyScheduler ks = new KeyScheduler(new BitStructure(
                new int[]{0, 0, 0, 1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 1, 0, 0, 0, 1, 0, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 0, 0, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 1}));

        assertArrayEquals(new int[]{
                0,0,0,1,1,0,1,1,0,0,0,0,0,0,1,0,1,1,1,0,1,1,1,1,1,1,1,1,1,1,0,0,0,1,1,1,0,0,0,0,0,1,1,1,0,0,1,0,
        },ks.getEncryptionKeys()[0].toArray());

        assertArrayEquals(new int[]{
                0,1,1,1,1,0,0,1,1,0,1,0,1,1,1,0,1,1,0,1,1,0,0,1,1,1,0,1,1,0,1,1,1,1,0,0,1,0,0,1,1,1,1,0,0,1,0,1,
        },ks.getEncryptionKeys()[1].toArray());

        assertArrayEquals(new int[]{
                0,1,0,1,0,1,0,1,1,1,1,1,1,1,0,0,1,0,0,0,1,0,1,0,0,1,0,0,0,0,1,0,1,1,0,0,1,1,1,1,1,0,0,1,1,0,0,1,
        },ks.getEncryptionKeys()[2].toArray());

        assertArrayEquals(new int[]{
                0,1,1,1,0,0,1,0,1,0,1,0,1,1,0,1,1,1,0,1,0,1,1,0,1,1,0,1,1,0,1,1,0,0,1,1,0,1,0,1,0,0,0,1,1,1,0,1,
        },ks.getEncryptionKeys()[3].toArray());

        assertArrayEquals(new int[]{
                0,1,1,1,1,1,0,0,1,1,1,0,1,1,0,0,0,0,0,0,0,1,1,1,1,1,1,0,1,0,1,1,0,1,0,1,0,0,1,1,1,0,1,0,1,0,0,0,
        },ks.getEncryptionKeys()[4].toArray());
    }

    @Test
    public void getEncryptionKeys2() {
        KeyScheduler ks = new KeyScheduler(new BitStructure(
                new int[]{0, 0, 0, 1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 1, 0, 0, 0, 1, 0, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 0, 0, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 1}));

        assertArrayEquals(new int[]{
                0,1,0,1,0,1,0,1,1,1,1,1,1,1,0,0,1,0,0,0,1,0,1,0,0,1,0,0,0,0,1,0,1,1,0,0,1,1,1,1,1,0,0,1,1,0,0,1,
        },ks.getEncryptionKeys()[2].toArray());
    }

    @Test
    public void getDncryptionKeys() {
        KeyScheduler ks = new KeyScheduler(new BitStructure(
                new int[]{0, 0, 0, 1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 1, 0, 0, 0, 1, 0, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 0, 0, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 1}));

        assertArrayEquals(ks.getDecryptionKeys()[0].toArray(), ks.getEncryptionKeys()[15].toArray());
    }
}