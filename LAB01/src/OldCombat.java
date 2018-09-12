

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class OldCombat {
    public static void main(String[] args) {
        File file = new File("src\\loading.txt");
        BufferedReader reader = null;
        String temp = null;
        int line = 1;
        try {
            reader = new BufferedReader(new FileReader(file));
            while ((temp = reader.readLine()) != null) {
                Fighter fighter=(Fighter) Class.forName(temp).newInstance();
                fighter.fight();
                line++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}