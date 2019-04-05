package Algorithm;

import java.util.BitSet;
import javax.crypto.spec.SecretKeySpec;

public class DES {

    public DES() {

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
            // todo
            // todo Apply the SBox
            // todo XOR left32
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