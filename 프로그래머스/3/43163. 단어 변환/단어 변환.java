import java.util.*;
class Solution {
    public int solution(String begin, String target, String[] words) {
        boolean[] visited = new boolean[words.length];
		Queue<int[]> queue = new LinkedList<>();

		for (int i = 0; i < words.length; i++) {
			int cnt = 0;
			for (int j = 0; j < words[i].length(); j++) {
				if (begin.charAt(j) != words[i].charAt(j)) {
					cnt++;
				}
				if (cnt >= 2) break;
			}
			if (cnt == 1) {
				queue.add(new int[] {i, 1});
                visited[i] = true;
			}
		}

		while (!queue.isEmpty()) {
			int[] cur = queue.poll();
			if (Objects.equals(words[cur[0]], target)) {
				return cur[1];
			}
			for (int i = 0; i < words.length; i++) {
				if (!visited[i] && isPossible(words[cur[0]], words[i])) {
					visited[i] = true;
					queue.add(new int[] {i, cur[1] + 1});
				}
			}
		}
		return 0;
    }
    
    public static boolean isPossible(String pre, String next) {
		int cnt = 0;
		for (int i = 0; i < pre.length(); i++) {
			if (pre.charAt(i) != next.charAt(i)) {
				cnt++;
			}

			if (cnt >= 2) {
				return false;
			}
		}

		return cnt == 1;
	}
}