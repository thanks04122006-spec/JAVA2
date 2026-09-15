package ai0915;

import java.util.Arrays;
import  java.util.Random;

public class LOttoGenerator {
    static int lottoNumber() {
        Random rand = new Random();
        int number = rand.nextInt(45);
        return number;
    }


    public static void main(String[] args) {
        int[] lottoArr = {};
        int number = 0;

        System.out.println("+++++++++++++++++ 로또 추첨 시작 +++++++++++++++++++");

        my_loop:
        while (true) {
            number = lottoNumber();

            for(int num : lottoArr) {
                if(num == number) {
                    continue my_loop;
                }
            }

            lottoArr = Arrays.copyOf(lottoArr, lottoArr.length + 1);
            lottoArr[lottoArr.length - 1] = number;

            if(lottoArr.length == 6) {
                break;
            }
        }

        System.out.println("++++++++++++ 이번주 로또 1등 번호 ++++++++++++++");
        Arrays.sort(lottoArr);
        System.out.println(Arrays.toString(lottoArr));
    }
}
