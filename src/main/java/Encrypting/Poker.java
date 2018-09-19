package Encrypting;

import java.io.*;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;


public class Poker {
    final static LinkedHashMap<Integer, String> cardsHash = new LinkedHashMap<>();

    public static void HashcodeCards(String catalogname) throws IOException {
        List <File> mas=Files.walk(Paths.get(catalogname))
                .filter(Files::isRegularFile)
                .map(Path::toFile)
                .collect(Collectors.toList());
        FileWriter writer;
        for (File f:mas) {
            String l= Arrays.toString(Files.readAllBytes(f.toPath()));
            writer=new FileWriter("Hcards"+"/"+f.getName());
            char [] mass;
           writer.write(Integer.parseInt(l,2));
            writer.flush();
        }
    }

    public static void OpenCards() throws IOException {
        String[] mas = {"Ea.txt", "Eb.txt", "Ec.txt"};
        for (String ma : mas) {
            List<String> lines = Files.readAllLines(Paths.get(ma), StandardCharsets.UTF_8);
            FileWriter writer = new FileWriter(ma);
            for (String line : lines) {
                writer.write(cardsHash.get(Integer.parseInt(line)));
                writer.append('\n');
            }
            writer.flush();
        }

        }


    static void EncryptCards(String catalognameFrom, String catalognameTo, long e, long p) throws IOException {
     List <File> mas=Files.walk(Paths.get(catalognameFrom))
                .filter(Files::isRegularFile)
                .map(Path::toFile)
                .collect(Collectors.toList());
      FileWriter writer = null;
      String res;
        for (File f:mas) {
            byte [] lines = Files.readAllBytes(f.toPath());
            writer=new FileWriter(catalognameTo+"/"+f.getName());
            res = String.valueOf(Encrypting(lines, e, p));
            writer.append(res).append('\n');
        }
        writer.flush();

    }

    static ArrayList<Integer> Rand(int number, int bound) {
        Random r = new Random();
        ArrayList<Integer> randomN = new ArrayList<>();
        while (randomN.size() != number) {
            int rand = r.nextInt(bound);
            if (!randomN.contains(rand)) {
                randomN.add(rand);
            }
        }
        Collections.sort(randomN);
        return randomN;
    }


    private static Long Encrypting(byte [] l, long e, long p) {
        int m=0;
        for (byte aL : l) {
            m += aL;
        }
        BigInteger n = new BigInteger(String.valueOf(m));
        return n.modPow(BigInteger.valueOf(e), BigInteger.valueOf(p)).longValue();
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

    static void ChoosingCards(ArrayList<Integer> cards, String filenameFrom) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(filenameFrom), StandardCharsets.UTF_8);
        FileWriter writer = new FileWriter(filenameFrom);
        ArrayList<String> needed_lines = new ArrayList<>();
        for (int i = 0; i < lines.size(); i++) {
            if (cards.contains(i)) {
                needed_lines.add(lines.get(i));
            } else {
                writer.write(lines.get(i));
                writer.append('\n');
            }
        }
        writer.flush();
        writer = new FileWriter("temp.txt");
        for (String line : needed_lines) {
            writer.write(line);
            writer.append('\n');
        }
        writer.flush();
    }
}
