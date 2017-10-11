package com.markit.demo;

public class LinkedList {
    Node head;
    
	public static class Node{
		
		int data;
        Node next;
		Node(int i){
			this.data=i;
		}
        Node(int i, Node n){
			this.data=i;
			this.next=n;		
		}
		
	}
	
	
public static void main(String[] args) {
	LinkedList l=new LinkedList();
	
	//Node fourth=new Node(4);
	Node fourth=new Node(4);
	Node third=new Node(3,fourth);
	Node second=new Node(2,third);	
	l.head=new Node(1,second);
	//fourth.next=l.head;
	splitLinkList(l);
	
	//l.head=l.reverseLinkedList(l.head);
	//l.removeFromLinkedList(3);
	//l.printLinkedList(l.head);
	}

public static void splitLinkList(LinkedList l){

	int count=0;
	Node next=l.head;
	Node prev=null;
		while(next!=null){
			count++;
			prev=next;
			next=next.next;
			if(next!=null&&next.data==l.head.data){
				break;			
			} 
		}
		Node last=prev;
		if(last.next==null){
			System.out.println("linear");
		}else{
			System.out.println("circular");
		}
		System.out.println(last.data);
		System.out.println(count);
	
}
/*
public void printLinkedList(Node head){
	Node next=head;
	while(next!=null){
		System.out.println(next.data); 
		next=next.next;		
	}		
}

public void removeFromLinkedList(int key){
	int count=0;
	Node next=head;
    Node prev=null;
	while(next!=null){
		count++;
		if(next.data==key){
		break;
		} 
		prev=next;
		next=next.next;		
	}			
   prev.next=next.next;
   next=null;   	
}

public Node reverseLinkedList(Node head){
	Node next=null;
	Node current=head;
	Node prev=null;
	while(current!=null){
		next=current.next;
		current.next=prev;
		prev=current;
		current=next;
	}
	return prev;
}*/
}