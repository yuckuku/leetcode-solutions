package leetcode_solutions.arrays;

import javax.swing.text.StyledEditorKit;
import java.util.*;

public class TaskScheduler621 {

    //too slow
    public int leastInterval(char[] tasks, int n) {
        if (n < 1) {
            return tasks.length;
        }

        Map<Character, Integer> map = new TreeMap<>();
        for (char task :
                tasks) {
            if (map.containsKey(task)) {
                map.put(task, map.get(task) + 1);
            } else {
                map.put(task, 1);
            }
        }

        int least = 0;
        int temp = 0;
        int num;
        while (!map.isEmpty()) {
            least = least + n + 1;
            List<Map.Entry<Character, Integer>> list = new ArrayList<>(map.entrySet());
            // 降序排序
            Collections.sort(list, (o1, o2) -> -o1.getValue().compareTo(o2.getValue()));
            if (list.size() > n) {
                temp = 0;
            } else {
                temp = n + 1 - list.size();
            }
            num = Math.min(list.size(), n + 1);

            System.out.println("list size:" + list.size());
            System.out.println("num:" + num);
            for (int i = 0; i < num; i++) {
                map.put(list.get(i).getKey(), list.get(i).getValue() - 1);
            }

//            Set<Character> set = map.keySet();
//
//            if (set.size() > n) {
//                least += set.size();
//                temp = 0;
//            } else {
//                least = least + n + 1;
//                temp = n + 1 - set.size();
//            }

//            Iterator<Map.Entry<Character, Integer>> entries = map.entrySet().iterator();
//            while (entries.hasNext()) {
//                Map.Entry<Character, Integer> entry = entries.next();
//                if (entry.getValue() > 1) {
//                    map.put(entry.getKey(), entry.getValue() - 1);
//                } else {
//                    map.remove(entry.getKey());
//                }
//            }

            Iterator<Character> iterator = map.keySet().iterator();
            while (iterator.hasNext()) {
                Character c = iterator.next();
                if (map.get(c) < 1) {
                    iterator.remove();
                }
            }

            System.out.println(least);
            System.out.println(temp);
            System.out.println(map);
//            for (Character c :
//                    set) {
//                if (map.get(c) > 1) {
//                    map.put(c, map.get(c) - 1);
//                } else {
//                    set.remove()
//                    map.remove(c);
//                }
//            }
        }
        return least - temp;
    }


    public int leastInterval0(char[] tasks, int n) {
        int k = 0, m = 0, num = 0;
        int[] nums = new int[26];
        for (char i : tasks) nums[i - 'A']++;
        for (int i : nums) {
            m += i;
            k = k > i ? k : i;
        }
        for (int i : nums) if (k == i) num++;
        int ret = (k - 1) * n + k + num - 1;

        return ret > tasks.length ? ret : tasks.length;
    }
}
