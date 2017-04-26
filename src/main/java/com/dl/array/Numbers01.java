package com.dl.array;

//http://www.geeksforgeeks.org/find-combinations-k-bit-numbers-n-bits-set-1-n-k-sorted-order/
public class Numbers01 {

	public static void main(String[] args) {
		int n = 5;
		String s = "00000", s1, s2;
		for(int i = n-1 ; i >= 0 ; i--) {
			s = s.substring(0,i) + "1" + s.substring(i+1);
			s1 = s;
			System.out.println(s);
			for(int j = i-1; j >= 0 ; j--) {
				//System.out.println("Before == >"+s1);		
				s1 = s1.substring(0,j) + s1.charAt(j+1) + "0" + s1.substring(j+2);
				s2 = s1;
				System.out.println(s1);		
				for(int k = j-1; k >= j; k--) {
					s2 = s2.substring(k,j) + s2.charAt(k+1) + "0" + s1.substring(k+2);
					System.out.println(s2);		
				}
			}
		}
	}

}
