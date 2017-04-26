package com.dl.com.backtracking;

/**
 * Created by iruve on 13/4/17.
 */
public class LandMinesMine {
    int N = 10;
    int M = 12;
    public Integer[][] lMap = new Integer[M][N];
    //Integer[] x_step = {-1, -1, -1, 0, 0, 1, 1, 1};
    //Integer[] y_step = {1, 0, -1, 1, -1, 1, 0, -1};
    Integer[] x_step = {-1, 0, 0, 1};
    Integer[] y_step = {0, 1, -1, 0};

    public boolean isSafe(int x, int y) {
        if (x < 0 || y < 0 || x > M) {
            return false;
        } else if (y == N-1) {
            return lMap[x][y] != 0;
        }
        int x_next = x;
        int y_next = y;
        for (int j = 0; j < x_step.length; j++) {
            x_next = x + x_step[j];
            y_next = y + y_step[j];
            if (x_next < 0 || y_next < 0 || x_next >= M || y_next >= N) {
                continue;
            } else if (lMap[x_next][y_next] == 0) {
                return false;
            }
        }
        return true;
    }

    public boolean findMinPath(int x, int y ) {
        int minPathStart = 0 , t = 0;
        int nodesInMinPath = 0, nodesInCurrentPath = 0;
        for(int i = 0 ; i < M ; i++) {
            boolean validPath = true;
            y = -1;
            t = i;
            nodesInCurrentPath = 0;
            while(y < N-1 && x < M) {
                if(isSafe(t, ++y)) {
                    nodesInCurrentPath++;
                } else if(y != 0 && isSafe(++t, --y)){
                    nodesInCurrentPath++;
                } else {
                    validPath = false;
                    break;
                }
            }
            if(validPath || y == N-1) {
                minPathStart = (nodesInMinPath < N || nodesInCurrentPath < nodesInMinPath) ? i : minPathStart;
                nodesInMinPath = (nodesInMinPath < N ||  nodesInCurrentPath < nodesInMinPath) ? nodesInCurrentPath : nodesInMinPath;
            }
        }
        if(nodesInMinPath >= N) {
            System.out.println("The min nodes is "+nodesInMinPath+" and row is "+minPathStart);
            return true;
        }
        return false;
    }

    public static void main(String[] a) {
        LandMinesMine lm = new LandMinesMine();
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
        lm.lMap = arr;
        lm.findMinPath(0,0);
    }
}
