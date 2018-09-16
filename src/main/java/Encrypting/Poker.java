package Encrypting;

import java.io.*;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;


public class Poker {
    static long GenerationInverse(Long a, Long p) {
        BigInteger inverse = new BigInteger(String.valueOf(a));
        return inverse.modInverse(BigInteger.valueOf(p)).longValue();
    }

    public static void HashcodeCards(String filename) throws IOException {
        FileWriter writer = new FileWriter("hashcodecards.txt");
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        for (int i = 0; i < reader.read(); i++) {
            String l = reader.readLine();
            int n = l.hashCode();
            if (n<0)
                n-=2*n;
            writer.write(String.valueOf(n));
            writer.append('\n');
        }
        writer.flush();

    }

    static void EncryptCards(String filenameFrom, String filenameTo, long e, long p) throws IOException {
        FileWriter writer = new FileWriter(filenameTo);
        List<String> lines = Files.readAllLines(Paths.get(filenameFrom), StandardCharsets.UTF_8);
        for (String line : lines) {
            line = String.valueOf(Encrypting(Long.parseLong(line), e, p));
            writer.append(line).append('\n');
        }
        writer.flush();
    }
    static void DecryptCards(String filenameFrom, String filenameTo, long e, long p) throws IOException {
        FileWriter writer = new FileWriter(filenameTo);
        List<String> lines = Files.readAllLines(Paths.get(filenameFrom), StandardCharsets.UTF_8);
        for (String line : lines) {
            line = String.valueOf(Encrypting(Long.parseLong(line), e, p));
            writer.append(line).append('\n');
        }
        writer.flush();
    }

    static ArrayList<Integer> Rand(int number,int bound) {
        Random r = new Random();
        ArrayList<Integer> randomN = new ArrayList<>();
        for (int i = 0; i < number; i++) {
            int rand = r.nextInt(bound);
            if (!randomN.contains(rand)) {
                randomN.add(rand);
            }
        }
        Collections.sort(randomN);
        return randomN;
    }

    static long Random() {
        return BigInteger.probablePrime(5, new Random()).longValue();

    }

    private static Long Encrypting(long m, long e, long p) {
        BigInteger n = new BigInteger(String.valueOf(m));
        return n.modPow(BigInteger.valueOf(e), BigInteger.valueOf(p)).longValue();
    }

    static Long Decrypting(long x, long d, long p) {
        BigInteger n = new BigInteger(String.valueOf(x));
        return n.modPow(BigInteger.valueOf(d), BigInteger.valueOf(p)).longValue();
    }

    static int[] Keys(String filename) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(filename)));
        String line;
        int i = 0;
        int[] res = new int[3];
        while ((line = reader.readLine()) != null) {
            res[i++] = Integer.valueOf(line);
        }
        return res;
    }
    static void ChoosingCards(ArrayList<Integer> cards,String filenameFrom) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(filenameFrom), StandardCharsets.UTF_8);
        FileWriter writer = new FileWriter(filenameFrom);
        ArrayList<String> needed_lines=new ArrayList<>();
        for (int i = 0; i < lines.size(); i++) {
         if(cards.contains(i)) {
            needed_lines.add(lines.get(i));
         }
         else {
             writer.write(lines.get(i));
             writer.append('\n');
         }
        }
        writer.flush();
        writer=new FileWriter("temp.txt");
        for (String line:needed_lines) {
            writer.write(line);
            writer.append('\n');
        }
        writer.flush();
    }
}
