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
 */
public class CopyOfColorTransformation {

	public static void main(String[] args) {
		mincolorusingstack();
	}
		
	public static void mincolorusingstack() {
		char[] ca =  {'G','B','R','R','R','R','R','R'};				
		Stack<Character> st = new Stack<>();		
		st.push(ca[0]);
		st.push(ca[1]);
		for(int i=2; i<ca.length; i++) {
			if(i < ca.length-1 && (ca[i] == ca[i+1])) {
				st.push(ca[i]);
				transform(st);
			} else {
				transform(st);
				st.push(ca[i]);
			}			
		}
		transform(st);
		System.out.println(st);
		
	}

	public static void transform(Stack<Character> st) {
		char t1, t2, t3;
		int c = 0;
		while(true) {
			if(st.size() > 1) {
				t1 = st.pop();
				t2 = st.pop();
				if(t1 == t2) {
					if(st.empty()) {
						st.push(t1);
						st.push(t2);
						break;
					} else {
						while(t1 == t2 && !st.empty()) {
							t2 = st.pop();
							c++;
						}
						if(t1 == t2) {
							do {
								st.push(t1);
							} while(--c > 0);
						} else {
							t3 = t2;
							do {
								t3 = transform(t1, t3);
								st.push(t3);
							} while(--c > 0);
						}
					}
				} else {
					t3 = transform(t1, t2);
					st.push(t3);
				}
			} else {				
				break;
			}
		}
		System.out.println(st);
	}
	
	public static char transform(char a, char b) {
		if(a == b) return a;
		if(a == '\u0000') return b;
		if(b == '\u0000') return a;
		if((a == 'R' || b == 'R') && (a == 'B' || b == 'B')) return 'G';
		else if((a == 'R' || b == 'R') && (a == 'G' || b == 'G')) return 'B';
		else return 'R';
	}

}
