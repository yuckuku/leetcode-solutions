package tests;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/11/1.
 */
public class test1 {
    public static void main(String[] args) {
        Integer i3 = 128;
        Integer i4 = 200;
        System.out.println(i3.equals(i4));
        System.out.println(i3);
        System.out.println(i4);
        System.out.println(i3.hashCode());
        System.out.println(i4.hashCode());


        ArrayList<String> list = new ArrayList<>(20);

        System.out.println();
        System.out.println(list.size());
        list.add("00");
        list.add("11");
        list.add("22");
        list.add("33");
        list.add("44");
        list.add("55");
        list.add("66");
        list.add("77");
        list.add("88");
        list.add("99");
        list.ensureCapacity(20);
        System.out.println(list);
        list.trimToSize();

    }
}
