package Util;

import java.util.BitSet;

public class Utils {
    public static int bin2int(boolean input) {
        return input == true ? 1 : 0;
    }

    public static boolean int2bin(int i) {
        return i != 0;
    }

    public static int bin2int(boolean[] input) {
        int acc = 0;
        for (int i=0; i<input.length; i++) {
            acc += bin2int(input[i]) * (1 << i);
        }
        return acc;
    }

    public static int bin2int(int[] input) {
        int acc = 0;
        for (int i=0; i<input.length; i++) {
            acc += input[i] * (1 << i);
        }
        return acc;
    }

    public static BitSet concat(BitSet bs1, int len1, BitSet bs2, int len2) {
        BitSet bs = new BitSet();
        for(int i=0; i < len1 + len2; i++) {
            boolean val = i < len1? bs1.get(i) : bs2.get(i-len1);
            bs.set(i, val);
        }
        return bs;
    }

    public static BitSet loadFromArray(int[] arr) {
        BitSet bs = new BitSet(arr.length);
        for (int i = 0; i < arr.length; i++) {
            bs.set(i, int2bin(arr[i]));
        }
        return bs;
    }

    public static BitSet loadFromArray(boolean[] arr) {
        BitSet bs = new BitSet(arr.length);
        for (int i = 0; i < arr.length; i++) {
            bs.set(i, arr[i]);
        }
        return bs;
    }

    public static int[] dumpArray(BitSet bs, int length) {
        int[] arr = new int[length];
        for (int i = 0; i < bs.length(); i++) {
            arr[i] = bin2int(bs.get(i));
        }
        return arr;
    }

    public static BitSet long2bs(long value) {
        BitSet bits = new BitSet();
        int index = 0;
        while (value != 0L) {
            if (value % 2L != 0) {
                bits.set(index);
            }
            ++index;
            value = value >>> 1;
        }
        return bits;
    }
}
