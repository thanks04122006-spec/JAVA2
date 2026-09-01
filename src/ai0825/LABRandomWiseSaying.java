package ai0825;

import java.util.Date;
import java.util.Random;

public class LABRandomWiseSaying {
    public static void main(String[] args) {
        String[] Wise_say = {"성공은 최종 목적지가 아니라, 실패를 두려워하지 않고 계속 나아가는 과정이다.",
                "할 수 있다고 믿든, 할 수 없다고 믿든, 믿는 대로 된다.",
                "오늘 걷지 않으면 내일은 뛰어야 한다.",
                "배움에는 끝이 없고, 성장에는 한계가 없다.",
                "가장 큰 영광은 한 번도 실패하지 않는 것이 아니라, 넘어질 때마다 다시 일어서는 데 있다.",
                "미래를 예측하는 가장 좋은 방법은 미래를 만드는 것이다.",
                "시작이 반이다.",
                "꿈을 이루고자 하는 사람은 먼저 깨어 있어야 한다.",
                "변화는 모든 성장의 결과물이다.",
                "당신이 할 수 있는 가장 큰 모험은 당신이 꿈꾸는 삶을 사는 것이다."};

        Random rand = new Random();
        int randonIndex = rand.nextInt(Wise_say.length);
        Date now = new Date();

        System.out.println( now + " 오늘의 명언: " + Wise_say[randonIndex]);
    }
}
