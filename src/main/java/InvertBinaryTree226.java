/**
 * Created by Administrator on 2017/10/16.
 */
import common.TreeNode;
public class InvertBinaryTree226 {

    public TreeNode invertTree(TreeNode root) {
        if (null == root) {
            return root;
        }
        TreeNode tn = root.left;
        root.left = root.right;
        root.right = tn;
        invertTree(root.left);
        invertTree(root.right);
        return root;
    }
}


