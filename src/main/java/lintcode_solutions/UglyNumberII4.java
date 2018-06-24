package lintcode_solutions;

import java.util.ArrayList;
import java.util.List;

public class UglyNumberII4 {
    //find a pattern
    /**
     * @param n: An integer
     * @return: the nth prime number as description.
     */
    public int nthUglyNumber(int n) {
        // write your code here
        List<Integer> list = new ArrayList<>();
        list.add(1);
        int cur = 2;

        int i1= 0,i2 = 0,i3 = 0;
        int min1,min2,min3;
        while (list.size()<n){
            while(list.get(i1)*2<cur) i1++;
            min1 = list.get(i1)*2;
            while(list.get(i2)*3<cur) i2++;
            min2 = list.get(i2)*3;
            while(list.get(i3)*5<cur) i3++;
            min3 = list.get(i3)*5;

            int next = min1<min2?min1:min2;
            next = next<min3?next:min3;

            cur = next+1;
            list.add(next);
        }
        return list.get(n-1);
    }
}
