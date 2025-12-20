package org.tiger.lev9backtracking;

import java.util.*;

/**
 * @ClassName demo01
 * @Description 测试用例
 * @Author tiger
 * @Date 2025/11/5 10:16
 */
public class demo01 {
    public static void main(String[] args) {
        System.out.println(solve(4));
    }

    public static List<List<String>> solve(int n){
        List<List<String>> result = new ArrayList<>();
        int[] queens = new int[n];
        Arrays.fill(queens, -1);

        Set<Integer> colums = new HashSet<>();
        Set<Integer> dals1 = new HashSet<>();
        Set<Integer> dals2 = new HashSet<>();

        dfs(result, queens, colums, dals1, dals2, 0, n);
        return result;
    }

    public static void dfs(List<List<String>> result, int[] queens, Set<Integer> colums, Set<Integer> dals1, Set<Integer> dals2, int row, int n){
        //done
        if(row == n){
            List<String> current = gen(queens, n);
            result.add(current);
            return;
        }

        // try possible
        for(int i = 0; i < n; i++){
            if(colums.contains(i)){
                continue;
            }

            int di1 = row - i; // 行减去列
            if(dals1.contains(di1)){
                continue;
            }

            int di2 = row + i;// 行加上列
            if(dals2.contains(di2)){
                continue;
            }

            // 加入到queens队列
            queens[row] = i;
            colums.add(i);
            dals1.add(di1);
            dals2.add(di2);

            dfs(result, queens, colums, dals1, dals2, row + 1, n);
            // rollback
            queens[row] = - 1;
            colums.remove(i);
            dals1.remove(di1);
            dals2.remove(di2);
        }
    }

    public static List<String> gen(int[] queens, int n){
        List<String> current = new ArrayList<>(); // 存储结果
        for(int i = 0; i < n; i++){
            char[] row = new char[n];
            Arrays.fill(row, '.');
            row[queens[i]] = 'Q';
            current.add(new String(row));
        }
        return current;
    }
}
