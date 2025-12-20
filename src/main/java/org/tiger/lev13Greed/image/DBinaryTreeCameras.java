package org.tiger.lev13Greed.image;

/**
 * @ClassName DBinaryTreeCameras
 * @Description 监控二叉树
 * @Author tiger
 * @Date 2025/12/15 10:41
 */
public class DBinaryTreeCameras {
    // 二叉树定义
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public static void main(String[] args) {
        /**
         * 构造二叉树
         *      0
         *     0
         *    0 0
         * */
        TreeNode root = new TreeNode(0);
        root.left = new TreeNode(0);
        root.right = null;
        root.left.left = new TreeNode(0);
        root.left.right = new TreeNode(0);

        System.out.println("二叉树一共需要摄像头：" + minCameraCover(root));
    }

    public static int result = 0;
    public static int minCameraCover(TreeNode root){
        // 对根节点检测，如果为0则添加一个摄像头
        if(solve(root) == 0){
            result++;
        }
        return result;
    }
    public static int solve(TreeNode root){
        if(root == null){
            // 空节点默认 有覆盖状态，避免在叶子节点上放置摄像头
            return 2;
        }

        int left = solve(root.left);
        int right = solve(root.right);

        if(left == 2 && right == 2){
            return 0;
        }else if (left == 0 || right == 0){
            result ++;
            return 1;
        }else {
            return 2;
        }
    }
}
