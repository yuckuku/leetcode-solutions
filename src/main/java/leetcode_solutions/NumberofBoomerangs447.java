package leetcode_solutions;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/11/30.
 */
public class NumberofBoomerangs447 {
    //time limit exceeded
    public int numberOfBoomerangs(int[][] points) {
        if (points == null || points.length < 3)
            return 0;
        int num = 0;
        for (int i = 0; i < points.length; i++) {
            for (int j = 0; j < points.length; j++) {
                if (i == j)
                    continue;
                for (int k = 0; k < points.length; k++) {
                    if (i == k || j == k)
                        continue;
                    if (getDistance(points[i], points[j]) == getDistance(points[i], points[k])) {
                        System.out.println("ijk " + i + " " + j + " " + k);
                        num++;
                    }
                }
            }
        }
        return num;
    }

    //wrong way to get distance
    public int getDistance(int[] a, int[] b) {
        return Math.abs(a[0] - b[0]) + Math.abs(a[1] - b[1]);
    }

    public int numberOfBoomerangs1(int[][] points) {
        int result = 0;
        Map<Integer, Integer>  map = new HashMap<>();
        for (int i = 0; i < points.length; i++) {
            for (int j = 0; j < points.length; j++) {
                if (i == j) continue;
                int distance = getDistance1(points[i], points[j]);
                map.put(distance, map.getOrDefault(distance, 0) + 1);
            }
            for (int val : map.values()) {
                result += val * (val - 1); //满足条件的点的排列组合结果数
            }
            map.clear();
        }

        return result;
    }
    public int getDistance1(int[] point1, int[] point2){
        int x = point1[0] - point2[0];
        int y = point1[1] - point2[1];
        return x*x + y*y;
    }
    @Test
    public void test() {
        int[] i0 = new int[]{0, 0};
        int[] i1 = new int[]{1, 0};
        int[] i4 = new int[]{0, -1};
        System.out.println(Math.abs(i0[1] - i4[1]));

    }

    public int recursion(int num) {//利用递归计算阶乘
        int sum = 1;
        if (num < 0)
            throw new IllegalArgumentException("必须为正整数!");//抛出不合理参数异常
        if (num == 1) {
            return 1;//根据条件,跳出循环
        } else {
            sum = num * recursion(num - 1);//运用递归计算
            return sum;
        }
    }
}
