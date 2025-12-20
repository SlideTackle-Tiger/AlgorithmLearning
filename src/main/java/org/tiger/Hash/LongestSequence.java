package org.tiger.Hash;

/**
 * @ClassName LongestSequence
 * @Description 最长连续序列
 * @Author tiger
 * @Date 2025/10/30 09:41
 */

import java.util.HashSet;
import java.util.Set;

/**
 * 输入：nums = [100,4,200,1,3,2]
 * 输出：4
 * 解释：最长数字连续序列是 [1, 2, 3, 4]。它的长度为 4。
 * 注意时间复杂度O(n)解法：
 * */
public class LongestSequence {
    public static void main(String[] args) {
        int[] nums = {1,0,1,2};
        System.out.println(longestConsecutiveSequence(nums));

    }

    public static int longestConsecutiveSequence(int[] nums){
        Set<Integer> set = new HashSet<>();
        for(int i : nums){
            set.add(i);
        }

        // 开始计算最大连续序列
        int longest = 0;
        for(int i =0 ;i < nums.length; i++){
            int currentSize = 1;
            int current = nums[i];
            if(!set.contains(current - 1)){
                while(set.contains(current +1)){
                    current++;
                    currentSize++;
                }
                longest = Math.max(longest, currentSize);
            }
        }
        return longest;
    }

}
