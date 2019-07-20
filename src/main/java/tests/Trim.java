package tests;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by Administrator on 2017/10/30.
 */
public class Trim {
    public static void main(String[] args) {
//        String s = "447. Number of Boomerangs   ";
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();  //读取字符串型输入
//        String ss = s.replaceAll("\\s*","");
//        System.out.println("line is:" + s);
        String temp[] = StringUtils.split(s);
        String className = "";
        for (String str :
                temp) {
            className += str;
        }
        System.out.println("class name:" + className);
    }
}
