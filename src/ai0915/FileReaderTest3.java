package ai0915;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FileReaderTest3 {
//    파일의 내용 전체를 완전히 거꾸로 하고 싶을때
    public static void main(String[] args) {
        try {
            StringBuilder sb = new StringBuilder();
//            file을 읽어오기 위한 입력 스트링
            BufferedReader br = new BufferedReader(new FileReader("mydata1.txt"));

            String line = "";

//            파일에서 한 줄 읽어오기
            while (true) {
                line = br.readLine();
                if (line == null) {
                    break;
                }
                sb.append(line).append("\n");
            }

//            파일인풋스트림닫기
            br.close();

            sb.reverse();
            System.out.println(sb);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
