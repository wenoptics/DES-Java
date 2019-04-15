package Algorithm;

import DataStructures.BitStructure;
import Util.Utils;

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
    private BitStructure round_f(BitStructure r_32, BitStructure key_48) {
        assert r_32.length() == 32;
        assert key_48.length() == 48;

        BitStructure output;

        // Do the expand permutation
        output = Permutation.doSBoxExpansionPermutation(r_32);  //fixme

        // should be expanded to 48 bits long now (from 32 bits)
        assert output.length() == 48;

        // XOR with the key
        output.xor(key_48);

        // Do SBox
        BitStructure sbox_output = new BitStructure(0);
        for (int i = 0; i < output.length() / 6; i ++) {
            // Do sbox on every 6-bit block
            BitStructure block_6 = output.get(6*i, 6*(i+1));
            // Append to the BitStructure
            sbox_output.extend(SBox.doSBox(i, block_6));
        }

        assert sbox_output.length() == 32;

        // Do the permutation
        output = Permutation.doSBoxPermutation(sbox_output);

        assert output.length() == 32;

        return output;
    }

    /**
     * Do DES encryption.
     *      BitStructure is a encapsulated data structure is that includes BitSet and a length.
     *
     *      BitSet is a good data structure in JAVA to manipulate bit-array. Which support basic operation like AND, OR
     *      and more importantly XOR as well. See https://docs.oracle.com/javase/7/docs/api/java/util/BitSet.html
     *
     * @param inputPlainText The plain text input in 64 bits
     * @param key The key that used in DES (56 bits)
     * @return The cipher text in 64 bits
     */
    public BitStructure encrypt(BitStructure inputPlainText, BitStructure key) {

        assert inputPlainText.length() == 64;
        assert key.length() == 64;

        BitStructure[] roundKeys = new KeyScheduler(key).getEncryptionKeys();

        // Step 1: initial permutation
        BitStructure bs = Permutation.doInitialPermutation(inputPlainText);

        // Split the bits to the left half & the right half
        BitStructure left32, right32;
        int _mid = bs.length() / 2;
        left32 = bs.get(0, _mid);
        right32 = bs.get(_mid + 1, bs.length() - 1);
        assert left32.length() == right32.length();

        // Step 2: Do the 16 rounds of DES
        for (int r = 0; r < 16; r++) {
            // the left 32 bit is the right 32 of last round
            BitStructure tmp_l32 = right32;

            // Apply the f function
            right32 = round_f(right32, roundKeys[r]);

            // XOR left32
            right32.xor(left32);

            left32 = tmp_l32;
        }

        // Step 3: the 32-bit swap
        BitStructure tmpBitSet;
        tmpBitSet = left32; left32 = right32;
        right32 = tmpBitSet;

        // Step 4: Do IP inverse
        left32.extend(right32);
        bs = Permutation.doInitialPermutationInv(left32);

        return bs;



    }
}