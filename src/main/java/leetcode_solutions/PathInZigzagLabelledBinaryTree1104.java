package leetcode_solutions;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class PathInZigzagLabelledBinaryTree1104 {
    class Solution {

        int[] boundary = new int[]{1, 2, 4, 8, 16, 32, 64, 128, 256, 512, 1024, 2048, 4096, 8192, 16384, 32768, 65536, 131072, 262144, 524288, 1048576};

        public List<Integer> pathInZigZagTree(int label) {
            List<Integer> re = new ArrayList<>();
            //找到层数，从第19层开始
            int bIndex = 19;
            while (bIndex > 0) {
                if (label >= boundary[bIndex]) break;
                else bIndex--;
            }


            while (label > 0) {
                re.add(0, label);
                //找到本层位置对称元素
                int sum = boundary[bIndex] + boundary[bIndex + 1] - 1;
                int symmetricalValue = sum - label;
                label = symmetricalValue / 2;
                bIndex--;
            }

            return re;

        }
    }

    class SolutionOnLeetcode1 {
        private List<Integer> res = new ArrayList<>();

        public List<Integer> pathInZigZagTree(int label) {
            int level = (int) (Math.log(label) / Math.log(2));
            findPath(label, level);
            return res;
        }

        private void findPath(int label, int level) {
            int parent = label / 2;
            int symmetryParent = (int) (Math.pow(2, level) + Math.pow(2, level - 1)) - 1 - parent;
            if (level != 0) {
                findPath(symmetryParent, level - 1);
            }
            res.add(label);
        }
    }

    @Test
    public void test1() {
        int label = 14;
        Solution solution = new Solution();
        List<Integer> re = solution.pathInZigZagTree(label);
        System.out.println(re);

        System.out.println("------------");
        label = 16;//        [1,3,4,15,16]

        System.out.println("------------");
        label = 656356;//[1,2,6,10,27,40,111,160,447,640,1790,2563,7160,10255,28640,41022,114563,164089,458253,656356]
        re = solution.pathInZigZagTree(label);
        System.out.println(re);
    }


    @Test
    public void test() {
        int i = 1000000;
        System.out.println(Math.log(i) / Math.log(2));
        System.out.println("-------------");
        System.out.println(Math.pow(2, 19));
        System.out.println(Math.pow(2, 20));

        for (int j = 0; j < 21; j++) {
            System.out.print((int) (Math.pow(2, j)));
            System.out.print(",");

        }
    }
}
