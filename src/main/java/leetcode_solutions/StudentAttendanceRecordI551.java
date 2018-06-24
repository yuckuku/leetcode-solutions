package leetcode_solutions;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.junit.Test;

/**
 * Created by Administrator on 2017/12/11.
 */
public class StudentAttendanceRecordI551 {

  public boolean checkRecord0(String s) {
    String reg = ".*LLL.*";
    if (s.matches(reg)) {
      return false;
    }
    reg = ".*A.*A.*";
    if (s.matches(reg)) {
      return false;
    }
    return true;
  }

  @Test
  public void test() {
    String s = "011111a";
    Pattern p = Pattern.compile("(\\w)\\1{3,}");
    Matcher m = p.matcher(s);
    System.out.println((m.find() ? "true " + m.group() : "false "));
    String str = "Hello World";  //待判断的字符串
    String reg = ".*ll.*a.*";  //判断字符串中是否含有ll
    System.out.println(str.matches(reg));
  }
}
