package ai0901;

import java.util.Random;

public class UserMethodTest1 {
    public static void testDice(String userName){
        System.out.println(userName + "님, 주사위를 던지세요.");
        Random rand = new Random();
        int diceNum = rand.nextInt(6)+1;
        System.out.println("주사위의 결과: " + diceNum);
    }

    public static void main(String[] args) {
        String Namearr[] = {"사과","키위","복숭아","딸기","무화과","바나나"};

        for(int i=0;i<Namearr.length;i++){
            testDice(Namearr[i]);
        }
    }
}
