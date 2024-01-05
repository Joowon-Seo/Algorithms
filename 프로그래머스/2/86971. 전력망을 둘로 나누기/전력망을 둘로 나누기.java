import java.util.*;
class Solution {
    public static List<List<Integer>> graph;
	public static boolean[] visited;
	public static int[] subTreeSize;
	public static int answer = Integer.MAX_VALUE;
    public int solution(int n, int[][] wires) {
        graph = new ArrayList<>();
		visited = new boolean[n + 1];
		subTreeSize = new int[n + 1];

		for (int i = 0; i <= n; i++) {
			graph.add(new ArrayList<>());
		}

		for (int i = 0; i < wires.length; i++) {
			int parent = wires[i][0];
			int child = wires[i][1];
			graph.get(parent).add(child);
			graph.get(child).add(parent);
		}

		dfs(wires[0][0], n);

		return answer;
    }
    
    public static void dfs(int node, int n) {
		visited[node] = true;
		subTreeSize[node] = 1;

		for (int i = 0; i < graph.get(node).size(); i++) {
			int child = graph.get(node).get(i);
			if (!visited[child]) {
				dfs(child, n);
				subTreeSize[node] += subTreeSize[child];
			}	
		}
        int diff = n - subTreeSize[node];
		answer = Math.min(answer, Math.abs(subTreeSize[node] - diff));
	}
}