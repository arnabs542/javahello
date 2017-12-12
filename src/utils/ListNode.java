package utils;

import java.util.ArrayList;
import java.util.Iterator;

public class ListNode {
   public int value;
   public ListNode next;
   public ListNode(int value) {
     this.value = value;
     next = null;
   }
   
   public static ListNode array2List(int[] arr) {
	   ListNode dum = new ListNode(-1);
	   ListNode tail = dum;
	   for(int val: arr) {
		   ListNode tmp = new ListNode(val);
		   tail.next = tmp;
		   tail = tail.next;
	   }
	   return dum.next;
   }

   public static int[] list2Array(ListNode head) {
	   ArrayList<Integer> al = new ArrayList<Integer>();
	   if (head == null)
		   return new int[0];
	   while(head != null) {
		   al.add(head.value);
		   head = head.next;
	   }
	   
	   int[] ret = new int[al.size()];
	    Iterator<Integer> iterator = al.iterator();
	    for (int i = 0; i < ret.length; i++)
	    {
	        ret[i] = iterator.next().intValue();
	    }
	    return ret;
//	   return al.toArray(new int[0]);
   }

}
