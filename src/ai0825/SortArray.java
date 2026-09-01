package ai0825;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

public class SortArray {
    public static void main(String[] args) {
        Integer[] NumArr = {77,33,11,99,22,55,88};
//        Arrays.sort(NumArr, Collections.reverseOrder()); //내림차순 정렬
        Arrays.sort(NumArr); // 오름차순 정렬
        for(int data : NumArr){
            System.out.print(data + " ");
        }
        System.out.println();
        String[] NameArr = {"김유민", "도형준", "강석현", "유재화", "장세은"};
//        Arrays.sort(NameArr); //오름차순 정렬
        Arrays.sort(NameArr, Collections.reverseOrder()); //내림차순
        for(String name : NameArr){
            System.out.print(name + " ");
        }

    }
}
