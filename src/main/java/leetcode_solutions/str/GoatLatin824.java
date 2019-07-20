package leetcode_solutions.str;

public class GoatLatin824 {
    class Solution {
        public String toGoatLatin(String S) {
            StringBuilder sb = new StringBuilder();
            String[] ss = S.split(" ");
            for (int i = 0; i < ss.length - 1; i++) {
                sb.append(goatLatin(ss[i], i + 1)).append(" ");
            }
            sb.append(goatLatin(ss[ss.length - 1], ss.length));
            return sb.toString();
        }

        private char[] goatLatin(String s, int i) {
            char[] chs = s.toCharArray();
            int len = chs.length;
            char[] tmp = new char[len + i + 2];
            if (chs[0] == 'a' || chs[0] == 'e' || chs[0] == 'i' || chs[0] == 'o' || chs[0] == 'u'
                    || chs[0] == 'A' || chs[0] == 'E' || chs[0] == 'I' || chs[0] == 'O' || chs[0] == 'U') {
                System.arraycopy(chs, 0, tmp, 0, len);
            } else {
                System.arraycopy(chs, 1, tmp, 0, len - 1);
                tmp[len - 1] = chs[0];
            }
            tmp[len] = 'm';
            for (int j = 0; j < i + 1; j++) {
                tmp[len + i + 1 - j] = 'a';
            }
            return tmp;
        }
    }

    class SolutionOnLeetcode1 {
        public String toGoatLatin(String S) {
            StringBuilder sb = new StringBuilder();
            int k = 1;
            for (String s : S.split(" ")) {
                //1.判断元音还是辅音
                switch (s.charAt(0)) {
                    case 'a':
                    case 'e':
                    case 'i':
                    case 'o':
                    case 'u':
                    case 'A':
                    case 'E':
                    case 'I':
                    case 'O':
                    case 'U':
                        sb.append(s);
                        break;
                    default:
                        sb.append(s.substring(1) + s.charAt(0));
                }
                //2.添加"ma"、"a"、空格
                sb.append("ma");
                for (int i = 0; i < k; i++) {
                    sb.append("a");
                }
                k++;
                sb.append(" ");
            }
            //3.删除最后一个空格
            if (sb.length() > 0) {
                sb.deleteCharAt(sb.length() - 1);
            }
            return sb.toString();
        }
    }
}
