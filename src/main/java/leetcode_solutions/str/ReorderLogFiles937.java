package leetcode_solutions.str;

import org.junit.Test;

import java.util.*;

public class ReorderLogFiles937 {
    class Solution {
        public String[] reorderLogFiles(String[] logs) {
            int len = logs.length;
            String[] strs = new String[len];
            String[] dgts = new String[len];
            splitToTwo(logs, strs, dgts);
            String[] re = merge(strs, dgts);
            return re;
        }

        private String[] merge(String[] strs, String[] dgts) {
            int len = strs.length;
            String[] re = new String[len];
            int i = 0;
            for (; i < len; i++) {
                if (null != strs[i]) {
                    re[i] = strs[i];
                } else {
                    break;
                }
            }
            for (int j = 0; i < len; j++, i++) {
                re[i] = dgts[j];
            }
            return re;
        }

        private void splitToTwo(String[] logs, String[] strs, String[] dgts) {
            int strIndex = 0, dgtIndex = 0;
            int i = 0;
            for (; i < logs.length; i++) {
                String[] tmp = logs[i].split(" ");
                if (tmp[tmp.length - 1].matches("^[0-9]*$")) {
                    dgts[dgtIndex++] = logs[i];
                } else {
                    strs[strIndex++] = logs[i];
                    i++;
                    break;
                }
            }

            for (; i < logs.length; i++) {
                String[] tmp = logs[i].split(" ");
                if (tmp[tmp.length - 1].matches("^[0-9]*$")) {
                    dgts[dgtIndex++] = logs[i];
                } else {
                    int left = 0, right = strIndex - 1;
                    while (left <= right) {
                        int mid = (left + right) >> 1;
                        String[] midSs = strs[mid].split(" ");
                        boolean flag = compareStrs(tmp, midSs);
                        if (flag) {
                            left = mid + 1;
                        } else {
                            right = mid - 1;
                        }
                    }
                    for (int j = strIndex; j > left; j--) {
                        strs[j] = strs[j - 1];
                    }
                    strs[left] = logs[i];
                    strIndex++;
                }
            }
        }

        private boolean compareStrs(String[] a, String[] b) {
            int aLen = a.length;
            int bLen = b.length;
            int len = Math.min(aLen, bLen);
            for (int i = 1; i < len; i++) {
                int cmp = a[i].compareTo(b[i]);
                if (cmp > 0) return true;
                else if (cmp < 0) return false;
            }
            if (aLen > bLen) return true;
            else if (aLen < bLen) return false;
            else {
                int cmp = a[0].compareTo(b[0]);
                if (cmp > 0) return true;
                else if (cmp < 0) return false;
            }
            return true;
        }
    }

    class SolutionOnLeetcode {
        public String[] reorderLogFiles(final String[] logs) {
            //第61测试用例有bug
            int idx = logs.length - 1;
            for (int i = logs.length - 1; i > -1; i--) {
                if (logs[i].charAt(logs[i].indexOf(' ') + 1) < 90) {
                    String temp = logs[idx];
                    logs[idx--] = logs[i];
                    logs[i] = temp;
                }
            }
            Arrays.sort(logs, 0, idx + 1, Comparator.comparing(e -> e.substring(e.indexOf(' ') + 1)));
            //todo:相同的按照第一位排序
            return logs;
        }
    }

    class SolutionOnLeetcode1 {
        public String[] reorderLogFiles(String[] logs) {

            List<Log> alist = new ArrayList<>();
            List<String> numList = new ArrayList<>();


            for (String log : logs) {
                int length = log.length();

                if (log.charAt(length - 1) >= 'a' && log.charAt(length - 1) <= 'z') {
                    int index = log.indexOf(' ');
                    Log alog = new Log();
                    alog.setFlag(log.substring(0, index));
                    alog.setTail(log.substring(index, log.length()));
                    alist.add(alog);
                } else {
                    numList.add(log);
                }
            }
            Collections.sort(alist);
            for (int i = alist.size() - 1; i >= 0; i--) {
                Log log = alist.get(i);
                String astring = log.getFlag() + log.getTail();
                numList.add(0, astring);
            }

            return (String[]) numList.toArray(new String[numList.size()]);
        }


        class Log implements Comparable {
            private String flag;
            private String tail;

            public Log() {
            }

            public Log(String flag, String tail) {
                this.flag = flag;
                this.tail = tail;
            }


            public String getFlag() {
                return flag;
            }

            public void setFlag(String flag) {
                this.flag = flag;
            }

            public String getTail() {
                return tail;
            }

