package utils;

public class ConsolePrinter {
	
	public static void printIntArray(int[] nums) {
		if (nums == null || nums.length == 0) {
			return;
		}
		for (int i=0; i < nums.length; i++) {
			System.out.print(nums[i] + " ");
		}
	}

	public static void printIntMatrix(int[][] mat) {
		if (mat == null || mat.length == 0 || mat[0].length == 0) {
			return;
		}
		for (int i=0; i < mat.length; i++) {
			printIntArray(mat[i]);
			System.out.println();
		}
	}

}
