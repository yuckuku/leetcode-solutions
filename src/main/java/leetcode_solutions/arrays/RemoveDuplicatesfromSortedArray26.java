package leetcode_solutions.arrays;

import java.util.Arrays;
import java.util.BitSet;

public class RemoveDuplicatesfromSortedArray26 {
    //negative number, can not use bitset
    public int removeDuplicates(int[] nums) {
        BitSet bitset = new BitSet();
        for (int i = 0; i < nums.length; i++) {
            bitset.set(nums[i], true);
        }

        int j = -1;
        System.out.println(bitset.size());
        for (int i = 0; i < bitset.size(); i++) {

            if (bitset.get(i)) {
                System.out.println(i);
                nums[++j] = i;
            }
        }

        System.out.println(j);
        return ++j;
    }

    //my solution
    public int removeDuplicates0(int[] nums) {

        if (null == nums || nums.length < 1) {
            return 0;
        }

        Arrays.sort(nums);

        int i = 0, j = 1;
        while (j < nums.length) {
            while (j < nums.length - 1 && nums[j] == nums[i]) {
                j++;
            }
            if (j != i + 1) {
                //swap
                nums[i + 1] = nums[i + 1] ^ nums[j];
                nums[j] = nums[i + 1] ^ nums[j];
                nums[i + 1] = nums[i + 1] ^ nums[j];
            }
            i++;
            j++;
            for (int num :
                    nums) {
                System.out.print(num);
                System.out.print(" ");
            }
            System.out.println("i:" + i);
            System.out.println("j:" + j);
            System.out.println();
        }

        int k=0;
        for (; k <nums.length-1; k++) {
            if(nums[k]>=nums[k+1]){
                break;
            }
        }

        return k;
    }


    public int removeDuplicates1(int[] nums) {
        int len=nums.length;
        int j=0;
        for(int i=0;i<len;i++)
        {
            if(nums[i]!=nums[j])
            {
                j++;
                nums[j]=nums[i];
            }
        }
        return j+1;
    }
}
