package hw;

/**
 * Data Structure
Stack With min()
Enhance the stack implementation to support min() operation. min() should return the current minimum value in the stack. If the stack is empty, min() should return -1.

pop() - remove and return the top element, if the stack is empty, return -1

push(int element) - push the element to top
top() - return the top element without remove it, if the stack is empty, return -1
min() - return the current min value in the stack.

 * 
 * @author 
 *
 */
import java.util.PriorityQueue;
import java.util.Stack;
import java.util.LinkedList;

public class StackWithMin {
	  private LinkedList<Integer> stack;
	  private LinkedList<Tuple> minStack;  
	  // the tuple of minvalue and its first pos (0 based) in stack
	  
	  public StackWithMin() {
	    // write your solution here
	    stack = new LinkedList<Integer>();
	    minStack = new LinkedList<Tuple>();
	  }
	  
	  public int pop() {
	    if (stack.isEmpty()) {
	      return -1;
	    }
	    int res = stack.pollLast();
	    // check if res is the min value 
	    if (res == minStack.peekLast().val1 ) {
	      if (stack.size() <= minStack.peekLast().val2 ) {
	        minStack.pollLast();
	      }
	    }
	    return res;
	  }
	  
	  public void push(int element) {
	    stack.offer(element);
	    if (minStack.size() == 0 || element < minStack.peekLast().val1) {
	      minStack.offer(new Tuple(element, minStack.size()));
	    }
	  }
	  
	  public int top() {
	    return stack.size() == 0 ? -1 : stack.peekLast();
	  }
	  
	  public int min() {
	    return stack.size() == 0 ? -1 : minStack.peekLast().val1;
	  }
	}

	class Tuple {
	  public int val1;
	  public int val2;
	  
	  public Tuple(int v1, int v2) {
	    val1 = v1;
	    val2 = v2;
	  }
	  
	}


class StackWithMin_self {
  private Stack<Integer> stack = new Stack<Integer>();
  private PriorityQueue<Integer> pQueue = new PriorityQueue<Integer>();
  
  public StackWithMin_self() {
    // write your solution here
  }
  
  public int pop() {
    if(this.stack.empty())
      return -1;
    int res = this.stack.pop();
    this.pQueue.remove(res);
    return res;
  }
  
  public void push(int element) {
    this.stack.push(element);
    this.pQueue.add(element);
  }
  
  public int top() {
    if(this.stack.empty())
      return -1;
    return this.stack.peek();
  }
  
  public int min() {
    if(this.stack.empty())
      return -1;
    return this.pQueue.peek();  
  }
}

