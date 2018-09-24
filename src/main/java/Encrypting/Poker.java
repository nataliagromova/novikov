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

    public static void HashcodeCards(String catalognameFrom) throws IOException {
        List<File> mas = ListOfFiles(catalognameFrom);
        FileWriter writer;
        for (File f : mas) {
            String l = Arrays.toString(Files.readAllBytes(f.toPath()));
            writer = new FileWriter("Hcards" + "/" + f.getName());
            char[] mass;
            writer.write(Integer.parseInt(l, 2));
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
        List<File> mas = ListOfFiles(catalognameFrom);
//        FileWriter writer = null;
//        String res;
//        for (File f : mas) {
//            byte[] lines = Files.readAllBytes(f.toPath());
//            writer = new FileWriter(catalognameTo + "/" + f.getName());
//            res = String.valueOf(Encrypting(lines, e, p));
//            writer.append(res).append('\n');
//            writer.flush();
//        }
//
//        writer.close();
//        System.gc();
        InputStream in;
        OutputStream out;
        int ch;
        for (File f : mas) {
//            byte[] lines = Files.readAllBytes(f.toPath());
//            writer = new FileWriter(catalognameTo + "/" + f.getName());
//            res = String.valueOf(Encrypting(lines, e, p));
//            writer.append(res).append('\n');
//            writer.flush();
            in=new FileInputStream(f);
            out=new FileOutputStream(catalognameTo + "/" + f.getName().split("\\.")[0]
                    +".dat");
            while ((ch = in.read()) >= 0)
            {
                out.write(Math.toIntExact(Encrypting(ch, e, p)));
            }
            in.close();
            out.close();
        }
    }
    static void DecryptCards(String catalognameFrom, String catalognameTo, long e, long p) throws IOException {
        List<File> mas = ListOfFiles(catalognameFrom);
        InputStream in;
        OutputStream out;
        int ch;
        for (File f : mas) {
            in = new FileInputStream(f);
            out = new FileOutputStream(catalognameTo + "/" + f.getName().split("\\.")[0] + ".txt");
            while ((ch = in.read()) >= 0) {
                out.write(Math.toIntExact(Encrypting(ch, e, p)));
            }
            in.close();
            out.close();
        }
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


    private static Long Encrypting(int ch, long e, long p) {
//        int m = 0;
//        for (byte aL : l) {
//            m += aL;
//        }
//        BigInteger n = new BigInteger(String.valueOf(m));
//        return n.modPow(BigInteger.valueOf(e), BigInteger.valueOf(p)).longValue();
        BigInteger n = new BigInteger(String.valueOf(ch));
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

    private static List<File> ListOfFiles(String catalognameFrom) throws IOException {
        return Files.walk(Paths.get(catalognameFrom))
                .filter(Files::isRegularFile)
                .map(Path::toFile)
                .collect(Collectors.toList());

    }

    static void ChoosingCards() {
        List<File> list = null;
        try {
            list = ListOfFiles("Ea(cards)");
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert list != null;
        List<File> list_res = list.subList(0, 5);
        for (File f : list_res) {
            f.renameTo(new File("temp" + "/" + f.getName()));
        }
        for (int i = 0; i < 5; i++) {
            try {
                Files.delete(list.get(0).toPath());
            } catch (IOException e) {
            }
            list.remove(0);
        }

    }
    public static void purgeDirectory(String  d) {
        File dir=new File(d);
        for (File file: dir.listFiles()) {
            if (file.isDirectory()) purgeDirectory(file.getName());
            file.delete();
        }
    }
    public  static void DeleteFiles(){
        Poker.purgeDirectory("Eb(Ea(cards)");
        Poker.purgeDirectory("Ec(Ea(cards)");
        Poker.purgeDirectory("Eb(cards)");
        Poker.purgeDirectory("Ec(cards)");
        Poker.purgeDirectory("Eb(Ea(cards)");
        Poker.purgeDirectory("Ea(cards)");
        Poker.purgeDirectory("for_A");
        Poker.purgeDirectory("for_B");
        Poker.purgeDirectory("for_C");
        Poker.purgeDirectory("temp");
    }



}
