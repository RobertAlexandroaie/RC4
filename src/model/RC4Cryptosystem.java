/**
 * 
 */
package model;

import util.KeyGen;

/**
 * @author Robert
 * 
 */
public class RC4Cryptosystem {
    private byte[] key;
    private byte[] K;
    private byte[] S;
    private static int I = 0;
    private static int J = 0;
    private static int[] zeros;

    public RC4Cryptosystem() {
	S = new byte[256];
    }

    public void initZeros(int length) {
	zeros = new int[length];
	for (int i = 0; i < length; i++) {
	    zeros[i] = 0;
	}
    }

    private void init() {
	K = KeyGen.getKey();
	int i = 0;
	int j = 0;
	for (i = 0; i < 256; i++) {
	    S[i] = (byte) i;
	}

	j = 0;
	for (i = 0; i < 256; i++) {
	    j = (j + S[i] + K[i % K.length]) % 256;
	    swap(i, j);
	}
    }

    private void swap(int i, int j) {
	byte aux;
	if (i < 0) {
	    i += 256;
	}
	if (j < 0) {
	    j += 256;
	}
	i %= 256;
	j %= 256;
	aux = S[i];
	S[i] = S[j];
	S[j] = aux;
    }

    private byte genKeyByte() {
	byte b;
	I = (I + 1) % 256;
	J = (J + S[I]) % 256;

	if (I < 0) I += 256;
	if (J < 0) J += 256;
	swap(I, J);
	int index = (S[I] + S[J]) % 256;
	if (index < 0) {
	    index += 256;
	    index %= 256;
	}
	b = (byte) S[index];
	return b;
    }

    public void buildKey(int n) {
	key = new byte[n];
	for (int i = 0; i < n; i++) {
	    byte b = genKeyByte();
	    key[i] = b;
	}
    }

    public String encrypt(String plaintext, boolean checkBytes) {
	init();
	I = 0;
	J = 0;
	buildKey(2 * plaintext.length());

	if (checkBytes) {
	    for (int i = 0; i < zeros.length; i++) {
		if (key[i] == 0) {
		    zeros[i]++;
		}
	    }
	}

	StringBuilder cryptedText = new StringBuilder(plaintext.length());
	for (int i = 0; i < plaintext.length(); i++) {
	    char c = 0;
	    c ^= key[2 * i];
	    c <<= 8;
	    c ^= key[2 * i + 1];
	    cryptedText.append((char) (plaintext.charAt(i) ^ c));
	}
	return cryptedText.toString();
    }

    public String decrypt(String cryptotext) {
	StringBuilder plainText = new StringBuilder(cryptotext.length());
	for (int i = 0; i < cryptotext.length(); i++) {
	    char c = 0;
	    c ^= key[2 * i];
	    c <<= 8;
	    c ^= key[2 * i + 1];
	    plainText.append((char) (cryptotext.charAt(i) ^ c));
	}

	return plainText.toString();
    }

    public void showZeros() {
	for (int i = 0; i < zeros.length; i++) {
	    System.out.println(String.format("Byte[%d]:%d", i, zeros[i]));
	}
    }

    public void showZerosOf(int pos) {
	System.out.println(String.format("Byte[%d]:%d", pos, zeros[pos]));
    }

    /**
     * @return the k
     */
    public byte[] getK() {
	return K;
    }

    /**
     * @param k
     *            the k to set
     */
    public void setK(byte[] k) {
	K = k;
    }

    /**
     * @return the s
     */
    public byte[] getS() {
	return S;
    }

    /**
     * @param s
     *            the s to set
     */
    public void setS(byte[] s) {
	S = s;
    }

    /**
     * @return the key
     */
    public byte[] getKey() {
	return key;
    }

}
