package ai0915;

public class VariableTest {
    static int a = 100;
    int b = 500;

    static void method1(){
        int a = 300;
        int b = 7000;
        System.out.println("지역변수 a에 저장된 값: "+ a);
        System.out.println("전역변수 a를 메소드 1에서 사용하고 싶을때: "+ VariableTest.a);
        VariableTest vt1 = new VariableTest();
        System.out.println("전역변수 b를 메소드 1에서 사용하고 싶을때: "+ vt1.b);

    }

    static void method2(){
        a += 20;
        System.out.println("전역변수 a에 저장된 값: "+ a);
    }
    public static void main(String[] args) {
        method1();
        method2();
    }
}
