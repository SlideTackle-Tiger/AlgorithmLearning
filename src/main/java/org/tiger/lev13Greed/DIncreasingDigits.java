package org.tiger.lev13Greed;

/**
 * @ClassName DIncreasingDigits
 * @Description 单调递增的数字
 * @Author tiger
 * @Date 2025/12/15 09:08
 */
public class DIncreasingDigits {
    public static void main(String[] args) {
        int num = 332;
        System.out.println(num +" 的最大单调递增数字为：" + solve(num));
    }

    public static int solve(int n){
        String s = n + ""; // int类型转换为字符串
        char[] chars = s.toCharArray(); // 字符串转换为char数组
        int flag = s.length(); // 初始化标记为 ，flag和flag后的所有数都变为9
        // 从后向前遍历数组，注意因为要处理i-1因此遍历截止到数组的倒数第二个元素。
        for(int i = s.length() - 1; i > 0; i--){
            // 如果当前i的前一位大于i，那么前一位-- 且flag等于i
            if(chars[i - 1] > chars[i]){
                chars[i - 1]--;
                flag = i;
            }
        }
        // 将flag和flag后的所有数字变为9
        for(int i = flag; i < s.length(); i++){
            chars[i] = '9';
        }
        // String.valueOf将char数组转为字符串，Integer.parseInt将字符串转换为整数
        return Integer.parseInt(String.valueOf(chars));
    }
}
