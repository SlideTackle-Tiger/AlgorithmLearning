package org.tiger.lev9backtracking;

/**
 * @ClassName TelephoneNumber
 * @Description 电话号码组合问题
 * @Author tiger
 * @Date 2025/11/5 15:48
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 是否可以看成是，两层的全排序问题？
 * */
public class TelephoneNumber {
    public static void main(String[] args) {
        // 示例输入：数字字符串
        String digits = "23";
        // 生成并打印数字对应的所有字母组合
        System.out.println(generateLetterCombinations(digits));
    }

    /**
     * 生成数字字符串对应的所有可能字母组合（模拟电话按键字母映射）
     * 例如：输入"23"，输出["ad","ae","af","bd","be","bf","cd","ce","cf"]
     *
     * @param digitStr 由数字组成的字符串（仅包含2-9）
     * @return 所有可能的字母组合列表，若输入为空则返回空列表
     */
    public static List<String> generateLetterCombinations(String digitStr) {
        List<String> combinationResults = new ArrayList<>();
        int strLength = digitStr.length();

        // 处理空输入的特殊情况
        if (strLength == 0) {
            return combinationResults;
        }

        // 构建电话按键与字母的映射关系（模拟手机键盘）
        Map<Character, String> phoneKeyMap = new HashMap<Character, String>() {{
            put('2', "abc");
            put('3', "def");
            put('4', "ghi");
            put('5', "jkl");
            put('6', "mno");
            put('7', "pqrs");
            put('8', "tuv");
            put('9', "wxyz");
        }};

        StringBuffer currentCombination = new StringBuffer();
        // 调用回溯算法生成所有组合
        backtrack(combinationResults, phoneKeyMap, digitStr, 0, currentCombination);

        return combinationResults;
    }

    /**
     * 回溯算法生成数字字符串对应的字母组合
     * 核心逻辑：按顺序处理每个数字，对每个数字对应的所有字母进行枚举，递归生成组合
     *
     * @param combinationResults 存储所有字母组合结果的列表
     * @param phoneKeyMap        电话按键与字母的映射表
     * @param digitStr           输入的数字字符串
     * @param currentDigitIndex  当前处理的数字索引（从0开始）
     * @param currentCombination 用于构建当前组合的字符串缓冲区
     */
    public static void backtrack(List<String> combinationResults, Map<Character, String> phoneKeyMap,
                                 String digitStr, int currentDigitIndex, StringBuffer currentCombination) {
        // 递归终止条件：当处理完所有数字时，当前组合已完整
        if (currentDigitIndex == digitStr.length()) {
            combinationResults.add(currentCombination.toString());
            return;
        }

        // 获取当前数字对应的字母字符串
        char currentDigit = digitStr.charAt(currentDigitIndex);
        String correspondingLetters = phoneKeyMap.get(currentDigit);
        int letterCount = correspondingLetters.length();

        // 枚举当前数字对应的所有字母，生成组合
        for (int i = 0; i < letterCount; i++) {
            // 选择当前字母，加入到当前组合中
            currentCombination.append(correspondingLetters.charAt(i));
            // 递归处理下一个数字（索引+1）
            backtrack(combinationResults, phoneKeyMap, digitStr, currentDigitIndex + 1, currentCombination);
            // 回溯：移除最后添加的字母，尝试其他可能性
            currentCombination.deleteCharAt(currentDigitIndex);
        }
    }


}
