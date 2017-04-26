package com.dl.array;

/*
 * http://www.geeksforgeeks.org/print-a-given-matrix-in-spiral-form/
 * Input:
        1    2   3   4
        5    6   7   8
        9   10  11  12
        13  14  15  16
   Output: 
		1 2 3 4 8 12 16 15 14 13 9 5 6 7 11 10 
		
0,0
0,1
0,2
0,3       

1,3
2,3
3,3       

3,2
3,1
3,0       

2,0
1,0       

1,1
1,2       

2,2       

2,1       


 */
public class SpiralMatrix {

	public static void main(String[] args) {
		printSpiral();
		
	}

	public static void printSpiral() {
		int a[][] = {{1,2,3,4},{5,6,7,8},{9,10,11,12},{13,14,15,16}};
		int i = 0, j = 0, r = i;
		for(i = 0; i < 4; i++) {
//			if(i % 2 == 0) {
				for(j = 0; j < 4; j++) {
					System.out.print(a[i][j] + " ");
				}
//			} else {
				for(j = 3; j >= 0; j--) {
					System.out.print(a[r++][j] + " ");
				}
//			}
			
		}
	}
}
