package hw;

import java.util.HashMap;

/**
 * 
 * Most Points On A Line
 * 
 * Description
 * 
 * Given an array of 2D coordinates of points (all the coordinates are
 * integers), find the largest number of points that can be crossed by a single
 * line in 2D space.
 * 
 * Assumptions
 * 
 * The given array is not null and it has at least 2 points
 * 
 * Examples
 * 
 * <0, 0>, <1, 1>, <2, 3>, <3, 3>, the maximum number of points on a line is
 * 3(<0, 0>, <1, 1>, <3, 3> are on the same line)
 * 
 * Medium Hashtable
 * 
 * @author
 *
 */
public class MostPointsOnALine {
	public int most(Point[] points) {
		// Assumptions: points is not null, and pints.length >= 2.
		// record the maximum number of points on the same line.
		int result = 0;
		// we use each pair of points to form a line.
		for (int i = 0; i < points.length; i++) {
			// any line can be represented by a point and a slope.
			// we take the point as seed and try to find all possible slopes.
			Point seed = points[i];
			// record the points with same <x, y>. --> !!! overlapping points!!!
			int same = 1;
			// record the points with same s, for the special case of infinite slope. (vertical)
			int sameX = 0;
			// record the maxmum number of points on the same line
			// crossing the seed points.
			int most = 0;
			// a map with all possible slopes.
			HashMap<Double, Integer> cnt = new HashMap<>();
			for (int j = 0; j < points.length; j++) { 
				//--> above j = 0, map is for seed only, not global, can't change to j = i + 1;
				// if use global map, need to store <slope, b (when x = 0)>
				// pros: two points will calculate only one cause j can start from j = i+1
				// cons: calc b, need to create new structure <slope, b> for key of the map
				// --> seed map can still do, cause just O(2n) calc still fine, code simpler
				if (i == j) {
					continue;
				}
				Point tmp = points[j];
				if (tmp.x == seed.x && tmp.y == seed.y) {
					same++;
				} else if (tmp.x == seed.x) {
					sameX++;
				} else {
					// otherwise, just calculate the slope and increment the counter
					// for the calculated slope.
					double slope = ((tmp.y - seed.y) + 0.0) / (tmp.x - seed.x);
					if (!cnt.containsKey(slope)) {
						cnt.put(slope, 1);
					} else {
						cnt.put(slope, cnt.get(slope) + 1);
					}
					most = Math.max(most, cnt.get(slope)); // update here to save iterate over map after for j
				}
			}
			most = Math.max(most, sameX) + same; // !!! same need to be accumulated.
			result = Math.max(result, most);
		}
		return result;
	}
}
