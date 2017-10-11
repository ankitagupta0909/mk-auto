package com.markit.demo;

public class BinaryTree {

	Node root;
	

	public static class Node{
	
		int key;
		Node left,right;
		
		Node(int item){
			this.key=item;
      }
	}
	
	
	public static void main(String[] args) {

		BinaryTree bin=new BinaryTree();
		bin.root=new Node(1);
		bin.root.left=new Node(2);
		bin.root.right=new Node(3);
		
		System.out.println(bin.root);
		
	}

}
