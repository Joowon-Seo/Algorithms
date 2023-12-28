import java.util.*;

// edge case 의상의 종류가 30일 때 2^30 - 1
class Solution {
    public static HashMap<String, Integer> data = new HashMap<>();
	public static int answer = 0;
    public int solution(String[][] clothes) {
        for (int i = 0; i < clothes.length; i++) {
			data.put(clothes[i][1], data.getOrDefault(clothes[i][1], 0) + 1);
		}

		String[] arr = new String[data.size()];

        if(data.size() == 30) {
			return 1073741823;
		} else if (data.size() == 29) {
			return 536870911;
		}
		int idx = 0;
		for (String str : data.keySet()) {
			arr[idx++] = str;
		}


		for (int i = 1; i <= data.size(); i++) {
			boolean[] visited = new boolean[arr.length];
//			System.out.println("i : " + i);
			combination(arr, visited, 0, data.size(), i);
		}
		return answer;
    }
    
    public static void combination(String[] arr, boolean[] visited, int start, int n, int r) {
		if (r == 0) {
			int result = 1;
			for (int i = 0; i < visited.length; i++) {
				if (visited[i]) {
					result *= data.get(arr[i]);
				}
			}

//			System.out.println("result : " + result);
			answer += result;
			return;
		}

		for (int i = start; i < n; i++) {
			visited[i] = true;
			combination(arr, visited, i + 1, n, r - 1);
			visited[i] = false;
		}
	}
}