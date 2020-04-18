package test;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 01 背包问题
 *
 * @author alan smith
 * @version 1.0
 * @date 2020/4/18 9:43
 */
public class Package01 {
    /**
     * 输入的数据
     * <p>
     * 第一行：载重C 商品个数n
     * 第二行：商品重量w1 商品价格v1
     * ~
     * 第n+1行：商品重量w(n+1) 商品价格v(n+1)
     */
    static String string = "6 3\n" +
            "3 5\n" +
            "2 4\n" +
            "4 2";

    /**
     * 商品的封装
     */
    static class Good {
        public int weight;
        public int price;

        public Good(int weight, int price) {
            // 重量
            this.weight = weight;
            // 价值
            this.price = price;
        }

        @Override
        public String toString() {
            return "Good{" +
                    "weight=" + weight +
                    ", price=" + price +
                    '}';
        }
    }

    /**
     * 递归处理
     *
     * @param C     载重
     * @param n     商品数量
     * @param goods
     * @return 最大价值
     */
    private static int recursion(int C, int n, Good[] goods) {
        // 从0位上开始选商品
        return resursion(C, n, goods, 0);
    }

    /**
     * @param index 当前选择的商品
     */
    private static int resursion(int c, int n, Good[] goods, int index) {
        // 不符合条件，购买失败
        if (c <= 0 || n <= 0) {
            return 0;
        }

        // 不买
        // 选择下一个
        int noBuyPrice = resursion(c, n-1, goods, index + 1);

        // 买
        Good good = goods[index];
        int buyPrice = good.price;
        // 选择下一个
        buyPrice += resursion(c - good.weight, n - 1, goods, index + 1);

        // 判断返回
        int max = 0;
        if (c >= good.weight) {
            max = Math.max(buyPrice, noBuyPrice);
        } else {
            max = noBuyPrice;
        }
        return max;
    }


    public static void main(String[] args) {
        // 默认值
        int MAX_GOOD_NUMBER = 5000;// 最多多少个商品
        // 初始化
        Scanner in = new Scanner(string);
        // 载重
        int C = in.nextInt();
        // 有多少个物品
        int n = in.nextInt();
        // 解析出商品s
        Good[] goods = new Good[MAX_GOOD_NUMBER];
        for (int i = 0; i < n; i++) {
            int weight = in.nextInt();
            int price = in.nextInt();
            Good good = new Good(weight, price);
            goods[i] = good;
        }

        // 打印初始化环境
        System.out.println("载重C：" + C);
        System.out.println("商品个数n：" + n);
        System.out.println("全部商品：\n" + Arrays.toString(goods));

        // 递归处理
        int max = recursion(C, n, goods);
        System.out.println(max);
    }
}
