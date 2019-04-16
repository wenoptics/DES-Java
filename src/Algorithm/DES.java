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
    public static BitStructure round_f(BitStructure r_32, BitStructure key_48) {
        assert r_32.length() == 32;
        assert key_48.length() == 48;

        BitStructure output;

        // Do the expand permutation
        output = Permutation.doSBoxExpansionPermutation(r_32);

        // should be expanded to 48 bits long now (from 32 bits)
        assert output.length() == 48;

        // XOR with the key
        output.xor(key_48);

        // Do SBox
        BitStructure sbox_output = new BitStructure(0);
        for (int i = 0; i < output.length() / 6; i ++) {
            // Do sbox on every 6-bit block
            BitStructure block_6 = output.get(6*i, 6*(i+1));
            //assert block_6.length() == 6;

            BitStructure b4 = SBox.doSBox(i, block_6);
            //assert b4.length() == 4;

            // Append to the BitStructure
            sbox_output.extend(b4);
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
     * @param bitStructure64 The input in 64 bits
     * @param roundKeys The keys for each round
     * @return The cipher text in 64 bits
     */
    public static BitStructure procedure(BitStructure bitStructure64, BitStructure[] roundKeys) {
        assert bitStructure64.length() == 64;
        assert roundKeys.length == 16;

        // Step 1: initial permutation
        BitStructure bs = Permutation.doInitialPermutation(bitStructure64);

        // Split the bits to the left half & the right half
        BitStructure left32, right32;
        int _mid = bs.length() / 2;
        left32 = bs.get(0, _mid);
        right32 = bs.get(_mid, bs.length());
        assert left32.length() == right32.length();

        // Step 2: Do the 16 rounds of DES
        for (int r = 0; r < 16; r++) {
            // the left 32 bit is the right 32 of last round
            BitStructure tmp_l32 = new BitStructure(right32.toArray());

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

    public static BitStructure encrypt(BitStructure inputPlainText, BitStructure key) {

        assert key.length() == 64;
        BitStructure[] roundKeys = new KeyScheduler(key).getEncryptionKeys();
        return procedure(inputPlainText, roundKeys);

    }

    public static BitStructure decrypt(BitStructure cipherText, BitStructure key) {

        assert key.length() == 64;
        BitStructure[] roundKeys = new KeyScheduler(key).getDecryptionKeys();
        return procedure(cipherText, roundKeys);

    }
}