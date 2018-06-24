package leetcode_solutions;

/**
 * Created by Administrator on 2017/12/12.
 */
public class Base7504 {

  public String convertToBase7(int num) {
    StringBuffer stringBuffer = new StringBuffer();
    if (num < 0) {
      stringBuffer.append("-");
    } else if (num == 0) {
      stringBuffer.append("0");
    } else {
      stringBuffer.append(" ");
    }
    num = Math.abs(num);
    int remainder = 0;
    while (num != 0) {
      remainder = num % 7;
      num = num / 7;
      stringBuffer.insert(1, remainder);
    }
    if (stringBuffer.charAt(0) == ' ') {
      return stringBuffer.subSequence(1, stringBuffer.length()).toString();
    } else {
      return stringBuffer.toString();
    }
//    return stringBuffer.toString().trim();
  }
}
