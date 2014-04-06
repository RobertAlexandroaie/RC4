package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import util.Constants;
import model.RC4Cryptosystem;

public class Main {

    public static void main(String[] args) {

	RC4Cryptosystem cryptoSys = new RC4Cryptosystem();

	StringBuilder plaintextBuilder = new StringBuilder();
	BufferedReader bufferedReader = null;

	// citire plaintext
	try {
	    bufferedReader = new BufferedReader(new FileReader(new File("plaintext.txt")));
	    String line;
	    while ((line = bufferedReader.readLine()) != null) {
		plaintextBuilder.append(line + "\n");
	    }
	} catch (FileNotFoundException e) {
	    e.printStackTrace();
	} catch (IOException e) {
	    e.printStackTrace();
	} finally {
	    if (bufferedReader != null) {
		try {
		    bufferedReader.close();
		} catch (IOException e) {
		    e.printStackTrace();
		}
	    }
	}

	String plaintext = plaintextBuilder.toString();

	// 1)
	String cryptedTxt = cryptoSys.encrypt(plaintext, false);
	String decryptedTxt = cryptoSys.decrypt(cryptedTxt);

	BufferedWriter bufferedWriter = null;
	try {
	    // scriere criptotext
	    bufferedWriter = new BufferedWriter(new FileWriter(new File("criptotext.txt")));
	    bufferedWriter.write(cryptedTxt);

	    // scriere decriptotext
	    bufferedWriter = new BufferedWriter(new FileWriter(new File("decriptedtext.txt")));
	    bufferedWriter.write(decryptedTxt);
	} catch (IOException e) {
	    e.printStackTrace();
	} finally {
	    if (bufferedWriter != null) {
		try {
		    bufferedWriter.close();
		} catch (IOException e) {
		    e.printStackTrace();
		}
	    }
	}

	// System.out.println(plaintext);
	// System.out.println("-----------------------------------------------------");
	// System.out.println(cryptedTxt);
	// System.out.println("-----------------------------------------------------");
	// System.out.println(decryptedTxt);

	// ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	// ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

	// 2)
	plaintext=Constants.SHORT_MESSAGE;
	RC4Cryptosystem cryptoSys2 = new RC4Cryptosystem();
	long n = 256*256 ;
	int length = plaintext.getBytes().length;
	cryptoSys2.initZeros(length);
	for (int i = 0; i < n; i++) {
	    cryptoSys2.encrypt(plaintext, true);
	}
	cryptoSys2.showZeros();
    }
}
