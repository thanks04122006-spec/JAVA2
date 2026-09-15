package ai0915;

import java.util.Scanner;

public class Password {
    //    비밀번호의 길이는 8개 이상
//    한글 또는 영문만 사용, 숫자 기호 X
    static boolean password(String pwd) {
        if (pwd.length() < 8) {
            System.out.println("비밀번호 문자열 길이가 8개 이상이여야 합니다");
            return false;
        }
        for (int i = 0; i < pwd.length(); i++) {
            char c = pwd.charAt(i);
            if (!Character.isAlphabetic(c)) {
                System.out.println("비밀번호에는 한글 또는 영문만 사용가능 합니다");
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String pwd = input.nextLine();

        if (Password.password(pwd) == false) {
            System.out.println("! ! ! 비밀번호 규칙에 어긋납니다");
        }
        else
            System.out.println("OK~ 비밀번호 규칙에 맞습니다");

        input.close();
    }
}
