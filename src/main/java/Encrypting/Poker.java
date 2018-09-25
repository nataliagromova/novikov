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
    static void EncryptCards(String catalognameFrom, String catalognameTo, long e, long p) throws IOException {
        List<File> mas = ListOfFiles(catalognameFrom);
        InputStream in;
        OutputStream out;
        int ch;
        for (File f : mas) {
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
