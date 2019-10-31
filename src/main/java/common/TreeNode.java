package common;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.LinkedList;

/**
 * Created by Administrator on 2017/10/17.
 */
public class TreeNode {

    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode() {
    }

    public TreeNode(int x) {
        val = x;
        this.left = this.right = null;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("val", val)
                .append("left", left)
                .append("right", right)
                .toString();
    }

    public String levelTraverse() {

        StringBuilder builder = new StringBuilder();
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.offer(this);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            builder.append(node == null ? "null" : node.val).append(",");
            if (node != null) {
//                if (node.left != null) {
                queue.offer(node.left);
//                }
//                if (node.right != null) {
                queue.offer(node.right);
//                }
            }
        }
        return builder.toString();
    }

//    public void  preOrder(TreeNode root){
//        System.out.println(root);
//    }
}
