package leetcode_solutions;

/**
 * Created by Administrator on 2017/10/16.
 */
public class DetectCapital520 {

    public boolean detectCapitalUse(String word) {

        String regex_upper = "^[A-Z]+$";
        if (word.matches(regex_upper)) {
            return true;
        }
        String regex_lower = "^[a-z]+$";
        if (word.matches(regex_lower)) {
            return true;
        }
        if (word.length() > 1 && word.substring(0, 1).matches(regex_upper) && word.substring(1).matches(regex_lower)) {
            return true;
        }
        return false;
    }
}
