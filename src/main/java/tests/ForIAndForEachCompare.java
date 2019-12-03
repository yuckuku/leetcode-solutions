package tests;

import java.util.ArrayList;
import java.util.LinkedList;

public class ForIAndForEachCompare {
    public static void main(String[] args) {

        ArrayList<Integer> arrayList = new ArrayList<>();
        LinkedList<Integer> linkedList = new LinkedList<>();
        int[] nums = new int[10000];

        for (int i = 0; i < 10000; i++) {
            linkedList.add(i);
            arrayList.add(i);
            nums[i] = i;
        }

        long one = System.nanoTime();
        for (int i = 0; i < arrayList.size(); i++) {
            Integer integer = arrayList.get(i);
        }
        long two = System.nanoTime();
        System.out.println(two - one + ":以fori的方式循环arraylist");

        long one1 = System.nanoTime();
        for (Integer i : arrayList) {
            Integer integer = arrayList.get(i);
        }
        long two1 = System.nanoTime();
        System.out.println(two1 - one1 + ":以foreach的方式循环arraylist");

        System.out.println("=======================");
        long three = System.nanoTime();
        for (int i = 0; i < linkedList.size(); i++) {
            Integer integer = linkedList.get(i);
        }
        long four = System.nanoTime();
        System.out.println(four - three + ":以fori的方式循环linkedList");

        long three1 = System.nanoTime();
        for (Integer i : linkedList) {
            Integer integer = linkedList.get(i);
        }
        long four1 = System.nanoTime();
        System.out.println(four1 - three1 + ":以foreach的方式循环linkedList");
        System.out.println("=======================");

        long timeBefore1 = System.nanoTime();
        for (int i = 0; i < nums.length; i++) {
            int tmp = nums[i];
        }
        long timeAfter1 = System.nanoTime();
        System.out.println(timeAfter1 - timeBefore1 + ":以fori的方式循环int数组");

        long timeBefore2 = System.nanoTime();
        for (int i : nums) {
            int tmp = nums[i];
        }
        long timeAfter2 = System.nanoTime();
        System.out.println(timeAfter2 - timeBefore2 + ":以foreach的方式循环int数组");
        System.out.println("=======================");
    }

    /**
     * 在循环以array为底层实现的ArrayList的时候，fori的效率大概是foreach的3倍左右。
     *
     * 在循环以连表为底层实现的LinkedList的时候，foreach的效率大概是fori的1.5倍左右。
     *
     * 因此，对于不同的底层集合，可以有根据的选择适合的循环方式。
     */

}
