package tests;

/**
 * Created by Administrator on 2017/11/13.
 */
interface Beta{}
class Alpha implements Beta{
    String testIt(){
        return "Tested";
    }
}
public class test20171113 {
    static Beta getIt(){
        return new Alpha();
    }

    public static void main(String[] args) {
        Beta b = getIt();
//        System.out.println(b.testIt());
    }
}
