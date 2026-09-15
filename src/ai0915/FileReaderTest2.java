package ai0915;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FileReaderTest2 {
    public static void main(String[] args) {
        try {
//            file을 읽어오기 위한 입력 스트링
//            BufferedReader br = new BufferedReader(new FileReader("D:/FileIOTest/mydata1.txt"));
            BufferedReader br = new BufferedReader(new FileReader("mydata1.txt"));

            String line = "";

//            파일에서 한 줄 읽어오기
            while (true) {
                line = br.readLine();
                if (line == null) {
                    break;
                }
                System.out.println(line);
            }

//            파일인풋스트림닫기
            br.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
