package leetcode_solutions.arrays;

public class LargestRectangleinHistogram84 {
    //my solution; very like MaximalRectangle85
    public int largestRectangleArea(int[] heights) {
        int max = 0;
        for (int i = 0; i < heights.length; i++) {
            int count = 1;
            for (int k = i - 1; k >= 0 && heights[k] >= heights[i]; k--, count++) ;
            for (int k = i + 1; k < heights.length && heights[k] >= heights[i]; k++, count++) ;
            if (max < heights[i] * count) {
                max = heights[i] * count;
            }
        }
        return max;
    }

    //better solution
    public int largestRectangleArea0(int[] heights) {
        return find(heights, 0, heights.length - 1);
    }

    public int find(int[] h, int left, int right) {
        if (left > right) {
            return 0;
        }
        if (left == right) {
            return h[left];
        }

        int minIndex = left;
        boolean sorted = true;
        //是否升序 和最小值下标
        for (int i = left + 1; i <= right; i++) {
            if (h[i] < h[i - 1]) {
                sorted = false;
            }
            if (h[i] < h[minIndex]) {
                minIndex = i;
            }
        }
        //如果升序
        if (sorted) {
            int max = 0;
            for (int i = left; i <= right; i++) {
                if (h[i] * (right - i + 1) > max) max = h[i] * (right - i + 1);
            }
            return max;
        } else {
            int maxLeft = find(h, left, minIndex - 1);
            int maxRight = find(h, minIndex + 1, right);
            int crossMax = h[minIndex] * (right - left + 1);
            return Math.max(Math.max(maxLeft, maxRight), crossMax);
        }
    }
}
