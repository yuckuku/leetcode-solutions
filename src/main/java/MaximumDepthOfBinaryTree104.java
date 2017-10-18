import common.TreeNode;

/**
 * Created by Administrator on 2017/10/16.
 */
public class MaximumDepthOfBinaryTree104 {

    public int maxDepth(TreeNode root) {
        if(null == root){
            return 0;
        }
        int depth =1;
        if(null !=root.left){
            depth =Math.max(depth,1+maxDepth (root.left));
        }
        if(null !=root.right){
            depth =Math.max(depth,1+maxDepth (root.right));
        }
        return depth;
    }

}
