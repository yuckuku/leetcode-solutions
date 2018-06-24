package leetcode_solutions;

import org.junit.Test;

/**
 * Created by Administrator on 2017/11/14.
 */
public class TwoSumII_Inputarrayissorted167 {
    public int[] twoSum(int[] numbers, int target) {
        int index1 = 0;
        int index2 = numbers.length - 1;
        while(index1!=index2){
            System.out.println(" numbers: "+numbers[index1]+" "+numbers[index2]);
            if ((numbers[index1] + numbers[index2] )== target) {
                System.out.println("break");
                break;
            }
            if (numbers[index1] + numbers[index2] > target) {
                System.out.println("index2--");
                index2--;
            }
            if (numbers[index1] + numbers[index2] < target) {
                System.out.println("index1++");
                index1++;
            }
            System.out.println(index1+" "+index2);
        }
        return new int[]{index1 + 1, index2 + 1};
    }

    @Test
    public void test() {
        int[] numbers = new int[]{0,0,3,4};
        int target = -1;
        TwoSumII_Inputarrayissorted167 twoSumII_inputarrayissorted167 =
                new TwoSumII_Inputarrayissorted167();
        twoSumII_inputarrayissorted167.twoSum(numbers,target);
    }
}
