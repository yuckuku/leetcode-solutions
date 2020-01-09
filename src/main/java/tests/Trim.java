package tests;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.util.Scanner;

/**
 * Created by Administrator on 2017/10/30.
 */
public class Trim {
    public static void main(String[] args) {
//        String s = "447. Number of Boomerangs   ";
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();
        String tmp = sc.nextLine();
        while (!"stop".equals(tmp)) {
            sb.append(tmp);
            if (tmp.contains("的用户")) sb.append("\n");
            tmp = sc.nextLine();
        }
        sc.close();
        String s = sb.toString();  //读取字符串型输入
//        String ss = s.replaceAll("\\s*","");
        System.out.println("line is:" + s);

        sb.setLength(0);
        String temp[] = null;
        temp = StringUtils.split(s, "\n");
        for (int i = 0; i < temp.length; i++) {
            System.out.println(temp[i]);
            sb.append(temp[i].replaceAll("\\s*", "")).append("\n");
        }
        System.out.println("------------result-------------");
        System.out.println(sb.toString());
    }

    @Test
    public void test() {
        String s = "a b c d";
//        StringUtils.replace(s, " ", null);
        System.out.println(s.indexOf(" ", 0));
        s.replaceAll("\\s*", "");
        s.replaceAll(" +","");
        System.out.println(s.replaceAll("\\s*", ""));
    }
}
