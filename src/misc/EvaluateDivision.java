package misc;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class EvaluateDivision {
	private static final double NORESULT = -1.0;
	public double[] calcEquation(String[][] equations, double[] vals, String[][] queries) {
		Map<String, Map<String, Double>> graph = buildGraph(equations, vals);
		double[] result = new double[queries.length];
		for (int i = 0; i < queries.length; i++) {
			result[i] = calcValue(graph, queries[i], new HashSet<String>());
		}
		return result;
	}
	
	private Map<String, Map<String, Double>> buildGraph(String[][] equations, double[] vals) {
		Map<String, Map<String, Double>> graph = new HashMap<>();
		for (int i = 0; i < equations.length; i++) {
			// point a to b 
			graph.putIfAbsent(equations[i][0], new HashMap<>());
			graph.get(equations[i][0]).put(equations[i][1], vals[i]);
			// point b to a
			graph.putIfAbsent(equations[i][1], new HashMap<>());
			graph.get(equations[i][1]).put(equations[i][0], 1 / vals[i]);		
		}
		return graph;
	}
	
	private double calcValue(Map<String, Map<String, Double>> graph, String[] query, Set<String> visited) {
		// a dfs way to find the result of query
		if (!graph.containsKey(query[0]) || visited.contains(query[0])) {
			return NORESULT;
		}
		if (query[0] == query[1]) {
			return 1.0;
		}
		visited.add(query[0]);
		Map<String, Double> neighbors = graph.get(query[0]);
		if (neighbors.containsKey(query[1])) {
			return neighbors.get(query[1]);
		}
		for (Map.Entry<String, Double> nei : neighbors.entrySet()) {
			double neiResult = calcValue(graph, new String[] {nei.getKey(), query[1]}, visited);
			if (neiResult != -1.0) {
				return nei.getValue() * neiResult;
			}
		}
		visited.remove(query[0]);
		return NORESULT;
	}
}
