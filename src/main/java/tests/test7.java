package tests;

/**
 * Created by Administrator on 2017/10/31.
 */
public class test7 {
    public static void main(String[] args) {
        B1 b1 = new B1();
    }
}

class A1 {
    public A1() {
        this.test();
    }

    public void test() {
        System.out.println("hello");
    }
}

class B1 extends A1 {
    private int i = 11;
    private static int j = 11;

    public void test() {
        System.out.println(i);
        System.out.println(j);
    }
}
