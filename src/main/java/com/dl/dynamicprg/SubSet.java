package com.dl.dynamicprg;

/**
 * Created by iruve on 24/4/17.
 * Not a complete solution
 */
public class SubSet {
    public static Integer[] s = new Integer[]{};
    public static int[] table = new int[]{};
    public static String[] str = new String[]{};

    public static int findChangeDp(int m, int n) {
        table = new int[n+1];
        str = new String[n+1];
        table[0] = 1;
        for(int i=0;i<m;i++) {
            for(int j=s[i];j<=n;j++) {
                table[j] += table[j-s[i]];
                if(j-s[i] == 0) str[j] = j+"";
                if(s[i] == j-s[i] && table[s[i]] > 1 ||
                        (s[i] != j-s[i] && str[s[i]] != null && str[j-s[i]] != null)) {

                    if(!str[j-s[i]].contains(s[i]+"")) {
                        str[j] = (str[j] != null) ? str[j] + ":" + str[j-s[i]]+","+ str[s[i]] : str[j-s[i]]+str[s[i]];
                    }
                }

            }
        }
        return table[n];
    }
    public static void main(String[] a) {
        s = new Integer[]{3,34,4,12,5,2};
        System.out.println(findChangeDp(s.length,9));
        System.out.println(str[9]);
    }
}
