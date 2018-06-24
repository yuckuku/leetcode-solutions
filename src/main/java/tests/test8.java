package tests;

/**
 * Created by Administrator on 2017/10/31.
 */
public class test8 {
    public static void main(String[] args) {
        BB b1 = new BB("b0");
    }
}

class BB {
    private int i = 11;
    private static String name = "b3";
    private static BB b2 = new BB("b2");
    private static int j = 11;
    private static BB b1 = new BB("b1");

    {
        System.out.println(name + ": dynamic");
    }

    static {
        System.out.println(name + ": static");
    }

    public BB(String name) {
        BB.name = name;
        this.test();
    }

    public void test() {
        System.out.println(name + ": " + ++i);
        System.out.println(name + ": " + ++j);
    }

}
