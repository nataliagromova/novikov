import java.io.*;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Random;
import java.util.TreeSet;
import java.util.stream.Stream;


public class Poker {
    static long GenerationInverse(Long a, Long p) {
        BigInteger inverse = new BigInteger(String.valueOf(a));
        return inverse.modInverse(BigInteger.valueOf(p)).longValue();
    }

    static void HashcodeCards(String filename) throws IOException {
        FileWriter writer = new FileWriter("hashcodecards.txt");
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        for (int i = 0; i < reader.read(); i++) {
            String l = reader.readLine();
            int n = l.hashCode();
            writer.write(n);
            writer.append('\n');
        }
        writer.flush();
        writer.close();
    }

    static void EncryptCards(String filenameFrom,String filenameTo, long e, long p) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filenameFrom));
        FileWriter writer = new FileWriter(filenameTo);
        Stream<String> listofcards = Files.lines(Paths.get(filenameFrom), StandardCharsets.UTF_8);
        listofcards.forEach(m -> Encrypting(Long.parseLong(m), e, p));
        listofcards.forEach(Em -> {
            try {
                writer.append(Em).append('\n');
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });
    }
    static ArrayList<Integer> Rand(int number){
        Random r=new Random();
        ArrayList<Integer> randomN=new ArrayList<>();
        for (int i = 0; i < number; i++) {
            int rand=r.nextInt(52);
            if(!randomN.contains(rand)){
                randomN.add(rand);
            }
        }
       return randomN;
    }
    static long Random(){
       return BigInteger.probablePrime(5,new Random()).longValue();

    }

    private static Long Encrypting(long m, long e, long p) {
        BigInteger n = new BigInteger(String.valueOf(m));
        return n.modPow(BigInteger.valueOf(e), BigInteger.valueOf(p)).longValue();
    }
    static Long Decrypting(long x, long d, long p) {
        BigInteger n = new BigInteger(String.valueOf(x));
        return n.modPow(BigInteger.valueOf(d), BigInteger.valueOf(p)).longValue();
    }
}
