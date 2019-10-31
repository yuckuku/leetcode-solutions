package leetcode_solutions;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class LetterCombinationsofaPhoneNumber17 {
    class Solution {
        public List<String> letterCombinations(String digits) {
            char[][] mtrx = new char[][]{{}, {}, {'a', 'b', 'c'}, {'d', 'e', 'f'}, {'g', 'h', 'i'}, {'j', 'k', 'l'}, {'m', 'n', 'o'}, {'p', 'q', 'r', 's'}, {'t', 'u', 'v'}, {'w', 'x', 'y', 'z'}};
            char[] chs = digits.toCharArray();
            char[][] t = new char[chs.length][];
            for (int i = 0; i < chs.length; i++) {
                int index = (int) chs[i] - 48;
                t[i] = mtrx[index];
            }
            List<String> re = descartes(t);
            return re;
        }

        /**
         * 计算 多个集合的笛卡尔积
         *
         * @param mtrx 存储多个集合的 二维list
         * @return
         */
        private List<String> descartes(char[][] mtrx) {
            List<String> result = new ArrayList<String>();
            for (int i = 0; i < mtrx.length; i++) {
                char[] curList = mtrx[i];
                if (0 == i) {//如果是首个集合，直接放输入到结果集中
                    for (char ch : curList) {
                        result.add(String.valueOf(ch));
                    }
                    continue;
                }
                selfCopy(result, curList);//将前一个集合的乘积 result，自我复制 curListCount 份，并将当前集合的元素追加到上边
            }
            return result;
        }

        /**
         * 根据当前的集合，将之前的结果集复制
         *
         * @param result  　　之前的集合相称的结果集
         * @param curList 　　当前集合
         */
        private void selfCopy(List<String> result, char[] curList) {
            List<String> tempList = new ArrayList<String>();
            for (char strOfCurList : curList) {
                for (String strOfResult : result) {
                    tempList.add(strOfResult + strOfCurList);//因为这里是字符串集合相称，那么其实就是字符串相加。
                }
            }

            result.clear();
            for (String tempStr : tempList) {
                result.add(tempStr);
            }
        }
    }

    class Solution1 {

        List<String> ans = new ArrayList();
        String[] map = new String[]{"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

        public void backtrack(String have, String tobe) {
            if (tobe.length() == 0) ans.add(have);
            else {
                char digit = tobe.charAt(0);
                String options = map[digit - '0'];
                for (int i = 0; i < options.length(); i++) {
                    backtrack(have + options.substring(i, i + 1), tobe.substring(1));
                }
            }
        }

        public List<String> letterCombinations(String digits) {
            if (digits.length() != 0)
                backtrack("", digits);
            return ans;
        }
    }

    class Solution2 {
        String[] stringMap = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        private List<String> result = new ArrayList<>();

        public List<String> letterCombinations(String digits) {
            if (digits.equals(""))
                return result;
            findCombinations(digits, 0, "");
            return result;
        }

        private void findCombinations(String digits, int index, String s) {
            if (index == digits.length()) {
                result.add(s);
                return;
            }

            char c = digits.charAt(index);
            String letters = stringMap[c - '0'];
            for (int i = 0; i < letters.length(); i++)
                findCombinations(digits, index + 1, s + letters.charAt(i));
        }
    }


    @Test
    public void test() {
        System.out.println("aaa" + 'c');
        System.out.println((int) '0');
        System.out.println((int) '9');
        String digits = "23";
        List<String> re = new Solution().letterCombinations(digits);
        System.out.println(re);
    }
}
