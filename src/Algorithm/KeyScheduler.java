package Algorithm;

import DataStructures.BitStructure;




public class KeyScheduler {

	//Permuted Choice One
    public static final int[] PC1 = {
            57, 49, 41, 33, 25, 17, 9,
            1,  58, 50, 42, 34, 26, 18,
            10, 2,  59, 51, 43, 35, 27,
            19, 11, 3,  60, 52, 44, 36,
            63, 55, 47, 39, 31, 23, 15,
            7,  62, 54, 46, 38, 30, 22,
            14, 6,  61, 53, 45, 37, 29,
            21, 13, 5,  28, 20, 12, 4
        };
    //Permuted Choice Two
    public static final int[] PC2 = {
            14, 17, 11, 24, 1,  5,
            3,  28, 15, 6,  21, 10,
            23, 19, 12, 4,  26, 8,
            16, 7,  27, 20, 13, 2,
            41, 52, 31, 37, 47, 55,
            30, 40, 51, 45, 33, 48,
            44, 49, 39, 56, 34, 53,
            46, 42, 50, 36, 29, 32
        };
    // left bit shifting by one for bits 1,2,9,16 and by two for the rest of the bits
    public static final int bitShift[] = {
    	    1, 1, 2, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 1
    	};

			public BitStructure[] KeyScheduler(BitStructure key) {  // generate all the keys
      // todo
      BitStructure[] subKeySet = new BitStructure[bitShift.length];  // create a List object to contain subkeys
      BitStructure activeKey = permutation(key,PC1);     // perform the PC1 on original key
      int halfKeyLength = activeKey.size()/2;
      int keyLength = activeKey.size();
      int numberOfSubKey = bitShift.length;

      BitStructure part1 = activeKey.get(0,halfKeyLength);     // seperate the original key
      BitStructure part2 = activeKey.get(halfKeyLength,keyLength);

      for(k=0; k<numberOfSubKey; k++){
        part1=rotateLeft(part1,bitShift[k]);            // perform the rotation
        part2=rotateLeft(part2,bitShift[k]);
        BitStructure concat = concatenateTwo(part1,part2);    // concatenate two part of subkey
        concat = permutation(concat,PC2);               //perform the PC2
        subKeySet[k] = concat;                          // add into List
      }
      return subKeySet

    }


    public BitStructure permutation(BitStructure in, int[] map ){  // permutation method
      BitStructure out = new Bit();
      for(int i=0; i< map.length(); i++){
        boolean temp = in.get(map[i]-1);
        out.set(i,temp);
      }
      return out;
    }

    public BitStructure rotateLeft(BitStructure in,int step ){  //the rotate function
      BitStructure out = new BitStructure();
      for(int i=0; i<in.size(); i++){
        int temp = in.get(i);
        int index = (i-step+in.length)%in.length();
        out.set(index,temp);
      }
      return out;
    }

    public BitStructure concatenateTwo (BitStructure p1, BitStructure p2){  // concatenate two BitStructure
      BitStructure out = new BitStructure();
      int p1Length = p1.size();
      int p2Length = p2.size();

      for(j=0; j<p1Length; j++){
        boolean p1Bit =p1.get(j);
        out.set(j,p1Bit);
      }
      for(i=0; i<p2Length; i++){
        boolean p2Bit = p2.get(i);
        out.set(p1Length+i,p2Bit);
      }
      return out;

    }



    public BitStructure[] getEncryptionKeys() {
        //todo
        return null;
    }

    public BitStructure[] getDecryptionKeys() {
        //todo
        return null;
    }
}
