package hw;

import java.util.Arrays;
import java.util.Comparator;

/***
 * 
 * Given an array of 2D coordinates of points (all the coordinates are
 * integers), find the largest number of points that can form a set such that
 * any pair of points in the set can form a line with positive slope. Return the
 * size of such a maximal set.
 * 
 * Assumptions
 * 
 * The given array is not null 
 * Note: if there does not even exist 2 points can
 * form a line with positive slope, should return 0. 
 * 
 * Examples
 * 
 * <0, 0>, <1, 1>, <2, 3>, <3, 3>, the maximum set of points are {<0, 0>, <1,
 * 1>, <2, 3>}, the size is 3.
 * 
 * Medium Array
 * 
 * 
 * @author
 *
 */

/*
 * class Point { public int x; public int y; public Point(int x, int y) { this.x
 * = x; this.y = y; } }
 */

public class LargestSetOfPointsWithPositiveSlopes {
	public int largest(Point[] points) {
		// Assumptions: points is not null.
		// We need to sort the points first by y then by x.
		Arrays.sort(points, new MyComparator());
		// similar to longest ascending subsequence.
		int result = 1;
		int[] longest = new int[points.length];
		// --> looking for largest increasing sub-sequence.
		for (int i = 0; i < longest.length; i++) {
			longest[i] = 1; // better to init as 1 here, make it clearer
			for (int j = 0; j < i; j++) {
				if (points[j].x < points[i].x && points[j].y < points[i].y) {
					longest[i] = Math.max(longest[i], longest[j] + 1);
				}
			}
			// longest[i]++; //!!! important, otherwise wrong, result 2 will become(1-> 0)
			// --> because here longest[] are 0s initially, and therefore need to ++ here.
			// if not should initialize it as 1s.
			result = Math.max(result, longest[i]);
		}
		return result == 1 ? 0 : result;  // !!! need to remember there may be all negative slopes. 
		// --> 1 points means 0 result for the problem
	}
	
	static class MyComparator implements Comparator<Point> {

		@Override
		public int compare(Point p0, Point p1) {
			if (p0.x == p1.x) {
				if (p0.y == p1.y) {
					return 0;
				} else {
					return p0.y < p1.y ? -1 : 1;
				} 
			} else {
				return p0.x < p1.x ? -1 : 1;
			}
		}
		
	}
	
	public static void main(String[] args) {
		Point[] points = new Point[] {new Point(1, 2), new Point(2, 4)}; // expect 2
		LargestSetOfPointsWithPositiveSlopes sol = new LargestSetOfPointsWithPositiveSlopes();
		int result = sol.largest(points);
		System.out.println(result);
	}
}

class Point {
	public int x;
	public int y;

	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
