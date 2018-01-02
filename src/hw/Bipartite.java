package hw;
/**
 * Graph
Bipartite
Determine if an undirected graph is bipartite. A bipartite graph is one in which the nodes can be divided into two groups such that no nodes have direct edges to other nodes in the same group.

Examples

1  --   2

    /   

3  --   4

is bipartite (1, 3 in group 1 and 2, 4 in group 2).

1  --   2

    /   |

3  --   4

is not bipartite.

Assumptions

The graph is represented by a list of nodes, and the list of nodes is not null.
 

 


 * @author 
 *
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * public class GraphNode { public int key; public List<GraphNode> neighbors;
 * public GraphNode(int key) { this.key = key; this.neighbors = new
 * ArrayList<GraphNode>(); } }
 */
public class Bipartite {
	public boolean isBipartite(List<GraphNode> graph) {
		if (graph == null || graph.size() == 0) {
			return true;
		}
		// use 0 or 1 to denote two different groups ( colors )
		// the map maintains each node and its group after visited.
		HashMap<GraphNode, Integer> nodeRecords = new HashMap<GraphNode, Integer>();
		// because a bipartite graph may not be fully connected, so need to check every
		// node, one BFS may not cover all the nodes. -- self.
		// the graph can be represented by a list of nodes,
		// the graph is not guaranteed to be connected.
		// We have to do BFS from each of the nodes.
		for (GraphNode node : graph) {
			if (!BFS(node, nodeRecords)) {
				// use BFS to check if the node and connected nodes can be a valid
				// if not return false
				return false;
			}
		}
		return true;
	}

	private boolean BFS(GraphNode node, HashMap<GraphNode, Integer> nodeRecords) {
		// if the node has been traversed, no need to BFS again.
		// and successfully put to record means that the node is a 'valid' node. -- self.
		if (nodeRecords.containsKey(node)) {
			return true;
		}
		Queue<GraphNode> queue = new LinkedList<GraphNode>();
		// start BFS from the node, since the node is not visited,
		// we can assign it to any of the groups, for example, group 0.
		queue.offer(node);
		nodeRecords.put(node, 0);
		while (!queue.isEmpty()) {
			GraphNode cur = queue.poll();
			// the neighbor should be in a different group / color than current node -- self
			int neiColor = nodeRecords.get(cur) == 0 ? 1 : 0;
			for (GraphNode neighbor : cur.neighbors) {
				if (nodeRecords.containsKey(neighbor)) {
					if (nodeRecords.get(neighbor) != neiColor) {
						return false;
					}
				} else {
					nodeRecords.put(neighbor, neiColor);
					queue.offer(neighbor);
				}
			}
		}
		return true;
	}
}

class GraphNode {
	public int key;
	public List<GraphNode> neighbors;

	public GraphNode(int key) {
		this.key = key;
		this.neighbors = new ArrayList<GraphNode>();
	}
}
