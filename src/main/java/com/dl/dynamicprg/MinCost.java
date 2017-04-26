package com.dl.dynamicprg;

/**
 * Created by iruve on 23/4/17.
 */
public class MinCost {

    public static Integer[][] arr = new Integer[3][3];
    public static Integer[][] as = new Integer[][] {{-1,0},{0,-1},{-1,-1}};

    public static boolean isSafe(int x, int y) {
        return (x >=0 && y >= 0 && x <=3 && y <= 3);
    }

    public static void getMinsCost() {
        for(int i = 0; i < arr.length; i++) {
            for(int j = 0; j < arr[i].length; j++) {
                Integer md = Integer.MAX_VALUE;
                for(int k = 0; k < as.length; k++) {
                    Integer id = (isSafe(i+as[k][0],j+as[k][1]) ? arr[i][j]+arr[i+as[k][0]][j+as[k][1]] : null);
                    if(id == null) continue;
                    md = Math.min(md,id);
                }
                if(md!= null && md != Integer.MAX_VALUE) arr[i][j] = md;
            }
        }
        System.out.println("The min dist is "+arr[2][2]);
    }

    public static void main(String[] a) {
        arr = new Integer[][] {
                {1,12,3},
                {4,18,2},
                {1,5,3}
        };
        getMinsCost();
    }
}
