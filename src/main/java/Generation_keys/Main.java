package Generation_keys;

import java.io.FileWriter;
import java.io.IOException;
import Encrypting.Poker;


public class Main {
    public static void main(String[] args) throws IOException {
        long p = 1243;
        long q = 11;
        System.out.println("1.Generating public and private keys for everyone");
        FileWriter writer;
        for (int i = 0; i < 3; i++) {
            RSA rsa = new RSA(p, q);
            long e = rsa.getE();
            long d = rsa.getD();
            long n = rsa.getN();
            if (i == 0) {
                writer = new FileWriter("keys_A.txt");
                writer.write(String.valueOf(e));
                writer.append('\n');
                writer.write(String.valueOf(d));
                writer.append('\n');
                writer.write(String.valueOf(n));
            } else if (i == 1) {
                writer = new FileWriter("keys_B.txt");
                writer.write(String.valueOf(e));
                writer.append('\n');
                writer.write(String.valueOf(d));
                writer.append('\n');
                writer.write(String.valueOf(n));
            } else {
                writer = new FileWriter("keys_C.txt");
                writer.write(String.valueOf(e));
                writer.append('\n');
                writer.write(String.valueOf(d));
                writer.append('\n');
                writer.write(String.valueOf(n));
            }
            writer.flush();
        }
        System.out.println("2.A is encrypting the cards");
        Poker.HashcodeCards("cards.txt");

    }
}
