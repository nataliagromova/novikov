import java.io.FileWriter;
import java.io.IOException;


public class Main {
    public static void main(String[] args) throws IOException {
        long p = 113;
        long q = 11;
        RSA rsa = new RSA(p, q);
        System.out.println("1.Generating public and private keys for everyone");
        FileWriter writer;
        for (int i = 0; i < 3; i++) {
            long e = rsa.getE();
            long d = rsa.getD();
            long n = rsa.getN();
            if(i==0) {
                writer = new FileWriter("for_A.txt");
                writer.write(String.valueOf(e));
                writer.append('\n');
                writer.write(String.valueOf(d));
            }
            else if (i==1){
                    writer = new FileWriter("for_B.txt");
                    writer.write(String.valueOf(e));
                    writer.append('\n');
                    writer.write(String.valueOf(d));
            }
            else {
                    writer = new FileWriter("for A.txt");
                    writer.write(String.valueOf(e));
                    writer.append('\n');
                    writer.write(String.valueOf(d));
                }

        }
        System.out.println("2.A is encrypting of cards");

    }
}
