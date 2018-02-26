package utils;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.stream.Collectors;

public class TreeNode {
   public int key;
   public TreeNode left;
   public TreeNode right;
   public TreeNode(int key) {
     this.key = key;
   }
   
   public static TreeNode createTreeFromStringArr(String[] vals) {
	   
	   if (vals == null || vals.length == 0) {
		   return null;
	   }
	   TreeNode root = new TreeNode(Integer.parseInt(vals[0]));
	   int idx = 1;
	   ArrayList<TreeNode> parents = new ArrayList<TreeNode>();
	   parents.add(root);
	   while (!parents.isEmpty()) {
		   ArrayList<TreeNode> children = new ArrayList<TreeNode>();
		   for (int i = 0; i < parents.size(); i++) {
			   TreeNode cur = parents.get(i);
			   cur.left = getNextNode(vals, idx++);
			   if (cur.left != null) {
				   children.add(cur.left);
			   }
			   cur.right = getNextNode(vals, idx++);
			   if (cur.right != null) {
				   children.add(cur.right);
			   }
		   }
		   parents.clear();
		   parents = children;
	   }
	   return root;
	   
   }
   
   public static TreeNode createTreeFromString(String strNodes) {
	   if (strNodes == null || strNodes.length() == 0) {
		   return null;
	   }
	   String[] svals = strNodes.split(",\\s*");
	   ArrayList<String> vals = new ArrayList<String>();
	   for (String sval : svals) {
		   if (sval == "") {
			   continue;
		   }
		   vals.add(sval);
	   }
//	   vals = (ArrayList<String>) vals.stream()
//			    .filter(ele -> ele != "").collect(Collectors.toList());
	   
	   TreeNode root = new TreeNode(Integer.parseInt(vals.get(0)));
	   int idx = 1;
	   ArrayList<TreeNode> parents = new ArrayList<TreeNode>();
	   parents.add(root);
	   while (!parents.isEmpty()) {
		   ArrayList<TreeNode> children = new ArrayList<TreeNode>();
		   for (int i = 0; i < parents.size(); i++) {
			   TreeNode cur = parents.get(i);
			   cur.left = getNextNode(vals, idx++);
			   if (cur.left != null) {
				   children.add(cur.left);
			   }
			   cur.right = getNextNode(vals, idx++);
			   if (cur.right != null) {
				   children.add(cur.right);
			   }
		   }
		   parents.clear();
		   parents = children;
	   }
	   return root;
   }
   
   private static TreeNode getNextNode(ArrayList<String> vals, int idx) {
	   if (idx >= vals.size()) {
		   return null;
	   }
	   if (vals.get(idx).equals("#") || vals.get(idx).equals("null")) {
		   return null;
	   }
	   TreeNode node = new TreeNode(Integer.parseInt(vals.get(idx)));
	   return node;
   }
   
   private static TreeNode getNextNode(String[] vals, int idx) {
	   if (idx >= vals.length) {
		   return null;
	   }
	   if (vals[idx].equals("#") || vals[idx].equals("null")) {
		   return null;
	   }
	   TreeNode node = new TreeNode(Integer.parseInt(vals[idx]));
	   return node;
   }
   
   public static void printTree(TreeNode root) {
	   if (root == null) {
		   return;
	   }
	   StringBuilder sb = new StringBuilder();
	   Deque<TreeNode> queue = new LinkedList<>();
	   queue.offerLast(root);
	   while (!queue.isEmpty()) {
		   int lsize = queue.size();
		   for (int i = 0; i < lsize; i++) {
			   TreeNode cur = queue.pollFirst();
			   if (cur == null) {
				   sb.append("#, ");
				   continue;
			   }
			   sb.append(cur.key+", ");
			   queue.offerLast(cur.left);
			   queue.offerLast(cur.right);
		   }
		   sb.append("\r\n");
	   }
	   System.out.println(sb.toString());
   }
}

