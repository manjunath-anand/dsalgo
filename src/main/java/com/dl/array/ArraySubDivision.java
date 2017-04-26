package com.dl.array;

/*
 * http://www.geeksforgeeks.org/find-if-array-can-be-divided-into-two-subarrays-of-equal-sum/
 */
public class ArraySubDivision {

	public static void main(String[] args) {
		findSumEqual();		
	}

	/*
	 * Input:  arr = [6, 2, 3, 2, 1]
	`* Output:  true
	 * Explanation:  On removing element 2 at index 1, the array gets divided 
	 * into two sets [6] and [3, 2, 1] having equal sum
	 */
	public static void findSumEqual() {
		//int[] arr = {6,2,3,2,1};
		int[] arr = {6,-2,3,2,3};
		int ts = 0, tsi = 0, lsi, rsi;		
		for(int i=0; i < arr.length; i++) {
			ts += arr[i];
		}
		for(int i=0; i < arr.length; i++) {
			tsi += arr[i];
			lsi = tsi - arr[i];
			rsi = ts - tsi;
			if(lsi == rsi) {
				System.out.println("On removing element ["+arr[i]+"] at index ["+i+"],"
						+ "the array gets divided into two sets");
			}
		}
	}
}
