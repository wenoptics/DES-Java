package DataStructures;

import Util.Utils;

import java.util.BitSet;

public class BitStructure {
    private BitSet bitSet;
    private int n_bits = 0;

    public BitStructure(int n_bits) {
        bitSet = new BitSet(n_bits);
        this.n_bits = n_bits;
    }

    public BitStructure(int[] arr) {
        this.bitSet = Utils.loadFromArray(arr);
        this.n_bits = arr.length;
    }

    public BitStructure(boolean[] arr) {
        this.bitSet = Utils.loadFromArray(arr);
        this.n_bits = arr.length;
    }

    public BitStructure(BitSet bs, int n_bits) {
        this.bitSet = bs;
        this.n_bits = n_bits;
    }

    public BitStructure(long value, int n_bits) {
        this.bitSet = Utils.long2bs(value);
        this.n_bits = n_bits;
    }

    public BitStructure(byte[] input) {
        this.bitSet = new BitSet(0);
        for (byte b : input) {
            this.extend(new BitStructure(b, 8));
        }
        assert this.length() == input.length * 8;
    }

    public BitStructure(byte[] input, int n_bits) {
        this.bitSet = new BitSet(0);
        for (byte b : input) {
            this.extend(new BitStructure(b, 8));
        }
        this.n_bits = n_bits;
    }

    public BitSet getBitSet() {
        return this.bitSet;
    }

    public boolean get(int bit) {
        assert bit < this.length();
        return bitSet.get(bit);
    }

    public BitStructure get(int fromBit, int toBit) {
        assert fromBit < this.length();
        assert toBit <= this.length();
        return new BitStructure(bitSet.get(fromBit, toBit), toBit - fromBit);
    }

    public BitStructure getNoBound(int fromBit, int toBit) {
        return new BitStructure(bitSet.get(fromBit, toBit), toBit - fromBit);
    }

    public void set(int bit, boolean value) {
        assert bit < this.length();
        bitSet.set(bit, value);
    }

    public void set(int bit) {
        assert bit < this.length();
        bitSet.set(bit);
    }

    public void xor(BitStructure bs) {
        bitSet.xor(bs.bitSet);
    }

    public int length() {
        return this.n_bits;
    }

    private static int bin2int(boolean input) {
        return input == true ? 1 : 0;
    }

    public int[] toArray() {
        int[] arr = new int[this.n_bits];
        for (int i = 0; i < this.n_bits; i++) {
            arr[i] = bin2int(this.bitSet.get(i));
        }
        return arr;
    }

    public void extend(BitStructure bs) {
        this.bitSet = Utils.concat(this.bitSet, this.n_bits, bs.getBitSet(), bs.length());
        this.n_bits += bs.length();
    }

    public int getValue() {
        return Utils.bin2int(this.toArray());
    }

    public byte[] getBytes() {
        int len = length() / 8;
        if (length() % 8 != 0) {
            len++;
        }

        byte[] output = new byte[len];
        for (int i = 0; i < len; i++) {
            output[i] = (byte) getNoBound(8*i, 8*(i+1)).getValue();
        }

        return output;
    }


}
