package leetcode_solutions;

import org.junit.Test;

/**
 * Created by Administrator on 2017/12/12.
 */
public class ReverseStringII541 {

  @Test
  public void test() {
    String re = reverseStr0("abcdefg", 2);
    System.out.println("original: abcdefg");
    System.out.println("result:   " + re);
  }

  public String reverseStr0(String s, int k) {
    StringBuffer sb = new StringBuffer();
    StringBuffer reverseSb = new StringBuffer();
    for (int i = 0; i < s.length(); i += 2 * k) {
      if (s.length() <= i + k) {
        reverseSb.append(s.substring(i, s.length()));
        sb.append(reverseSb.reverse());
        reverseSb.setLength(0);
      } else if (s.length() > i + k && s.length() <= i + 2 * k) {
        reverseSb.append(s.substring(i, i + k));
        sb.append(reverseSb.reverse());
        sb.append(s.substring(i + k, s.length()));
        reverseSb.setLength(0);
      } else {
        reverseSb.append(s.substring(i, i + k));
        sb.append(reverseSb.reverse());
        sb.append(s.substring(i + k, i + 2 * k));
        reverseSb.setLength(0);
      }
    }
    return sb.toString();
  }

  public String reverseStr1(String s, int k) {
    if (s == null || s.length() == 0) {
      return "";
    }

    int len = s.length();
    int i = 0;
    char[] arr = s.toCharArray();
    while (i < len) {
      int j = Math.min(i + k - 1, len - 1);
      swap(arr, i, j);
      i = i + (2 * k);
    }
    return new String(arr);
  }

  private void swap(char[] arr, int l, int r) {
    while (l < r) {
      char tmp = arr[l];
      arr[l] = arr[r];
      arr[r] = tmp;
      l++;
      r--;
    }
  }

}