            public void setTail(String tail) {
                this.tail = tail;
            }

            /**
             * Compares this object with the specified object for order.  Returns a
             * negative integer, zero, or a positive integer as this object is less
             * than, equal to, or greater than the specified object.
             *
             * <p>The implementor must ensure <tt>sgn(x.compareTo(y)) ==
             * -sgn(y.compareTo(x))</tt> for all <tt>x</tt> and <tt>y</tt>.  (This
             * implies that <tt>x.compareTo(y)</tt> must throw an exception iff
             * <tt>y.compareTo(x)</tt> throws an exception.)
             *
             * <p>The implementor must also ensure that the relation is transitive:
             * <tt>(x.compareTo(y)&gt;0 &amp;&amp; y.compareTo(z)&gt;0)</tt> implies
             * <tt>x.compareTo(z)&gt;0</tt>.
             *
             * <p>Finally, the implementor must ensure that <tt>x.compareTo(y)==0</tt>
             * implies that <tt>sgn(x.compareTo(z)) == sgn(y.compareTo(z))</tt>, for
             * all <tt>z</tt>.
             *
             * <p>It is strongly recommended, but <i>not</i> strictly required that
             * <tt>(x.compareTo(y)==0) == (x.equals(y))</tt>.  Generally speaking, any
             * class that implements the <tt>Comparable</tt> interface and violates
             * this condition should clearly indicate this fact.  The recommended
             * language is "Note: this class has a natural ordering that is
             * inconsistent with equals."
             *
             * <p>In the foregoing description, the notation
             * <tt>sgn(</tt><i>expression</i><tt>)</tt> designates the mathematical
             * <i>signum</i> function, which is defined to return one of <tt>-1</tt>,
             * <tt>0</tt>, or <tt>1</tt> according to whether the value of
             * <i>expression</i> is negative, zero or positive.
             *
             * @param o the object to be compared.
             * @return a negative integer, zero, or a positive integer as this object
             * is less than, equal to, or greater than the specified object.
             * @throws NullPointerException if the specified object is null
             * @throws ClassCastException   if the specified object's type prevents it
             *                              from being compared to this object.
             */
            @Override
            public int compareTo(Object o) {
                Log temp = (Log) o;
                if (this.tail.equals(temp.getTail())) {
                    return this.flag.compareTo(temp.getFlag());
                } else {

                    return this.tail.compareTo(temp.getTail());
                }
            }


            @Override
            public String toString() {
                return "Log{" +
                        "flag='" + flag + '\'' +
                        ", tail='" + tail + '\'' +
                        '}';
            }
        }
    }

    class SolutionOnLeetcode2 {
        public String[] reorderLogFiles(String[] logs) {
            List<String> letters = new ArrayList<>();
            List<String> digits = new ArrayList<>();
            for (String tmp : logs) {
                for (int i = 0; i < tmp.length(); i++) {
                    if (tmp.charAt(i) == ' ') {
                        if (tmp.charAt(i + 1) >= '0' && tmp.charAt(i + 1) <= '9') {
                            digits.add(tmp);
                        } else {
                            letters.add(tmp);
                        }
                        break;
                    }
                }
            }
            Collections.sort(letters, new Comparator<String>() {
                @Override
                public int compare(String t1, String t2) {
                    int i = 1, j = 1;
                    for (; i < t1.length(); i++) {
                        if (t1.charAt(i - 1) == ' ') {
                            break;
                        }
                    }
                    for (; j < t2.length(); j++) {
                        if (t2.charAt(j - 1) == ' ') {
                            break;
                        }
                    }
                    int t = (t1.substring(i)).compareTo(t2.substring(j));
                    if (t == 0) {
                        return (t1.substring(0, i - 1).compareTo(t2.substring(0, j - 1)));
                    }
                    return t;
                }
            });
            String res[] = new String[logs.length];
            int i = 0;
            for (String str : letters) {
                res[i++] = str;
            }
            for (String str : digits) {
                res[i++] = str;
            }
            return res;
        }
    }

    @Test
    public void test() {
        String[] logs = new String[]{"j mo", "5 m w", "g 07", "o 2 0", "t q h"};
        Solution s = new Solution();
        String[] re = s.reorderLogFiles(logs);
        System.out.println(Arrays.toString(re));
    }

    @Test
    public void test1() {
        int cmp = "mo".compareTo("m");
        System.out.println(cmp);
        cmp = "h".compareTo("mo");
        System.out.println(cmp);
        cmp = "le".compareTo("zbr");
        System.out.println(cmp);
    }
}
