package leetcode_solutions;

/**
 * Created by Administrator on 2017/10/26.
 */
public class RangeAdditionII598 {
    public int maxCount(int m, int n, int[][] ops) {
        for (int[] op : ops
                ) {
            m = Math.min(m, op[0]);
            n = Math.max(n, op[1]);
        }
        return m * n;
    }

}
