package leetcode_solutions;

import java.util.*;

public class TopKFrequentElements347 {
    class Solution {
        private final Map<Integer, Integer> counts = new HashMap<>();

        public List<Integer> topKFrequent(int[] nums, int k) {
            for (int i = 0; i < nums.length; i++) {
                counts.put(nums[i], !counts.containsKey(nums[i]) ? 1 : counts.get(nums[i]) + 1);
            }
            //根据value排序
            List<Map.Entry<Integer, Integer>> list = new ArrayList<Map.Entry<Integer, Integer>>(counts.entrySet());
            //collections.sort()
            Collections.sort(list, (o1, o2) -> o2.getValue().compareTo(o1.getValue()));

            List<Integer> re = new ArrayList<>(k);
            for (int i = 0; i < k; i++) {
                re.add(list.get(i).getKey());
            }

            return re;
        }
    }

    class Solution8ms {
        class Pair implements Comparable<Pair> {
            int n1;
            int n2;

            public Pair(int n1, int n2) {
                this.n1 = n1;
                this.n2 = n2;
            }

            public int compareTo(Pair o) {
                if (this.n2 < o.n2) {
                    return 1;
                } else if (this.n2 > o.n2) {
                    return -1;
                } else {
                    return 0;
                }
            }
        }

        public List<Integer> topKFrequent(int[] nums, int k) {
            List<Integer> result = new LinkedList<>();
            //非法情况
            if (nums.length <= 0 || k < 1) {
                return result;
            }
            //当数组只有一个元素时，特殊处理
            if (nums.length == 1) {
                result.add(nums[0]);
                return result;
            }
            //对数组进行排序，方便后面的操作
            Arrays.sort(nums);
            PriorityQueue<Pair> queue = new PriorityQueue<>();
            int ret = 1;
            //作用是如果数组所有元素都是同一个值的时候，没有进行 offer 操作。用 flag 可以作为标记。
            int flag = 0;
            for (int i = 0; i < nums.length - 1; i++) {
                if (nums[i] == nums[i + 1]) {
                    ret++;
                    continue;
                }
                queue.offer(new Pair(nums[i], ret));
                flag = 1;
                ret = 1;
            }
            //当数组所有元素都是同一个值的时候，进行特殊处理。
            if (flag == 0) {
                queue.offer(new Pair(nums[0], ret));
            }
            //因为上面的循环没有处理最后一种元素（一种元素即值相等的元素）的情况，在这里进行处理
            if (nums[nums.length - 1] != nums[nums.length - 2]) {
                queue.offer(new Pair(nums[nums.length - 1], 1));
            } else {
                queue.offer(new Pair(nums[nums.length - 1], ret));
            }
            //进行出优先队列操作
            while (k > 0) {
                Pair pair = queue.poll();
                result.add(pair.n1);
                k--;
            }
            return result;
        }
    }

    //hashmap+最小堆
    class Solution1 {
        public List<Integer> topKFrequent(int[] nums, int k) {
            // 使用字典，统计每个元素出现的次数，元素为键，元素出现的次数为值
            HashMap<Integer, Integer> map = new HashMap();
            for (int num : nums) {
                if (map.containsKey(num)) {
                    map.put(num, map.get(num) + 1);
                } else {
                    map.put(num, 1);
                }
            }
            // 遍历map，用最小堆保存频率最大的k个元素
            PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {
                @Override
                public int compare(Integer a, Integer b) {
                    return map.get(a) - map.get(b);
                }
            });
            for (Integer key : map.keySet()) {
                if (pq.size() < k) {
                    pq.add(key);
                } else if (map.get(key) > map.get(pq.peek())) {
                    pq.remove();
                    pq.add(key);
                }
            }
            // 取出最小堆中的元素
            List<Integer> res = new ArrayList<>();
            while (!pq.isEmpty()) {
                res.add(pq.remove());
            }
            return res;
        }
    }

    //基于桶排序求解「前 K 个高频元素」
    class Solution2 {
        public List<Integer> topKFrequent(int[] nums, int k) {
            List<Integer> res = new ArrayList();
            // 使用字典，统计每个元素出现的次数，元素为键，元素出现的次数为值
            HashMap<Integer, Integer> map = new HashMap();
            for (int num : nums) {
                if (map.containsKey(num)) {
                    map.put(num, map.get(num) + 1);
                } else {
                    map.put(num, 1);
                }
            }

            //桶排序
            //将频率作为数组下标，对于出现频率不同的数字集合，存入对应的数组下标
            List<Integer>[] list = new List[nums.length + 1];
            for (int key : map.keySet()) {
                // 获取出现的次数作为下标
                int i = map.get(key);
                if (list[i] == null) {
                    list[i] = new ArrayList();
                }
                list[i].add(key);
            }

            // 倒序遍历数组获取出现顺序从大到小的排列
            for (int i = list.length - 1; i >= 0 && res.size() < k; i--) {
                if (list[i] == null) continue;
                res.addAll(list[i]);
            }
            return res;
        }
    }


}
