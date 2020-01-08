package leetcode_solutions;

import java.util.List;
import java.util.Objects;

public class ConvexPolygon469 {
    /**
     * 使用向量叉积，
     * a = (x1,y1)
     * b = (x2,y2)
     * aXb = x1y2-x2y1
     * 叉积小于0，那么a在b的逆时针方向，
     * 叉积大于0，a在b的顺时针方向
     * 叉积等于0，a,b方向相同
     */
    //执行用时:15ms,在所有Java提交中击败了89.58%的用户
    //内存消耗:42MB,在所有Java提交中击败了100.00%的用户
    public class Solution {

        /**
         * 当内积第一次不为0时，奠定了整个图形的向量旋转方向，之后如果有旋转方向跟这个方向不一致的，就不是凸多边形
         */
        Boolean trunRight = null;

        class Vector {
            int start;
            int end;

            Vector(int start, int end) {
                this.start = start;
                this.end = end;
            }

            int cross(Vector v) {
                return this.start * v.end - v.start * this.end;
            }
        }

        public boolean isConvex(List<List<Integer>> points) {
            int len = points.size();
            if (len == 3) {
                return true;
            }


            Vector v1;
            Vector v2;

            //i=0和i=len-1时单独处理
            v1 = new Vector(points.get(0).get(0) - points.get(len - 1).get(0),
                    points.get(0).get(1) - points.get(len - 1).get(1));

            v2 = new Vector(points.get(1).get(0) - points.get(0).get(0),
                    points.get(1).get(1) - points.get(0).get(1));

            if (reVerseCross(v1, v2)) {
                return false;
            }

            v1 = new Vector(points.get(len - 1).get(0) - points.get(len - 2).get(0),
                    points.get(len - 1).get(1) - points.get(len - 2).get(1));

            v2 = new Vector(points.get(0).get(0) - points.get(len - 1).get(0),
                    points.get(0).get(1) - points.get(len - 1).get(1));

            if (reVerseCross(v1, v2)) {
                return false;
            }

            for (int i = 1; i < points.size() - 1; i++) {
                v1 = new Vector(points.get(i).get(0) - points.get(i - 1).get(0),
                        points.get(i).get(1) - points.get(i - 1).get(1));

                v2 = new Vector(points.get(i + 1).get(0) - points.get(i).get(0),
                        points.get(i + 1).get(1) - points.get(i).get(1));

                if (reVerseCross(v1, v2)) {
                    return false;
                }
            }


            return true;
        }

        boolean reVerseCross(Vector v1, Vector v2) {

            if (Objects.isNull(trunRight)) {
                if (v1.cross(v2) < 0) {
                    trunRight = true;
                }
                if (v1.cross(v2) > 0) {
                    trunRight = false;
                }
            }

            if (!Objects.isNull(trunRight)) {
                if (trunRight && v1.cross(v2) > 0) {
                    return true;
                }
                if (!trunRight && v1.cross(v2) < 0) {
                    return true;
                }
            }

            return false;
        }


    }

    //判断斜率变化方向，比如是否从递增化为递减
    class Solution9ms {
        public int crossProduct(List<Integer> p1, List<Integer> p2, List<Integer> p3) {
            int ax = p2.get(0) - p1.get(0);
            int ay = p2.get(1) - p1.get(1);
            int bx = p3.get(0) - p2.get(0);
            int by = p3.get(1) - p2.get(1);
            int cp = ax * by - ay * bx;
            if (cp > 0) {
                return 1;
            } else if (cp < 0) {
                return -1;
            } else {
                return 0;
            }
        }

        public boolean isConvex(List<List<Integer>> points) {
            int cp;
            int numPoints = points.size();
            boolean negativeFlag = false;
            boolean postiveFlag = false;
            for (int i = 2; i < numPoints + 2; i++) {
                cp = crossProduct(points.get((i - 2) % numPoints), points.get((i - 1) % numPoints),
                        points.get(i % numPoints));
                System.out.println(cp);
                if (cp == 1) {
                    postiveFlag = true;
                } else if (cp == -1) {
                    negativeFlag = true;
                }
            }
            if (postiveFlag && negativeFlag || (!postiveFlag && !negativeFlag)) {
                return false;
            } else {
                return true;
            }
        }
    }

}
