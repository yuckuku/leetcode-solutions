package leetcode_solutions.arrays;

import org.junit.Test;

import java.util.Arrays;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

/**
 * @author: L'Nan
 * Date: 2019/5/30 22:38
 * Description:
 */
public class FriendsOfAppropriateAges825 {
    class Solution {
        public int numFriendRequests(int[] ages) {
            int re = 0;
            int len = ages.length;
            Map<Integer, Integer> map = new TreeMap<>();
//            Arrays.sort(ages);
            int i = 0;
            for (; i < len; i++) {
                if (ages[i] >= 15) map.put(ages[i], map.getOrDefault(ages[i], 0) + 1);
            }

            int left = 15;
            boolean flag = true;
            for (int j = 15; j <= 120; j++) {
                if (map.containsKey(j)) {
                    int value = map.get(j);
                    NavigableMap<Integer, Integer> navigableMap = ((TreeMap<Integer, Integer>) map).subMap(left, true, j, false);
                    int jFriends = 0;
                    for (Map.Entry<Integer, Integer> entry : navigableMap.entrySet()) {
                        jFriends += entry.getValue();
                    }
                    if (value == 1) {
                        re += jFriends;
                    } else {
                        jFriends += map.get(j);
                        jFriends--;
                        re += (jFriends * map.get(j));
                    }
                }
                if (flag) left++;
                flag = !flag;
            }
            return re;
        }

        private boolean friendRequest(int a, int b) {
            if (b <= a && b > ((a >> 1) + 7)) {
                return true;
            }
            return false;
        }
    }

    class SolutionOnLeetcode1 {
        public  int numFriendRequests(int[] ages) {
            int count = 0;
            int[] allAge = new int[121];
            for (int age : ages) {
                allAge[age]++;
            }

            for (int i = 120; i >= 0; i--) {
                if (allAge[i] > 0) {

                    double limit = (0.5 * i) + 7;
                    if (i > limit) {
                        count += allAge[i] * (allAge[i] - 1);
                    }
                    for (int j = i - 1; j > limit; j--) {
                        count += allAge[i] * allAge[j];
                    }
                }

            }
            return count;
        }
    }

    @Test
    public void test() {
        int[] ages = new int[]{16, 17, 18};
        Solution s = new Solution();
        int re = s.numFriendRequests(ages);
        System.out.println(re);
    }
}
