package Generation_keys;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;


class Cards_generation {
//    public static void main(String[] args) throws IOException {
//        Generation_card();
//        System.out.println("2.Cards have their hashcodes.");
//    }
    static void Generation_card() throws IOException {
        List<String> lines = Files.readAllLines(Paths.get("cards.txt"), StandardCharsets.UTF_8);
        Collections.shuffle(lines);
        FileWriter writer;
        int i = 1;
        for (String line : lines) {
            writer = new FileWriter("All_cards/" + String.valueOf(i) + ".txt");
            writer.write(line + " " + String.valueOf(Rnd(10000, 99999)));
            writer.write('\n');
            i++;
            writer.flush();
        }
    }
    private static int Rnd(int min, int max) {
        max -= min;
        return (int) (Math.random() * ++max) + min;
    }
}
