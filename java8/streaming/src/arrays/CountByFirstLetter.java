package arrays;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class CountByFirstLetter {
	
	public static long funStyle(String[] arr, String letter) {
		return Arrays.stream(arr).
				filter((x) -> x.startsWith(letter)).
				mapToInt((x) -> 1).
				count();
	}
	
	public static int noFunStyle(String[] arr, String letter) {
		Map<String, Integer> counts = new HashMap<String, Integer>();
		for (int i = 0; i < arr.length; i++) {
			if (counts.containsKey(arr[i].substring(0, 1))) {
				Integer count = counts.get(arr[i].substring(0, 1));
				counts.put(arr[i].substring(0, 1), ++count);
			} else
				counts.put(arr[i].substring(0, 1), 1);
		}
		return counts.get(letter);
	}
	
	public static void main(String[] args) {
		String[] arr1 = {"alice", "bob", "alice", "andrew", "alla", "bella"};
		assert CountByFirstLetter.noFunStyle(arr1, "a") == 4;
		assert CountByFirstLetter.funStyle(arr1, "a") == 4;
		assert CountByFirstLetter.funStyle(arr1, "b") == 2;
	}

}
