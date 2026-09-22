package ai0922;

import java.io.*;

public class FileCopy{
    public static void main(String[] args) {
        String fileName = "myData1.txt";

        try {
//            파일 input스트림 생성
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            FileWriter fw = new FileWriter("data2.txt");

            String line = "";

            while (true) {
                line = br.readLine();
                if (line == null) {
                    break;
                }

                fw.write(line + "\n");
            }

            br.close();
            fw.close();

            System.out.println("파일 복사 완료");

        } catch (IOException e) {
            System.out.println("파일을 읽는 중 오류가 발생했습니다.");
        }
    }
}