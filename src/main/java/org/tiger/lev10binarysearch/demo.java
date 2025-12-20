package org.tiger.lev10binarysearch;

/**
 * @ClassName demo
 * @Description 测试类
 * @Author tiger
 * @Date 2025/11/11 09:41
 */
public class demo {
    public static void main(String[] args) {
        int[][] matrix = {{1,3,5,7},{10,11,16,20},{23,30,34,60}};
        int target = 23;
        System.out.println(sovle(matrix, target));
    }

    public static boolean sovle(int[][] board, int target){
        // 第一次二分查找第一列找到target所在的行
        int row = searchcol(board, target);

        // 第二次二分查找找到对应行 是否存在target
        boolean check = searchrow(board, target, row);
        return check;

    }

    public static int searchcol(int[][] board, int target){
        int left = 0; int right = board.length - 1;
        int ans = -1;
        while(left <= right){
            int mid = ((right - left) >> 1) + left;
            if(board[mid][0] <= target){
                ans = mid;
                left = mid + 1;
            }else {
                right = mid - 1;
            }
        }
        return  ans;
    }

    public static boolean searchrow(int[][] board, int target, int row){
        int left = 0; int right = board[row].length - 1;
        while(left <= right){
            int mid = ((right - left) >> 1) +left;
            if(board[row][mid] == target){return true;}
            if(board[row][mid] < target){
                left = mid + 1;
            }else {
                right = mid -1;
            }
        }
        return false;

    }


}
