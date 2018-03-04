package hw;

import java.util.List;

public class NinetyFivePercentile {
	public int percentil95(List<Integer> lengths) {
		// Assumptions: lengths is not null and size >= 1 without
		// any null values.
		// The length of possible longest url is 4096.
		int[] count = new int[4097];
		for (int len : lengths) {
			count[len]++;
		}
		int sum = 0;
		int len = 4097;
		while (sum <= 0.05 * lengths.size()) {
			sum += count[--len];
		}
		return len;
	}

	public int percentile95_self_ac(List<Integer> lengths) {
		int[] counts = new int[4097]; // 0 - 4096
		for (int len : lengths) {
			counts[len]++;
		}
		int subSum = 0;
		for (int i = 4096; i >= 0; i--) {
			subSum += counts[i];
			// if ((float)subSum / (float)lengths.size() > 0.05) { // !!! float need to be
			// (float)
			if (subSum > lengths.size() * 0.05) { // !!! above way will have some error, use * instead of / when
													// possible!!!
				// return i + 1; // this is not correct, i, not i + 1
				return i;
			}
		}
		return 0; // note ref use 4097 to return, while self 0 is also ac
	}
}
