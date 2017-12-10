package utils;

public class ConsolePrinter {
	
	public static void printIntArray(int[] nums) {
		if(nums == null || nums.length == 0) {
			return;
		}
		for(int i=0; i < nums.length; i++) {
			System.out.print(nums[i] + " ");
		}
	}

}
