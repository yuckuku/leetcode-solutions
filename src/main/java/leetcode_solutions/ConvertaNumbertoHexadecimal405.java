package leetcode_solutions;

import org.junit.Test;

public class ConvertaNumbertoHexadecimal405 {

    public String toHex(int num) {
        StringBuffer sb = new StringBuffer();
        if (num > 0) {
            while (num / 16 != 0) {
                String temp = getLastC(num);
                sb.append(temp);
                num = num / 16;
            }
            String temp = getLastC(num);
            sb.append(temp);
        } else if (num == 0) {
            return "";
        } else {
            String temp = toHex(Integer.MAX_VALUE + num + 1);

            return "f" + temp.substring(1, temp.length());
        }
        return sb.reverse().toString();
    }

    private String getLastC(int num) {
        int remainer = num % 16;
        String re;
        if (remainer > 9) {
            remainer += 87;
            re = String.valueOf((char) Integer.parseInt(String.valueOf(remainer)));
        } else {
            re = String.valueOf(remainer);
        }
        return re;
    }

    @Test
    public void test() {
//        System.out.println(Integer.valueOf('a'));
//        System.out.println((char) Integer.parseInt("97"));
//        System.out.println(String.valueOf((char) Integer.parseInt("97")));
//        System.out.println(getLastC(10));
        System.out.println(toHex(-1));
    }

    public String toHex0(int num) {
        String hexString = "";
        String hexChar = "0123456789abcdef";
        int cnt = 0;
        while (num != 0 ) {
            System.out.println(hexChar.charAt(num & 0xF));
            hexString = hexChar.charAt(num & 0xF) + hexString;
            num = (num >>> 4);
            System.out.println("num:"+num);
        }

        return hexString.isEmpty() ? "0" : hexString;

    }

    @Test
    public void test0() {
        System.out.println(toHex0(-2147483648));
        System.out.println(-1 & 0xF);
    }
}
