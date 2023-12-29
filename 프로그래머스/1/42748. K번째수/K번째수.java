import java.util.*;
class Solution {
    public static int[] arr;

    public int[] solution(int[] array, int[][] commands) {
        arr = array;
		List<Integer> list = new LinkedList<>();
		for (int i = 0; i < commands.length; i++) {
			list.add(sorting(commands[i][0] - 1, commands[i][1] - 1, commands[i][2] - 1));
		}
		return list.stream().mapToInt(Integer::intValue).toArray();
    }
    
    public static int sorting(int i, int j, int k) {
		List<Integer> list = new ArrayList<>();
		for (int l = i; l <= j; l++) {
			list.add(arr[l]);
		}
		Collections.sort(list);
		return list.get(k);
	}
}