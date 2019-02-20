package leetcode_solutions.arrays;

import org.junit.Test;

import javax.swing.text.StyledEditorKit;
import java.util.Arrays;
import java.util.List;

/**
 * @Auther: L'Nan
 * @Date: 2018/11/8 17:22
 * @Description:
 */
public class TrappingRainWater42 {
    //my solution:wrong answer, did not consider two big value on both side of two low points
    public int trap(int[] height) {
        if (null == height || height.length < 3) {
            return 0;
        }

        //find low point
        int sum = 0;//sum to return
        for (int i = 1; i < height.length - 1; i++) {
            if (height[i] <= height[i - 1] && height[i] <= height[i + 1]) {
                int solidSum = height[i];//sum solid

                int j = i - 1;//left
                int k = i + 1;//right
                for (; j >= 0; j--) {
                    if (height[j] >= height[j + 1]) {
                        solidSum += height[j];
                    } else {
                        j++;
                        break;
                    }
                }
                for (; k <= height.length - 1; k++) {
                    if (height[k] >= height[k - 1]) {
                        solidSum += height[k];
                        i++;
                    } else {
                        k--;
                        break;
                    }
                }

                j = Math.max(0, j);
                k = Math.min(height.length - 1, k);

                if (height[j] == height[k]) {
                    sum += height[j] * (k - j + 1) - solidSum;
                } else if (height[j] > height[k]) {
                    while (height[j] > height[k]) {
                        solidSum -= height[j];
                        j++;
                    }
                    sum += Math.max(height[j], height[k]) * (k - j + 1) - solidSum;
                } else {
                    while (height[k] > height[j]) {
                        solidSum -= height[k];
                        k--;
                    }
                    sum += Math.max(height[j], height[k]) * (k - j + 1) - solidSum;
                }
            }
        }

        return sum;
    }


    /**
     * 观察下就可以发现被水填满后的形状是先升后降的塔形，
     * 因此，先遍历一遍找到塔顶，然后分别从两边开始，往塔顶所在位置遍历，
     * 水位只会增高不会减小，且一直和最近遇到的最大高度持平，这样知道了实时水位，就可以边遍历边计算面积。
     */
    class Solution {
        public int trap(int[] height) {

            if (height.length <= 2) return 0;
            int max = -1, maxInd = 0;
            int i = 0;
            for (; i < height.length; ++i) {
                if (height[i] > max) {
                    max = height[i];
                    maxInd = i;
                }
            }

            int area = 0, root = height[0];
            for (i = 0; i < maxInd; ++i) {
                if (root < height[i]) root = height[i];
                else area += (root - height[i]);
            }
            for (i = height.length - 1, root = height[height.length - 1]; i > maxInd; --i) {
                if (root < height[i]) root = height[i];
                else area += (root - height[i]);
            }
            return area;
        }
    }

    class Solution0 {
        public int trap(int[] height) {
            int lastIdx = 0;
            int res = 0;
            for (int i = lastIdx + 1; i < height.length; i++) {
                while (i < height.length && height[i] < height[lastIdx]) i++;
                if (i == height.length) break;
                for (int j = lastIdx + 1; j < i; j++)
                    res += height[lastIdx] - height[j];
                lastIdx = i;
            }
            int end = lastIdx;
            lastIdx = height.length - 1;
            for (int i = lastIdx - 1; i >= end; i--) {
                while (i >= end && height[i] < height[lastIdx]) i--;
                if (i == end - 1) break;
                for (int j = lastIdx - 1; j > i; j--)
                    res += height[lastIdx] - height[j];
                lastIdx = i;
            }
            return res;
        }
    }

    @Test
    public void test() {
        //ans=6
        int[] nums = new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};

        //ans=2
//        int[] nums = new int[]{2, 0, 2};

        //ans=14
//        int[] nums = new int[]{5, 2, 1, 2, 1, 5};

        int re = new Solution0().trap(nums);
        System.out.println(re);
    }

}
