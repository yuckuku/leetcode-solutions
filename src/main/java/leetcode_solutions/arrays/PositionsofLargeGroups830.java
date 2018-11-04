package leetcode_solutions.arrays;

import java.util.ArrayList;
import java.util.List;

public class PositionsofLargeGroups830 {
    public List<List<Integer>> largeGroupPositions(String S) {

        char[] chs = S.toCharArray();
        List<List<Integer>> ans = new ArrayList<>();
        int start = 0;
        for (int i = 1; i < chs.length; i++) {
            if (chs[i] == chs[start]) {
                continue;
            } else {
                if (i - start >= 3) {
                    List<Integer> list = new ArrayList<>();
                    list.add(start);
                    list.add(i - 1);
                    ans.add(list);
                    start = i;
                } else {
                    start = i;
                }
            }
        }

        if (chs.length - start >= 3) {
            List<Integer> list = new ArrayList<>();
            list.add(start);
            list.add(chs.length - 1);
            ans.add(list);
        }

        return ans;
    }
}
