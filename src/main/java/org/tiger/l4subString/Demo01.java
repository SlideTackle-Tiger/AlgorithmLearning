package org.tiger.l4subString;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @ClassName MinimumWindowSubstring
 * @Description 最小覆盖子串求解
 * @Author tiger
 * @Date 2025/11/2 12:56
 */
public class Demo01 {
    public static void main(String[] args) {
        String source = "ADOBECODEBANC";
        String target = "ABC";
        System.out.println(findMinWindow(source, target));  // 预期输出: "BANC"
    }

    /**
     * 查找包含目标字符串所有字符的最小子串
     * @param source 源字符串
     * @param target 目标字符串
     * @return 最小覆盖子串，若不存在则返回空串
     */
    public static String findMinWindow(String source, String target) {
        // 存储目标字符串中各字符的需求数量
        Map<Character, Integer> targetCharCounts = new HashMap<>();
        // 存储当前窗口中各字符的数量
        Map<Character, Integer> windowCharCounts = new HashMap<>();

        // 初始化目标字符计数
        for (int i = 0; i < target.length(); i++) {
            Character c = target.charAt(i);
            targetCharCounts.put(c, targetCharCounts.getOrDefault(c, 0) + 1);
        }

        // 滑动窗口边界
        int left = 0;
        int right = -1;

        // 记录最小窗口信息
        int minWindowLength = Integer.MAX_VALUE;
        int minWindowLeft = -1;
        int minWindowRight = -1;

        int sourceLength = source.length();

        // 扩展窗口
        while (right < sourceLength) {
            right++;  // 移动右边界

            // 如果当前字符是目标字符，则更新窗口计数
            if (right < sourceLength && targetCharCounts.containsKey(source.charAt(right))) {
                Character currentChar = source.charAt(right);
                windowCharCounts.put(currentChar, windowCharCounts.getOrDefault(currentChar, 0) + 1);
            }

            // 当窗口包含所有目标字符时，尝试收缩左边界
            while (isWindowValid(windowCharCounts, targetCharCounts) && left <= right) {
                // 更新最小窗口
                int currentWindowLength = right - left + 1;
                if (currentWindowLength < minWindowLength) {
                    minWindowLength = currentWindowLength;
                    minWindowLeft = left;
                    minWindowRight = right;
                }

                // 收缩左边界
                Character leftChar = source.charAt(left);
                if (targetCharCounts.containsKey(leftChar)) {
                    windowCharCounts.put(leftChar, windowCharCounts.get(leftChar) - 1);
                }
                left++;
            }
        }

        // 判断是否找到有效窗口
        return minWindowLeft == -1 ? "" : source.substring(minWindowLeft, minWindowRight + 1);
    }

    /**
     * 检查当前窗口是否包含目标字符串的所有字符（数量满足要求）
     * @param windowCounts 窗口字符计数
     * @param targetCounts 目标字符计数
     * @return 窗口有效返回true，否则返回false
     */
    public static boolean isWindowValid(Map<Character, Integer> windowCounts, Map<Character, Integer> targetCounts) {
        // 检查目标字符串中的每个字符在窗口中是否都满足数量要求
        Iterator<Map.Entry<Character, Integer>> iterator = targetCounts.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Character, Integer> entry = iterator.next();
            Character targetChar = entry.getKey();
            int requiredCount = entry.getValue();

            // 若窗口中该字符数量不足，则窗口无效
            if (windowCounts.getOrDefault(targetChar, 0) < requiredCount) {
                return false;
            }
        }
        // 所有字符都满足要求，窗口有效
        return true;
    }
}