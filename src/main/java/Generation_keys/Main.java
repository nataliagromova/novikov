package Generation_keys;

import java.io.FileWriter;
import java.io.IOException;
import Encrypting.Poker;


public class Main {
    public static void main(String[] args) throws IOException {
        long n=Generation_key.RandPrime();
        System.out.println("1.Generating public and private keys for everyone");
        FileWriter writer;
        for (int i = 0; i < 3; i++) {
            Generation_key key=new Generation_key(n);
            long e = Generation_key.getE();
            long d = Generation_key.getD();
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
        Cards_generation.Generation_card();
        System.out.println("2.Cards have their hashcodes.");
//        Poker.HashcodeCards("cards.txt");

    }
}
