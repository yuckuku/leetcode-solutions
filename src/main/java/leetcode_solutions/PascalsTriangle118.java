package leetcode_solutions;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class PascalsTriangle118 {
    public List<List<Integer>> generate(int numRows) {
        if (numRows < 1) {
            return new ArrayList<>();
        } else if (numRows == 1) {
            List<Integer> list = new ArrayList<>();
            list.add(1);
            List<List<Integer>> re = new ArrayList<>();
            re.add(list);
            return re;
        } else {
            List<List<Integer>> re = generate(numRows - 1);
            List<Integer> preList = re.get(re.size() - 1);
            List<Integer> list = new ArrayList<>();
            list.add(preList.get(0));
            for (int i = 0; i < preList.size() - 1; i++) {
                list.add(preList.get(i) + preList.get(i + 1));
            }
            list.add(preList.get(preList.size() - 1));
            re.add(list);
            return re;
        }
    }

    public List<List<Integer>> generate0(int numRows) {
        List<List<Integer>> result = new ArrayList<List<Integer>>(numRows);
        for (int i = 0; i < numRows; i++) {
            List<Integer> thisRow = new ArrayList<Integer>(i);
            thisRow.add(1);
            int temp = 1;
            int row = i;
            for (int j = 1; j <= i; j++) {
                temp = temp * row-- / j;
                thisRow.add(temp);
            }
            result.add(thisRow);
        }
        return result;
    }

    @Test
    public void test() {
        int i = 3;
        int temp = 1;
        int row = i;
        for (int j = 1; j <= i; j++) {
            System.out.println();
            temp = temp * row-- / j;
            System.out.println(temp);
        }
    }
}
