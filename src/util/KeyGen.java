package util;

import java.util.Random;

public abstract class KeyGen {

    public static byte[] getKey() {
	Random random = new Random();
	int keyLength = 5 + (int) (Math.random() * ((32 - 5) + 1));
	byte[] K = new byte[keyLength];
	random.nextBytes(K);
	return K;
    }
}
