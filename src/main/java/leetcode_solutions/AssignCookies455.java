package leetcode_solutions;

import org.junit.Test;

import java.util.Arrays;

/**
 * Created by Administrator on 2017/11/13.
 */
public class AssignCookies455 {
    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int num = 0;
        for (int i = 0, j = 0; i < g.length && j < s.length; j++) {
            if(s[j] >= g[i]){
                num++;
                i++;
                System.out.println(num);
            }
            System.out.println(num);
        }
        return num;
    }

    @Test
    public void test() {
        int[] g = new int[]{1,2};
        int[] s = new int[]{1,2,3};
        AssignCookies455 assignCookies455=new AssignCookies455();
        assignCookies455.findContentChildren(g,s);

    }
}
