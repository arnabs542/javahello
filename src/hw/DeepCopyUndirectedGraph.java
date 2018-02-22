package hw;

import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 
 * Make a deep copy of an undirected graph, there could be cycles in the
 * original graph.
 * 
 * Assumptions
 * 
 * The given graph is not null 
 * 
 * Medium 
 * Breadth First Search 
 * Depth First Search
 * 
 * @author
 *
 */
public class DeepCopyUndirectedGraph {
	public List<GraphNode> copy(List<GraphNode> graph) {
		if (graph == null) {
			return null;
		}
		// List<GraphNode> result = new ArrayList<>();
		Map<GraphNode, GraphNode> map = new HashMap<>();
		for (GraphNode node : graph) {
			if (!map.containsKey(node)) {
				map.put(node, new GraphNode(node.key));
				// result.add(map.get(node));
				// dfs(node, result);
				dfs(node, map);
			}
		}
		//return result;
		return new ArrayList<GraphNode>(map.values()); // !! good way to convert and return result here!!!
	}
	
	private void dfs(GraphNode seed, Map<GraphNode, GraphNode> map) {
		GraphNode copy = map.get(seed);
		for (GraphNode nei : seed.neighbors) {
			if (!map.containsKey(nei)) {
				map.put(nei, new GraphNode(nei.key));
				dfs(nei, map);
			}
			copy.neighbors.add(map.get(nei));
		}
	}
	
}
