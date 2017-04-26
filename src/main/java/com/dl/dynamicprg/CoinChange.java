package com.dl.dynamicprg;

/**
 * Created by iruve on 23/4/17.
 */
public class CoinChange {

    public static Integer[] coins = new Integer[]{};
    public static int change = 4;
    public static int[] table = new int[]{};

    public static int findChange(int range, int sum) {
        if(sum < 0 || range <= 0 && sum >= 1) {
            return 0;
        }

        if(sum == 0) {
            return 1;
        }
        return findChange(range-1,sum) + findChange(range, sum-coins[range-1]);
    }

    public static int findChangeDp(int m, int n) {
        table = new int[n+1];
        table[0] = 1;
        for(int i=0;i<m;i++) {
            for(int j=coins[i];j<=n;j++) {
                table[j] += table[j-coins[i]];
            }
        }
        return table[n];
    }
    public static void main(String[] a) {
        coins = new Integer[]{2,5,3,6};
        System.out.println(findChange(coins.length,10));
        System.out.println(findChangeDp(coins.length,10));
    }
}
