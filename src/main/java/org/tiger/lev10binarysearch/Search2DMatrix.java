package org.tiger.lev10binarysearch;

/**
 * @ClassName Search2DMatrix
 * @Description 搜索二维矩阵
 * @Author tiger
 * @Date 2025/11/10 15:08
 */

/*
  给你一个满足下述两条属性的 m x n 整数矩阵：
  每行中的整数从左到右按非严格递增顺序排列。
  每行的第一个整数大于前一行的最后一个整数。
  给你一个整数 target ，如果 target 在矩阵中，返回 true ；否则，返回 false 。
  */

/*
  输入：matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 3
  输出：true
 */

/*
* 思路：两次二分，第一次遍历列找到最后一个不大于target的元素的列，调用第二次二分找到元素是否存在
* */
public class Search2DMatrix {
    public static void main(String[] args) {
        int[][] matrix = {{1,3,5,7},{10,11,16,20},{23,30,34,60}};
        int target = 13;
        System.out.println(search(matrix,target));
    }

    public static boolean search(int[][] matrix, int target){
        int colums = binarySearchColums(matrix, target);
        if(colums < 0){
            return false;
        }
        return binarySearchRow(matrix, target, colums);
    }

    // 找到第一个小于target的列
    public static int binarySearchColums(int[][] matrix, int target){
        int n = matrix.length;
        int left = 0; int right = n-1;
        int ans = -1;
        while(left <= right){
            int mid = ((right - left) >> 1) + left;
            if(matrix[mid][0] <= target){
                ans = mid;
                left = mid + 1;
            }else {
                right = mid -1;
            }
        }
        return ans ;
    }

    // row中找到target的位置
    public static boolean binarySearchRow(int[][] matrix, int target, int colums){
        int[] row = matrix[colums];
        int n = row.length;
        int left = 0;
        int right = n -1;
        boolean find = false;
        while(left <= right){
            int mid = ((right - left) >> 1) + left;
            if(row[mid] == target){
                return true;
            }
            if(row[mid] < target){
                left = mid + 1;
            }else {
                right = mid - 1;
            }
        }

        return false;
    }
}
