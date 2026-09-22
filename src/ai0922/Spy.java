package ai0922;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Spy {
    public static void main(String[] args) {
        try {
            Scanner input = new Scanner(System.in);
            String line = "";
            String secure = "";
            FileWriter fw = new FileWriter("secure.txt");

            while (true) {
                secure = "";

                System.out.println("스파이에게 전달할 메세지");
                line = input.nextLine();

                if (line.isEmpty()) {
                    break;
                }

                for (int i = 0; i < line.length(); i++) {
                    int num = line.charAt(i);
                    num += 100;
                    secure += (char) num;
                }

                fw.write(secure + "\n");
            }

            fw.close();
            input.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
