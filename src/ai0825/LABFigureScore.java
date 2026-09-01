package ai0825;

import java.util.Scanner;

public class LABFigureScore {
    public static void main(String[] args) {
        int[] score = new int[5];
        int sum = 0;
        double avg;
        Scanner input = new Scanner(System.in);

        System.out.println("김연아 선수의 멋진경기를 마쳤습니다");
       for (int i = 0; i < score.length; i++) {
            System.out.println("심사위원" + (i+1) +":");
            score[i] = input.nextInt();
            sum += score[i];
        }

        avg=sum/score.length;
        System.out.println("[심사위원 입력점수]");
        for (int j = 0; j < score.length; j++) {
            System.out.printf("심사위원 %d: %d\n", j+1, score[j]);
        }
        System.out.println("합계점수: " + sum);
        System.out.printf("평균점수: %.2f", avg);
        input.close();
    }
}
