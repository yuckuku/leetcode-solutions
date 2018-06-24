package leetcode_solutions;

/**
 * Created by Administrator on 2017/11/8.
 */
public class RansomNote383 {
    public boolean canConstruct(String ransomNote, String magazine) {
        for (int i = 0; i < ransomNote.length(); i++) {
            if (magazine.contains(ransomNote.charAt(i) + "")) {
                magazine = magazine.substring(0, magazine.indexOf(ransomNote.charAt(i))) + magazine.substring(magazine.indexOf(ransomNote.charAt(i)) + 1);
                System.out.println(magazine);
            } else {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String ransomNote = "a";
        String magazine = "bac";
        RansomNote383 ransomNote383 = new RansomNote383();
        ransomNote383.canConstruct(ransomNote, magazine);
    }
}
