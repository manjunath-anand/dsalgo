package com.dl.com.backtracking;

/**
 * Created by iruve on 13/4/17.
 */
public class NQueen {
    int N = 8;
    Integer[][] chess = new Integer[N][N];

    public boolean isSafe(int r, int c) {
        boolean isSafe = false;
        for(int i = c; i >= 0; i--) {
            if(chess[r][i] != null) {
                return isSafe;
            }
        }
        for(int i = r, j = c; i >= 0 && j >= 0 ; i--, j--) {
            if(chess[r][i] != null) {
                return isSafe;
            }
        }
        for(int i = r, j = c; i <= N && j <= N ; i++, j++) {
            if(chess[r][i] != null) {
                return isSafe;
            }
        }
        return false;
    }

    public boolean placeQueen(int x, int y, int q) {
        if(isSafe(x,y)) {
            chess[x][y] = q;
            if(placeQueen(x-1,y-1, q+1)) {
                return true;
            }
        }
        return false;
    }
}
