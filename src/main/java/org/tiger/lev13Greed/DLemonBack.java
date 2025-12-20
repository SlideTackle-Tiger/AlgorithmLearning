package org.tiger.lev13Greed;

/**
 * @ClassName DLemonBack
 * @Description 柠檬水找零钱
 * @Author tiger
 * @Date 2025/12/12 13:11
 */
public class DLemonBack {
    public static void main(String[] args) {
        int[] bills = {5,5,5,10,20};
        System.out.println("是否可以找零：" + sovle(bills));
    }

    public static boolean sovle(int[] bills){
        int five = 0;
        int ten = 0;
        for(int bill : bills){
            switch (bill){
                case 5:
                    five++;
                    break;
                case 10:
                    if(five > 0){
                        five--;
                        ten++;
                        break;
                    }else{
                        return false;
                    }
                case 20:
                    if(ten > 0 && five >0){
                        five --;
                        ten--;
                        break;
                    }else if(five >= 3){
                        five = five - 3;
                        break;
                    }else {
                        return false;
                    }
            }
        }
        return true;
    }
}
