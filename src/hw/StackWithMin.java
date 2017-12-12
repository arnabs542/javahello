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

public class Solution {
  private Stack<Integer> stack = new Stack<Integer>();
  private PriorityQueue<Integer> pQueue = new PriorityQueue<Integer>();
  
  public Solution() {
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

