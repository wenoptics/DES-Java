package Algorithm;

import java.util.BitSet;

public class DES {

    public DES() {

    }

    /**
     *  The `f` function within each round
     *
     * @param r_32 the 32 bit input
     * @param key_48 the 48 bit round key
     * @return 32-bit BitSet output
     */
    private BitSet round_f(BitSet r_32, BitSet key_48) {
        assert r_32.length() == 32;
        assert key_48.length() == 48;

        BitSet output;

        // Do the expand permutation
        output = Permutation.doSBoxExpansionPermutation();

        // should be expanded to 48 bits long now (from 32 bits)
        assert output.length() == 48;

        // XOR with the key
        output.xor(key_48);

        // Do SBox
        BitSet sbox_output = new BitSet();
        for (int i = 0; i < output.length() / 6; i ++) {
            // Do sbox on every 6-bit block
            BitSet block_6 = output.get(6*i, 6*(i+1));
            sbox_output += SBox.doSBox(i, block_6);  // fixme Append to the bitSet
        }

        assert sbox_output.length() == 32;

        // Do the permutation
        output = Permutation.doSBoxPermutation(sbox_output);

        assert output.length() == 32;

        return output;
    }

    /**
     * Do DES encryption.
     *      BitSet is a good data structure in JAVA to manipulate bit-array. Which support basic operation like AND, OR
     *      and more importantly XOR as well. See https://docs.oracle.com/javase/7/docs/api/java/util/BitSet.html
     *
     * @param inputPlainText The plain text input in 64 bits
     * @param key The key that used in DES (56 bits)
     * @return The cipher text in 64 bits
     */
    public BitSet encrypt(BitSet inputPlainText, BitSet key) {

        assert inputPlainText.length() == 64;
        assert key.length() == 64;

        BitSet[] roundKeys = new KeyScheduler(key).getEncryptionKeys();

        // Step 1: initial permutation
        BitSet bitSet = Permutation.doInitialPermutation(inputPlainText);

        // Split the bits to the left half & the right half
        BitSet left32, right32;
        int _mid = bitSet.length() / 2;
        left32 = bitSet.get(0, _mid);
        right32 = bitSet.get(_mid + 1, bitSet.length() - 1);
        assert left32.length() == right32.length();

        // Step 2: Do the 16 rounds of DES
        for (int r = 0; r < 16; r++) {
            // the left 32 bit is the right 32 of last round
            BitSet tmp_l32 = right32;

            // Apply the f function
            right32 = round_f(right32, roundKeys[r]);

            // XOR left32
            right32.xor(left32);

            left32 = tmp_l32;
        }

        // Step 3: the 32-bit swap
        BitSet tmpBitSet;
        tmpBitSet = left32; left32 = right32;
        right32 = tmpBitSet;

        // Step 4: Do IP inverse
        bitSet = Permutation.doInitialPermutationInv(left32 + right32); // fixme

        return bitSet;



    }
}