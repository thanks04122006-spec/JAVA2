package ai0922;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Spy2 {
    public static void main(String[] args) {
        try {
            String line;
            String secure;

            BufferedReader br =
                    new BufferedReader(new FileReader("secure.txt"));

            FileWriter fw =
                    new FileWriter("decodesecure.txt");

            while (true) {
                line = br.readLine();

                if (line == null) {
                    break;
                }

                secure = "";

                for (int i = 0; i < line.length(); i++) {
                    int num = line.charAt(i);
                    num -= 100;
                    secure += (char) num;
                }

                fw.write(secure + "\n");
            }

            br.close();
            fw.close();

            System.out.println("복호화 완료");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}