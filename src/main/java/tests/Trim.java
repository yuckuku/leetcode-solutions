package tests;
import java.util.Scanner;

/**
 * Created by Administrator on 2017/10/30.
 */
public class Trim {
    public static void main(String[] args) {
//        String s = "447. Number of Boomerangs   ";
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();  //读取字符串型输入
        String ss = s.replaceAll("\\s*","");
        String temp[] = ss.split("\\.");
        System.out.println(temp[1]+temp[0]);
    }
}
