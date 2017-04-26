package com.dl.dynamicprg;

/**
 * Created by iruve on 19/4/17.
 */
public class Lis {
    static int max_lis_length = Integer.MIN_VALUE; // stores the final LIS
    static int max_lis_elem;
    static int[] lis;
    static int[] lisLength;
    // Recursive implementation for calculating the LIS
    static void _lis(int arr[], int nn)
    {
        if(nn == lis.length-1) {
            lis[nn] = 1;
            return;
        }
        lisLength[lis.length-1] = 1;
        for(int n = lis.length-1; n >=0; n--) {
            for (int i = n; i < lis.length-1; i++) {
                if (lis[n] < lis[i + 1] && lisLength[n] < lisLength[i + 1]) {
                    lisLength[n] = lisLength[i + 1] + 1;
                    if(max_lis_length < lisLength[n]) {
                        max_lis_length = lisLength[n];
                        max_lis_elem = lis[n];
                    }
                }
            }
        }
    }

    // The wrapper function for _lis()
    static int lis(int arr[], int n)
    {

        // max_lis_length is declared static above
        // so that it can maintain its value
        // between the recursive calls of _lis()
        _lis( arr, n );

        return max_lis_length;
    }

    // Driver program to test the functions above
    public static void main(String args[])
    {
        int arr[] = {10, 11,12,13,14,22, 9, 11,12,13,33, 21, 50, 41, 60};
        Lis lis = new Lis();
        lis.lis = arr;
        lisLength = new int[arr.length];
        int n = arr.length;
        System.out.println("Length of LIS is " + lis( arr, n ));
        System.out.println("max lis elem is " + max_lis_elem);
    }

}
