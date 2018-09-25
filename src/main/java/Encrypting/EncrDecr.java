package Encrypting;


import java.io.IOException;
import java.util.Scanner;

public class EncrDecr {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        Poker.DeleteFiles();
        while (true) {
            {
                long e = Poker.Keys("keys_A.txt")[0];
                long p = Poker.Keys("keys_A.txt")[2];
                Poker.EncryptCards("All_cards", "Ea(cards)", e, p);
                System.out.println("1.A is encrypting and shuffling the All_cards to the Ea(cards) and sending it to B.");
            }

            if (!scanner.nextLine().equals("e")) {
                System.out.println("2.B is choosing his cards,encrypting it and sending to A.");
                Poker.ChoosingCards();
                long e = Poker.Keys("keys_B.txt")[0];
                long p = Poker.Keys("keys_B.txt")[2];
                Poker.EncryptCards("temp", "Eb(Ea(cards)", e, p);
                Poker.purgeDirectory("temp");
            } else if (scanner.nextLine().equals("e"))
                return;

            if (!scanner.nextLine().equals("e")) {
                System.out.println("3.C is choosing his cards,encrypting it and sending to A.");
                Poker.ChoosingCards();
                long e = Poker.Keys("keys_C.txt")[0];
                long p = Poker.Keys("keys_C.txt")[2];
                Poker.EncryptCards("temp", "Ec(Ea(cards)", e, p);
                Poker.purgeDirectory("temp");
            } else if (scanner.nextLine().equals("e"))
                return;
            if (!scanner.nextLine().equals("e")) {
                System.out.println("4.A is decrypting Eb(Ea(cards) and Ec(Ea(cards)).");
                long d = Poker.Keys("keys_A.txt")[1];
                long p = Poker.Keys("keys_A.txt")[2];
                Poker.EncryptCards("Eb(Ea(cards)", "Eb(cards)", d, p);
                Poker.EncryptCards("Ec(Ea(cards)", "Ec(cards)", d, p);
            } else if (scanner.nextLine().equals("e"))
                return;
            if (!scanner.nextLine().equals("e")) {
                System.out.println("5.B and C can decrypt their cards and see them in the for_B and the for_C.");
                long d = Poker.Keys("keys_B.txt")[1];
                long p = Poker.Keys("keys_B.txt")[2];
                Poker.DecryptCards("Eb(cards)", "for_B", d, p);
                d = Poker.Keys("keys_C.txt")[1];
                p = Poker.Keys("keys_C.txt")[2];
                Poker.DecryptCards("Ec(cards)", "for_C", d, p);
            }
            if (!scanner.nextLine().equals("e")) {
                System.out.println("6.C chooses cards for A and sends it.\n");
                Poker.ChoosingCards();
                long d = Poker.Keys("keys_A.txt")[1];
                long p = Poker.Keys("keys_A.txt")[2];
                Poker.DecryptCards("temp", "for_A", d, p);
                System.out.println("7.A decrypts its cards to the for_A.");
            }
            return;
        }

    }
}
