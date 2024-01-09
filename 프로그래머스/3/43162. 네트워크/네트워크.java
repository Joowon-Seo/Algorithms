import java.util.*;
class Solution {
    public static boolean[] visited;
	public static List<List<Integer>> graph;
	public static int answer;
    public int solution(int n, int[][] computers) {
        visited = new boolean[n];
		graph = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			graph.add(new LinkedList<>());
			for (int j = 0; j < n; j++) {
				if (computers[i][j] == 1) {
					graph.get(i).add(j);
				}
			}
		}

		for (int i = 0; i < visited.length; i++) {
			if (!visited[i]) {
				dfs(i);
				answer++;
			}
		}
		return answer;
    }
    
    public static void dfs(int idx) {
		visited[idx] = true;
		for (int i = 0; i < graph.get(idx).size(); i++) {
			int adj = graph.get(idx).get(i);
			if (!visited[adj]) {
				dfs(adj);
			}
		}
	}
}