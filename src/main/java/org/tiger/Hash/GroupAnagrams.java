package org.tiger.Hash;

import java.util.*;

/**
 * @ClassName GroupAnagrams
 * @Description 字母异位词分组 anagrams异位词 group 分组
 * @Author tiger
 * @Date 2025/10/30 09:25
 * 输入: strs = ["eat", "tea", "tan", "ate", "nat", "bat"]
 * 输出: [["bat"],["nat","tan"],["ate","eat","tea"]]
 */
public class GroupAnagrams {
    // 测试
    public static void main(String[] args) {
        String[] strs = {"eat", "tea", "tan", "ate", "nat", "bat"};
        List<List<String>> solv = solution(strs);
        System.out.println(solv);


    }

    // solution
    public static List<List<String>> solution(String[] strArr){
        int n = strArr.length;
        // 处理数据，将数据存入到map里 key : 排序后的字符串，value：原字符串的List
        Map<String, List<String>> map = new HashMap<>();
        for(int i =0; i < n; i++){
            char[] c = strArr[i].toCharArray();
            Arrays.sort(c);
            String key = Arrays.toString(c);
            List<String> res = map.getOrDefault(key, new ArrayList<>());
            res.add(strArr[i]);
            map.put(key, res);
        }

        // 遍历map 将所有数据加入返回数组
        List<List<String>> res = new ArrayList<>();
        for(Map.Entry<String, List<String>> entry : map.entrySet()){
            res.add(entry.getValue());
        }

        return res;
    }
}
