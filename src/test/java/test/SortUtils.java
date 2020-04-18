package test;

import java.util.Arrays;

/**
 * @author alan smith
 * @version 1.0
 * @date 2020/4/17 20:30
 */
public class SortUtils {

    /**
     * 冒泡排序
     * 最坏 O(n*n)
     * 最好 O(n)
     */
    public static int[] bubbling(int[] arr) {
        if (arr == null || arr.length == 1) return arr;
        boolean sorted = true;
        for (int a = 0; a < arr.length; a++) {
            int bubble = arr[0];
            if(a>0 && sorted){
                return arr;
            }else {
                sorted=true;
            }
            for (int i = 1; i < arr.length-a; i++) {
                int temp = arr[i];
                if (temp - bubble < 0) {
                    sorted=false;
                    arr[i - 1] = temp;
                    arr[i] = bubble;
                }else {
                    bubble = temp ;
                }
            }
        }
        return arr;
    }

    public static void main(String[] args) {
        int[] arr = {3, 38, 5, 44, 15, 36, 26, 27, 2, 4, 19, 46, 48, 50};
        int[] arr1 = {1,0,2,3,4};
        // 冒泡
        System.out.println(Arrays.toString(bubbling(arr1)));
    }
}
