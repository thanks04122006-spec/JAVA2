package ai0901;

import java.util.Arrays;
import java.util.Collections;
import java.util.Random;


public class ResultFreRock {

    public static void main(String[] args) {
        String resultArr[] = new String[10000]; //A,B,없음
        String comA, comB;
        String[] strings = {"가위", "바위", "보"};
        int aWinFreq, bWinFreq, noWinFreq; //이기거나 비긴 횟수


        for (int i = 0; i < resultArr.length; i++) {
            Random rand = new Random();
            comA = strings[rand.nextInt(strings.length)];
            comB = strings[rand.nextInt(strings.length)];

            if (comA.equals("가위")) {
                if (comB.equals("가위")) {
                    resultArr[i] = "없음";
                } else if (comB.equals("바위")) {
                    resultArr[i] = "B";
                } else if (comB.equals("보")) {
                    resultArr[i] = "A";
                }
            }
            if (comA.equals("바위")) {
                if (comB.equals("가위")) {
                    resultArr[i] = "A";
                } else if (comB.equals("바위")) {
                    resultArr[i] = "없음";
                } else if (comB.equals("보")) {
                    resultArr[i] = "B";
                }
            }
            if (comA.equals("보")) {
                if (comB.equals("가위")) {
                    resultArr[i] = "B";
                } else if (comB.equals("바위")) {
                    resultArr[i] = "A";
                } else if (comB.equals("보")) {
                    resultArr[i] = "없음";
                }
            }
        }
        aWinFreq = Collections.frequency(Arrays.asList(resultArr),"A");
        bWinFreq = Collections.frequency(Arrays.asList(resultArr),"B");
        noWinFreq = Collections.frequency(Arrays.asList(resultArr),"없음");

        System.out.println("컴퓨터 A가 승리한 횟수: "+aWinFreq);
        System.out.println("컴퓨터 B가 승리한 횟수: "+bWinFreq);
        System.out.println("컴퓨터 비긴 횟수: "+aWinFreq);
    }
}
