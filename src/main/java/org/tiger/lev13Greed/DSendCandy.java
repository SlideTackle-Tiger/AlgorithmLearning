package org.tiger.lev13Greed;

/**
 * @ClassName DSendCandy
 * @Description 分发糖果
 * @Author tiger
 * @Date 2025/12/12 12:29
 */
public class DSendCandy {
    public static void main(String[] args) {
        int[] source = {1,2,2,5,4,3,2};
        int[] ratings = {1,0,2};
        System.out.println("分发糖果：" + sendCandy(source));
    }

    public static int sendCandy(int[] ratings){
        // 相邻的得分高的必须比得分低的多
        // 考虑两种情况，右孩子比左孩子得分高（从前往后遍历），左孩子比右孩子得分高（从后向前遍历）。两次遍历方向相反保证使用前一次遍历的结果

        int n = ratings.length;
        int[] candy = new int[n];
        candy[0] = 1;

        // 从前往后 i 从1开始到n -1
        for(int i = 1; i < n; i++){
            if(ratings[i] > ratings[i - 1]){
                candy[i] = candy[i -1] + 1;
            }else{
                candy[i] = 1;
            }

        }

        // 从后往前 i从 n -2 到 0
        for(int i = n -2 ; i >=0; i--){
            if(ratings[i] > ratings[i + 1]){
                candy[i] = Math.max(candy[i + 1] + 1, candy[i]);
            }
        }

        // 求和
        int result = 0;
        for(int i : candy){
            result += i;
        }
        return result;

    }

}
