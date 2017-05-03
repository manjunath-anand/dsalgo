package com.dl.dynamicprg;

/**
 * Created by iruve on 2/5/17.
 */
public class CutRod {

    static int cutRod(int price[],int n)
    {
        int l1 = 0,l2 = 0;
        int max_val = Integer.MIN_VALUE;
        int m = 0;
        for (int i = 1; i<=n/2; i++)
        {
            m = 0;
            while (n-(i*++m) > 0) {
                if(max_val < price[i-1]*m + price[n-1-(i*m)]) {
                    max_val = price[i-1]*m + price[n-1-(i*m)];
                    l1 = i;
                    l2 = n-(i*m);
                }
            }
        }
        System.out.println("the l1 and l2 are index --> "+l1+" -- "+m+" times and index --> "+l2);
        return max_val;
    }

    public static void main(String args[])
    {
        int arr[] = new int[] {2, 1, 8, 9, 10, 40, 17, 20};
        int size = arr.length;
        System.out.println("Maximum Obtainable Value is " +
                cutRod(arr, size));
        System.out.println("the no of loops is ");
    }
}
