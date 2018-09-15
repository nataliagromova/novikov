import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Created by NATA on 15.09.2018.
 */
public class Main {
    public static void main(String[] args) throws IOException {
       FileWriter writer =new FileWriter("cards.txt");
        String [] a={"2","3","4","5","6","7","8","9", "10","V","D","K","T"};
        String [] b={"Spades","Clubs", "Diamonds", "Hearts"};
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < b.length; j++) {
                String s= a[i]+" "+ b[j];
                writer.write(s);
                writer.append('\n');
            }
        }
        writer.flush();
    }
}
