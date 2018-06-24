package leetcode_solutions;

import org.junit.*;

import java.util.*;

/**
 * Created by Administrator on 2017/10/19.
 */
public class DegreeOfAnArray697 {
    //use distance idea in array problems
    public int findShortestSubArray_leetcode(int[] nums) {
        Map<Integer, Integer> left = new HashMap(),
                right = new HashMap(), count = new HashMap();

        for (int i = 0; i < nums.length; i++) {
            int x = nums[i];
            if (left.get(x) == null) left.put(x, i);
            right.put(x, i);
            count.put(x, count.getOrDefault(x, 0) + 1);
        }

        int ans = nums.length;
        int degree = Collections.max(count.values());
        for (int x: count.keySet()) {
            if (count.get(x) == degree) {
                ans = Math.min(ans, right.get(x) - left.get(x) + 1);
            }
        }
        return ans;
    }

    public int findShortestSubArray(int[] nums) {
        //find frequency elements
        Set<Integer> set = findFrequencyElements(nums);
        //find elements and their positions
        List<Integer> list = getPositions(nums, set);
        for (Integer i : list
                ) {
            System.out.println("in list:" + i);

        }
        //find all sub arrays contains two frequency elements
        Set<int[]> subs = findAllSubArrays(list, nums);
        int shortest = Integer.MAX_VALUE;
        for (int[] i : subs
                ) {
            shortest = shortest > i.length?i.length : shortest;
            System.out.println("in subs:" + i.length);
            System.out.println(i.hashCode());
            for (int j : i
                    ) {
                System.out.println(j);
            }
        }
        return shortest;
    }

    //memory limit exceeded
    public Set<int[]> findAllSubArrays(List<Integer> list, int[] nums) {
        Set<int[]> set = new HashSet<>();
        for (int i = 0; i < list.size(); i += 2) {
            int point1 = list.get(i);
            int point2 = list.get(i + 1);
            System.out.println("point1=" + point1 + "point2=" + point2);
            for (int left = point1; left >= 0; left--) {
                for (int right = point2 + 1; right <= nums.length; right++) {
                    System.out.println("left=" + left + "right=" + right);
                    int sub[] = Arrays.copyOfRange(nums, left, right);
                    set.add(sub);
                }
            }
        }
        return set;
    }

    public List<Integer> getPositions(int[] nums, Set<Integer> set) {
        List<Integer> list = new ArrayList<>();
        for (int num : set) {
            for (int j = 0; j < nums.length; j++) {
                if (nums[j] == num) {
                    list.add(j);
                    break;
                }
            }
            for (int i = nums.length - 1; i >= 0; i--) {
                if (nums[i] == num) {
                    list.add(i);
                    break;
                }
            }
        }
        return list;
    }


    public Set<Integer> findFrequencyElements(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            if (map.containsKey(num)) {
                map.put(num, map.get(num) + 1);
            } else {
                map.put(num, 1);
            }
        }
        int frequency = 0;
        Set<Integer> set = new HashSet<>();
        for (Integer i : map.keySet()) {
            if (map.get(i) == frequency) {
                set.add(i);
            }
            if (map.get(i) > frequency) {
                set.clear();
                set.add(i);
                frequency = map.get(i);
            }
        }
        return set;
    }

    @Test
    public void test() {
        int[] nums = new int[]{1};
        DegreeOfAnArray697 degree = new DegreeOfAnArray697();
        int numbers = degree.findShortestSubArray(nums);
        System.out.println(numbers);
    }

    @Test
    public void testif() {
        int i = 1, j = 10;
        do {
            System.out.println("before if" + "i=" + i + " and j=" + j);
            if (i++ > --j) {
                System.out.println("continues!");
                continue;
            }
            System.out.println("after if" + "i=" + i + " and j=" + j);
        } while (i < 7);
        System.out.println("i=" + i + " and j=" + j);
    }
}
