package com.dl.array;

import java.util.Stack;

/*
 * 1. R R R, Output 3
  2. R R G B -> R [R G] B -> [R B] B -> [G B] -> R, Output 1
  3. R G R G -> [R G] R G -> [B R] G ->G G, Output 2
  4. R G B B G R -> R [G B] B G R ->R [R B] G R ->[R G] G R 
                    -> [B G] R ->R R, Output 2
  5. R R B B G -> R [R B] [B G] -> R [G R] -> [R B] -> G,
                     Output 1
 * http://www.geeksforgeeks.org/find-minimum-elements-considering-possible-transformations/
 * RGGRB ->  
 * 
 * https://ideone.com/f948UN
 */
public class ColorTransformation {

	public static void main(String[] args) {
		mincolor();
	}

	public static void mincolor() {
		// input char array
		char[] ca =  {'R','R','R','G'};
		// stack containing transformed color
		Stack<Character> st = new Stack<>();
		// Initially push the first 2 values from input array
		st.push(ca[0]);
		st.push(ca[1]);
		for(int i=2; i<ca.length; i++) {
			// If the current element and its next element is same then add it to stack and transform the stack
			// This is to handle the case such as 'G','B','R','R' in which case 'B' and 'R' should be transformed and not 'G' and 'B' added to stack
			if(i < ca.length-1 && (ca[i] == ca[i+1])) {
				st.push(ca[i]);
				transform(st);
			} 
			// If not then transform elements in stack and add current color to stack
			else {
				transform(st);
				st.push(ca[i]);
			}			
		}
		// Transform one last time to make sure nothing is skipped because of the else condition above
		transform(st);
		System.out.println("The transformed color array is "+st);

	}

	/*
	 * Main logic of transforming colors in stack
	 */
	public static void transform(Stack<Character> st) {
		char t1, t2, t3;
		int c = 1;
		while(st.size() > 1) {
			t1 = st.pop();
			t2 = st.pop();
			// If last 2 colors are same then handle special cases 
			if(t1 == t2) {
				// case 1:- only 2 colors in stack and both same then add them back and stop looping the stack
				if(st.empty()) {
					st.push(t1);
					st.push(t2);
					break;
				} 					
				else {
					// case 2:- check how many colors are same from last and use a counter c
					while(t1 == t2 && !st.empty()) {
						t2 = st.pop();
						c++;
					}
					// case 3:- if all colors are same in stack then add all of them back and stop looping stack
					if(t1 == t2) {
						do {
							st.push(t1);
						} while(--c >= 0);
						break;
					} 
					// case 4:- if last c colors in stack are same then transform the last c-1 color with the repeating color and do it c times with the resulting color
					else {
						t3 = t2;
						do {
							t3 = transform(t1, t3);								
						} while(--c >= 0);
						st.push(t3);
					}
				}
			} else {
				// transform the 2 colors and add back their result into the stack
				t3 = transform(t1, t2);
				st.push(t3);
			}			
		}
	}

	/*
	 * Apply logic to return appropriate color based on input colors
	 * eg:- input is (R,G) --> output is B
	 */
	public static char transform(char a, char b) {
		if(a == b) return a;
		if(a == '\u0000') return b;
		if(b == '\u0000') return a;
		if((a == 'R' || b == 'R') && (a == 'B' || b == 'B')) return 'G';
		else if((a == 'R' || b == 'R') && (a == 'G' || b == 'G')) return 'B';
		else return 'R';
	}

}
