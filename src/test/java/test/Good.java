package test;

import java.util.Scanner;

/**
 * @author alan smith
 * @version 1.0
 * @date 2020/4/18 8:31
 */
public class Good {

    public int score  ;

    public Good(int score) {
        this.score = score;
    }

    static String  string = "1000 5\n" +
            "800 2 0\n" +
            "400 5 1\n" +
            "300 5 1\n" +
            "400 3 0\n" +
            "500 2 0" ;

    public static void main(String[] args) {
        // 初始化
        Scanner  in = new Scanner(string);
        // 总钱数
        int N = in.nextInt();
        // 存在的商品种类数
        int m = in.nextInt();
        // 分组，goods[i][0]主件、goods[i][1]附件1、goods[i][2]附件2
        Good[][] goods = new Good[60][3];
        for(int i=1; i<=m; i++) {
            // 价格
            int price = in.nextInt();
            // 重要程度
            int level = in.nextInt();
            // 种类 0主 1副
            int type = in.nextInt();
            Good good = new Good(price * level);
            if(type==0) {
                //goods[i][0] =
            }
        }
    }
}
