package leetcode_solutions;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

/**
 * Created by Administrator on 2017/12/4.
 */
public class BinaryWatch401 {

  //exhaustion, last way to go
  public List<String> readBinaryWatch(int num) {
    List<String> list = new ArrayList<>();
    if (num > 8) {
      return list;
    }
    for (int i = 0; i < 4 && i <= num; i++) {
      int j = num - i;
      System.out.println("one loop : " + i + " " + j);
      List<String> hour = getHour(i);
      List<String> minute = getMinute(j);
      if (hour != null && minute != null) {
        for (String h : hour) {
          for (String m : minute) {
            list.add(h + m);
          }
        }
      }
      System.out.println("one loop : " + i + " " + j);
    }
    return list;
  }

  public List<String> getMinute(int m) {
    List<String> list = new ArrayList<>();
    switch (m) {
      case 0:
        list.add("00");
        break;
      case 1:
        list.add("01");
        list.add("02");
        list.add("04");
        list.add("08");
        list.add("16");
        list.add("32");
        break;
      case 2:
        list.add("03");
        list.add("05");
        list.add("06");
        list.add("09");
        list.add("10");
        list.add("12");
        list.add("17");
        list.add("18");
        list.add("20");
        list.add("24");
        list.add("33");
        list.add("34");
        list.add("36");
        list.add("40");
        list.add("48");
        break;
      case 3:
        list.add("07");
        list.add("11");
        list.add("13");
        list.add("14");
        list.add("19");
        list.add("21");
        list.add("22");
        list.add("25");
        list.add("26");
        list.add("28");
        list.add("35");
        list.add("37");
        list.add("38");
        list.add("41");
        list.add("42");
        list.add("44");
        list.add("49");
        list.add("50");
        list.add("52");
        list.add("56");
        break;
      case 4:
        list.add("58");
        list.add("57");
        list.add("54");
        list.add("53");
        list.add("51");
        list.add("46");
        list.add("45");
        list.add("43");
        list.add("39");
        list.add("30");
        list.add("29");
        list.add("27");
        list.add("23");
        list.add("15");
        break;
      case 5:
        list.add("31");
        list.add("47");
        list.add("55");
        list.add("59");
        break;
      default:
        list = null;
        break;
    }
    return list;
  }

  public List<String> getHour(int h) {
    List<String> list = new ArrayList<>();
    switch (h) {
      case 0:
        list.add("0:");
        break;
      case 1:
        list.add("1:");
        list.add("2:");
        list.add("4:");
        list.add("8:");
        break;
      case 2:
        list.add("3:");
        list.add("5:");
        list.add("9:");
        list.add("6:");
        list.add("10:");
        break;
      case 3:
        list.add("7:");
        list.add("11:");
        break;
      default:
        list = null;
        break;
    }
    return list;
  }

  @Test
  public void test() {
    List<String> list = readBinaryWatch(6);
    for (String s : list) {
      System.out.println(s);
    }
  }

  //从二进制表示上遍历，由于小时有4位，分钟有6位，所以总共10位就可以表示所有的情况，
  // 最大的数值是0x2FF (1011 | 111111)，所以遍历从0到0x2FF之间的数值，
  // 判断1的个数是否等于num，是则截取出小时和分钟，并拼接成字符串添加到结果数组
  public List<String> readBinaryWatch1(int num) {
    List<String> times = new ArrayList<>();
    if (num < 0 || num > 8) {
      return times;
    }
//    BitSet bs=new BitSet();
    for (int i = 0; i < 0x2ff; i++) {
      if (Integer.bitCount(i) == num) {
        add(i, times);
      }
    }
    return times;
  }

  public void add(int i, List<String> times) {
    String s;
    int m = i & 0x3F;
    if (m > 59) {
      return;
    }
    int h = i >> 6 & 0xF;
    if (h > 11) {
      return;
    }
    times.add(h + (m < 10 ? ":0" : ":") + m);
  }

}
