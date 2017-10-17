/**
 * Created by Administrator on 2017/10/16.
 */
public class CountBinarySubstrings696 {

    public int countBinarySubstrings(String s) {

        list<String> list = findAllSubWithEvenElements( s);
        if(null == list){
            return 0;
        }
        int num=0;
        for(String s:list){
            if(check(s)){
                num++;
            }
        }
        return num;
    }

    public List<String> findAllSubWithEvenElements(String s){
        if(null == str ||str.length<2){
            return null;
        }
        List<String> list = new ArrayList<String>();
        for(int len =2;len <=str.length();len=len+2) {
            for (int i = 0; i <=str.length() - len ; i++) {
                list.add(str.substring(i, i + len));
            }
        }
        return list;
    }

    public boolean check(String s){
        if(s.length%2!=0){
            return false;
        }
        String regex_1 = "[1]+";
        String regex_0 = "[0]+";
        if(s.substring(0,s.length/2).matches(regex_1)&&s.substring(s.length/2).matches(regex_0 )){
            return true;
        }
        if(s.substring(0,s.length/2).matches(regex_0)&&s.substring(s.length/2).matches(regex_1 )){
            return true;
        }
        return false;
    }

    //recommanded solution
    public int countBinarySubstrings(String s) {
        int[] groups = new int[s.length()];
        int t = 0;
        groups[0] = 1;
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i-1) != s.charAt(i)) {
                groups[++t] = 1;
            } else {
                groups[t]++;
            }
        }

        for(int i:groups){
            System.out.println(i);

        }

        int ans = 0;
        for (int i = 1; i <= t; i++) {
            ans += Math.min(groups[i-1], groups[i]);
        }
        return ans;
    }

    public static void main(String[] args){
        String s = "00110011";
        int num = countBinarySubstrings(s);
    }

}