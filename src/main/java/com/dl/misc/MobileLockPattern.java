package com.dl.misc;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/*
 * http://www.geeksforgeeks.org/number-of-ways-to-make-mobile-lock-pattern/
 * 
 * https://ideone.com/t3To9s
 */
public class MobileLockPattern {

	public Node[][] nodes = new Node[3][3];
	public int m = 3;
	public int n = 3;
	public int cmin = 2;
	public int cmax = 2;
	public int noOfPaths;
	public List<Node> pathNodes = new ArrayList<Node>();
	
	public static void main(String[] args) {
		MobileLockPattern p = new MobileLockPattern();
		p.buildPattern();
		p.traverse();
		System.out.println("The number of paths is "+p.noOfPaths);
	}
	
	public void traverse() {
		for(int i=0; i<m; i++) {
			for(int j=0 ; j<n; j++) {
				Node n = nodes[i][j]; 
				navigatePattern(n, 1);				
			}
		}
	}
	
	public void navigatePattern(Node n, int l) {
		boolean isAdded = false;
		if(!n.isVisited) {		
			n.isVisited = true;
			isAdded = pathNodes.add(n);
			for(Node t : n.connects) {
				if(t.isVisited) {
					continue;
				}
				if(l < cmin) {
					navigatePattern(t, l+1);					
				} 
				else if(l < cmax) {			
					if(isAdded) {
						System.out.println("The path is "+pathNodes);
						noOfPaths++;
						isAdded = false;
					}						
					navigatePattern(t, l+1);					
				}
				if(l >= cmax) {				
					System.out.println("The path is "+pathNodes);
					noOfPaths++;
					break;
				}
			}
			System.out.println("Remove node "+n);	
			pathNodes.remove(n);
			n.isVisited = false;
		}
	}
	
	public void buildPattern() {
		int v = 1;
		for(int i=0; i<m; i++) {
			for(int j=0 ; j<n; j++) {
				Node n = new Node(v++);
				nodes[i][j] = n;
			}					
		}
		linkNodes();
	}

	public void linkNodes() {		
		Node a;
		for(int i=0; i<m; i++) {
			for(int j=0; j<n; j++) {
				Node cur = nodes[i][j];				
				if(i-1 >=0) {
					if(j-1 >= 0) {
						a = nodes[i-1][j-1];
						cur.addAdj(a);
					}
					a = nodes[i-1][j];
					cur.addAdj(a);
					if(j+1 < n) {
						a = nodes[i-1][j+1];
						cur.addAdj(a);
					}
				}
				if(j-1 >= 0) {
					a = nodes[i][j-1];
					cur.addAdj(a);
				}
				if (j+1 < n) {
					a = nodes[i][j+1];
					cur.addAdj(a);
				}
			}
		} 
	}
}

class Node implements Comparable<Node>{
	public Integer v;
	public boolean isVisited;
	public Set<Node> connects = new TreeSet<Node>();
	
	public Node(Integer v) {
		this.v = v;
	}	
	public void addAdj(Node adj) {
		this.connects.add(adj);
		adj.connects.add(this);
	}
	public boolean equals(Object n){
		if(this.v == ((Node)n).v) return true;
		else return false;
	}
	@Override
	public int compareTo(Node o) {
		return this.v.compareTo(o.v);
	}
	
	public String toString() {
		return String.valueOf(v);
	}
}