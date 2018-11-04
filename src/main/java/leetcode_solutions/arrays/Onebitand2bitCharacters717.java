package leetcode_solutions.arrays;

/**
 * 1-bit and 2-bit Characters
 * 1比特与2比特字符
 */
public class Onebitand2bitCharacters717 {
    //my solution:found regular pattern
    public boolean isOneBitCharacter(int[] bits) {
        boolean flag = true;
        for (int i = bits.length - 2; i >= 0; i--) {
            if (bits[i] == 0) {
                return flag;
            }
            if (bits[i] == 1) {
                flag = !flag;
            }
        }
        return flag;
    }

    public boolean isOneBitCharacter0(int[] bits) {
        int i = 0;
        while (i < bits.length - 1) {
            i += bits[i] + 1;
        }
        return i == bits.length - 1;
    }
}
