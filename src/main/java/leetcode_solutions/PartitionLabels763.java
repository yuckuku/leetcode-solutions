package leetcode_solutions;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class PartitionLabels763 {
    class Solution {
        public List<Integer> partitionLabels(String S) {
            List<Integer> re = new ArrayList<>();
            char[] chs = S.toCharArray();
            for (int i = 0; i < chs.length; ) {
                int end = S.lastIndexOf(chs[i]);
                for (int j = i + 1; j <= end; j++) {
                    int tmp = S.lastIndexOf(chs[j]);
                    if (tmp > end) end = tmp;
                }
                re.add(end - i + 1);
                i = end + 1;
            }
            return re;
        }
    }

    //把lastindexof优化了
    class Solution1 {
        public List<Integer> partitionLabels(String S) {
            List<Integer> res = new ArrayList<>();
            int[] max = new int[26];//最后出现位置
            char[] chars = S.toCharArray();
            for (int i = 0; i < chars.length; i++) {
                max[chars[i] - 'a'] = i;
            }
            int cur = 0;
            while (cur < chars.length) {
                int m = max[chars[cur] - 'a'];
                int i = cur;
                while (i < m) {
                    m = Math.max(m, max[chars[i++] - 'a']);
                }
                res.add(m - cur + 1);
                cur = m + 1;
            }
            return res;
        }
    }

    @Test
    public void test() {
        String s = "ababcbacadefegdehijhklij";
        Solution solution = new Solution();
        List<Integer> re = solution.partitionLabels(s);
        System.out.println(re);
    }
}
