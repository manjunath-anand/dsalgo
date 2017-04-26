package com.dl.com.backtracking;

/**
 * Created by iruve on 15/4/17.
 * http://www.geeksforgeeks.org/find-shortest-safe-route-in-a-path-with-landmines/
 */
public class LandMines {
    int M = 12, N = 10;
    public Integer[][] lmap = new Integer[M][N];
    public boolean[][] lmVisited = new boolean[M][N];
    public int minDist = Integer.MAX_VALUE;
    public int minRow = 0;
    Integer[] x_step_inv = {-1,0,0, 1};
    Integer[] y_step_inv = {0,1,-1, 0};
    Integer[] x_step = {0,-1, 1};
    Integer[] y_step = {1,0, 0};

    public boolean isSafe(int x , int y) {
        if(lmap[x][y] != 1 || lmVisited[x][y]) {
            return false;
        }
        return true;
    }

    public void markUnsafe() {
        for(int i = 0; i < M; i++) {
            for(int j = 0; j < N; j++) {
                if(lmap[i][j] == 0) {
                    for (int k = 0; k < x_step_inv.length; k++) {
                        if (isValid(i + x_step_inv[k], j + y_step_inv[k]) &&
                                lmap[i + x_step_inv[k]][j + y_step_inv[k]]!=0) {
                            lmap[i + x_step_inv[k]][j + y_step_inv[k]] = -1;
                        }
                    }
                }
            }
        }
    }

    public boolean isValid(int x, int y) {
        return (x >= 0 && y >= 0 && x < M && y < N);
    }

    public void findMinPath(int x, int y, int dist) {
        if(y == N-1) {
            this.minRow = (this.minDist < dist) ? x : minRow;
            this.minDist = Math.min(this.minDist, dist);
            return;
        }
        if(dist > minDist) {
            return;
        }

        for(int i = 0; i < x_step.length; i++) {
            if(isSafe(x, y) &&
                    isValid(x+x_step[i], y+y_step[i]) &&
                    isSafe(x+x_step[i], y+y_step[i])) {
                lmVisited[x][y] = true;
                findMinPath(x+x_step[i], y+y_step[i], dist+1);
            }
        }
        lmVisited[x][y] = false;
    }

    public static void main(String[] a) {
        LandMines lm = new LandMines();
        Integer arr[][] = {
                { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
                { 1, 0, 1, 1, 1, 1, 1, 1, 1, 1 },
                { 1, 1, 1, 0, 1, 1, 1, 1, 1, 1 },
                { 1, 1, 1, 1, 0, 1, 1, 1, 1, 1 },
                { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
                { 1, 1, 1, 1, 1, 0, 1, 1, 1, 1 },
                { 1, 0, 1, 1, 1, 1, 1, 1, 0, 1 },
                { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
                { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
                { 0, 1, 1, 1, 1, 0, 1, 1, 1, 1 },
                { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
                { 1, 1, 1, 0, 1, 1, 1, 1, 1, 1 }
        };
        lm.lmap = arr;
        lm.markUnsafe();
        for(int i = 0; i < lm.M; i++) {
            lm.findMinPath(i, 0, 0);
            if(lm.minDist == lm.N-1) break;
            System.out.println("the row is "+i);
        }
        System.out.println("The minDist is "+lm.minDist+" on row "+lm.minRow);
    }
}
