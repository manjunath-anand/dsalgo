package com.dl.misc;

import java.util.LinkedList;
import java.util.Queue;

// http://www.geeksforgeeks.org/find-a-number-in-minimum-steps/
/*
 * 				  0
 * 		   -1	    	  1
 *      -3      1       -1     3
 *   -6    0 -2   4  -4    2  0   6
 */
public class MinimumStepsProblem {

	public static void main(String[] args) {
		int n = 10;
		Node e, rt, lt;
		Queue<Node> q = new LinkedList<>();
		q.add(new Node(0,0));
		while(!q.isEmpty()) {
			e = q.poll();
			//if (e.v >= n || e.v <= n) break;
			if(e.v == n) {
				System.out.println("Found the number at level "+(e.l-1));
				break;
			}
			lt = new Node(e.v-e.l, e.l+1);
			rt = new Node(e.v+e.l, e.l+1);
			q.add(lt);
			q.add(rt);
		}
	}

	static class Node {
		int v;
		int l;
		public Node(int v, int l) {
			this.v = v;
			this.l = l;
		}
	}
}


