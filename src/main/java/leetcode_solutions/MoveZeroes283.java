package leetcode_solutions;

/**
 * Created by Administrator on 2017/10/19.
 */
public class MoveZeroes283 {

    //note differences between i++ and ++i
    public static void moveZeroes(int[] nums) {
        int point = 0;
        for(int i=0;i<nums.length;i++){
            if(0!=nums[i]){
                System.out.println(nums[i] + ":" + point);
                nums[point++]= nums[i];
            }
        }
        for(;point<nums.length;point++){
            System.out.println("0:" + point);
            nums[point]=0;
        }
        for (int i :nums) {
            System.out.println(i);
        }
    }

    public static void main(String[] args) {
        int[] nums = new int[]{0,1,0,3,12};
        moveZeroes(nums);

    }
}
