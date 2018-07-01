package misc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * 
 * 332. Reconstruct Itinerary
 * 
 * Given a list of airline tickets represented by pairs of departure and arrival
 * airports [from, to], reconstruct the itinerary in order. All of the tickets
 * belong to a man who departs from JFK. Thus, the itinerary must begin with
 * JFK.
 * 
 * Note:
 * 
 * If there are multiple valid itineraries, you should return the itinerary that
 * has the smallest lexical order when read as a single string. For example, the
 * itinerary ["JFK", "LGA"] has a smaller lexical order than ["JFK", "LGB"]. All
 * airports are represented by three capital letters (IATA code). You may assume
 * all tickets form at least one valid itinerary. 
 * 
 * Example 1:
 * 
 * Input: tickets = [["MUC", "LHR"], ["JFK", "MUC"], ["SFO", "SJC"], ["LHR",
 * "SFO"]] 
 * Output: ["JFK", "MUC", "LHR", "SFO", "SJC"] 
 * 
 * Example 2:
 * 
 * Input: tickets =
 * [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
 * Output: ["JFK","ATL","JFK","SFO","ATL","SFO"] 
 * 
 * Explanation: Another possible
 * reconstruction is ["JFK","SFO","ATL","JFK","ATL","SFO"]. But it is larger in
 * lexical order.
 * 
 * Difficulty:Medium Total Accepted:51K Total Submissions:169.8K
 * Contributor:LeetCode Companies
 * 
 * Related Topics
 * 
 * 
 * @author
 *
 */

public class ReconstructItinerary {
	/**
	 * 欧拉路径 Eulerian path ( visit every edge exactly once).
	 * Hierholzer算法
	 * 
	 * path = []
	 * DFS(u):
	 * 	 while (u 存在未被访问的边e(u,v)):
	 * 		mark边e(u,v) 为访问
	 * 		DFS(v)
	 * 	 end-of-while
	 *   path.pushLeft(u)
	 * 
	 * @param tickets
	 * @return
	 */
	public List<String> findItinerary(String[][] tickets) {
		if (tickets == null || tickets.length == 0) {
			return null;
		}
		Map<String, PriorityQueue<String>> map = new HashMap<>();
		LinkedList<String> result = new LinkedList<>(); // use 
		// construct result use LinkedList instead of ArrayList cause need to pushLeft
		
		for (String[] ticket : tickets) {
			map.putIfAbsent(ticket[0], new PriorityQueue<String>());
			map.get(ticket[0]).offer(ticket[1]);
		}
		dfs("JFK", result, map);
		return result;
	}
	
	private void dfs(String start, LinkedList<String> result, Map<String, PriorityQueue<String>> map) {
		PriorityQueue<String> q = map.get(start);
		while (q != null && !q.isEmpty()) {
			dfs(q.poll(), result, map);
		}
		result.addFirst(start);
	}
	
}

class ReconstructItinerary_BRUTEFORCE {
	/**
	 * Basic idea, use graph to model the cities, use tickets to build directed edges.
	 * Use DFS to test possible route. Since there are n tickets, 
	 * so the route represented as points will be n + 1 (n tickets)
	 * ( n edges --> n + 1 stops on the path t1s, t1e(t2s), t2e(t3s), t3e ==> 4 points
	 * 
	 * --> ref
	 * 从JFK开始尝试全部可能的路径， 返回第一个用完全部ticket的路径。
	 * 1. 把input转换成为图的结构，给定一个点，可以访问所有neighbors节点，以lexical order的顺序
	 * 访问他们
	 * 2. 在构造的图上从JFK开始做DFS，remove掉访问过的边，backtrack的时候再加回来。
	 * 
	 * n : n tickets (edges)
	 * Time: O(n) construct graph map + O(nlogn) sort map's key's neighbor list + O(n!) DFS
	 * Space: O(n)
	 * 
	 * @param tickets
	 * @return
	 */
    public List<String> findItinerary(String[][] tickets) {
        if (tickets == null || tickets.length == 0) {
        	return null;
        }
        int totalLen = tickets.length + 1; // when all tickets are used, totalLen of path is n + 1
        // map : maintain from -> to info according to tickets
        Map<String, List<String>> map = new HashMap<>();
        for (String[] ticket: tickets) {
        	map.computeIfAbsent(ticket[0], k -> new ArrayList<String>()).add(ticket[1]);
        }
        // sort destination so that the result will be in 'lexical order'
        for ( List<String> list : map.values()) {
        	Collections.sort(list);
        }
//        List<String> starts = new ArrayList<>(map.keySet());
//        Collections.sort(starts);
//        for (String start : starts) {
//            List<String> result = new ArrayList<>();
//            result.add(start);
//            if (findItinerary(start, result, map, totalLen)) {
//            	return result;
//            }        	
//        }
        // problem states that "a man who departs from JFK"
        String start = "JFK";
        List<String> result = new ArrayList<>();
        result.add(start);
        if (findItinerary(start, result, map, totalLen)) {
        	return result;
        }   
        return null;
    }
    
    private boolean findItinerary(String start, List<String> path, Map<String, List<String>> map, int totalLen) {
    	if (path.size() == totalLen) {
    		return true;
    	}
    	if (!map.containsKey(start)) {
    		return false;
    	}
    	List<String> destinations = map.get(start);
    	for (int i = 0; i < destinations.size(); i++) {
    		String dest = destinations.get(i);
    		destinations.remove(i);
    		path.add(dest);
    		if (findItinerary(dest, path, map, totalLen)) {
    			return true;
    		}
    		// recover the context to try other dest (with i++)
    		path.remove(path.size() - 1);
    		destinations.add(i, dest);
    	}
    	return false;
    }
}
