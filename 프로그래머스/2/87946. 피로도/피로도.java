import java.util.*;
class Solution {
    public static int max = 0;

	public static int solution(int k, int[][] dungeons) {
		// 던전의 개수가 8개로 적음 O(2^8) 임으로 Permutation
		int[][] output = new int[dungeons.length][2];
		boolean[] visited = new boolean[dungeons.length];
		permutation(dungeons, output, visited, 0, dungeons.length, dungeons.length, k);
		return max;
	}

	public static void permutation(int[][] arr, int[][] output, boolean[] visited, int depth, int n, int r, int k) {
		if (depth == r) {
			int cnt = 0;
			for (int i = 0; i < output.length; i++) {
				if (k >= output[i][0]) {
					k -= output[i][1];
					cnt++;
				}
			}

			max = Math.max(max, cnt);
			return;
		}

		for (int i = 0; i < n; i++) {
			if (!visited[i]) {
				visited[i] = true;
				output[depth] = arr[i];
				permutation(arr, output, visited, depth + 1, n, r, k);
				visited[i] = false;
			}
		}
	}
}