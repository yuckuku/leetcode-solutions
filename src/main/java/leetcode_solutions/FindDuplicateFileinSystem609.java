package leetcode_solutions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FindDuplicateFileinSystem609 {
    class Solution {
        public List<List<String>> findDuplicate(String[] paths) {
            Map<String, List<String>> map = new HashMap<>();
            for (int i = 0; i < paths.length; i++) {
                //"root/a 1.txt(abcd) 2.txt(efgh)"
                char[] chs = paths[i].toCharArray();
                int j = 0;
                for (; j < chs.length; j++) {
                    if (chs[j] == ' ') break;
                }
                String head = String.valueOf(chs, 0, j++);
                int l = j;
                String p = "";
                String key = "";
                while (j < chs.length) {
                    if (chs[j] == '(') {
                        p = String.valueOf(chs, l, j - l);
                        j++;
                        l = j;
                    }
                    if (chs[j] == ')') {
                        key = String.valueOf(chs, l, j - l);
                        if (map.containsKey(key)) {
                            map.get(key).add(head + "/" + p);
                        } else {
                            List<String> list = new ArrayList<>();
                            list.add(head + "/" + p);
                            map.put(key, list);
                        }
                        j += 2;
                        l = j;
                    }
                    j++;
                }
            }
            List<List<String>> re = new ArrayList<>();
            for (List<String> list : map.values()) {
                if (list.size() > 1) re.add(list);
            }
            return re;
        }
    }
}
