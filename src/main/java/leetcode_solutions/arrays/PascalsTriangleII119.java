package leetcode_solutions.arrays;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class PascalsTriangleII119 {
    public List<Integer> getRow(int rowIndex) {
        List<Integer> list = new ArrayList<>(rowIndex + 1);
        long temp = 1;
        long temp2=1;
        list.add(1);
        for (int i = 1; i < rowIndex + 1; i++) {
            temp = temp * (rowIndex - i + 1);
            temp2=temp2*i;
            list.add((int)(temp / temp2));
        }
        return list;
    }

    @Test
    public void test(){
        List<Integer> list =getRow(13);
        System.out.println(list);
    }

    public List<Integer> getRow0(int rowIndex) {
        long nCk = 1;
        List<Integer> result = new ArrayList<Integer>();
        for(int i=0;i<=rowIndex;i++){
            result.add((int)nCk);
            nCk = nCk *(rowIndex-i)/(i+1);
        }
        return result;
    }
}
