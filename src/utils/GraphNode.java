package utils;

import java.util.ArrayList;
import java.util.List;

public class GraphNode {
	public int key;
	public List<GraphNode> neighbors;
	
	public GraphNode(int x) {
		key = x;
		neighbors = new ArrayList<>();
	}
}
