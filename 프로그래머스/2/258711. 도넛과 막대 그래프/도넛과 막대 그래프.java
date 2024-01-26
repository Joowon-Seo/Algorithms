import java.util.*;
class Solution {
    
    // 단방향 간선이 3개 이상인 점이 정점이 됨

	// 도넛 : 어느 점에서 시작하든 자기 자신으로 돌아옴, 간선의 선택지가 오로지 단일
	// 막대 : 간선을 타고가다보면 남은 간선이 존재하지 않음
	// 8자 : 간선을 타고가다보면 간선의 선택지가 두 개가 나옴

	public static Map<Integer, List<Integer>> graph;
	public static int[] answer;
    public static int[] cnt;
	public static boolean[] visited;
    
    public int[] solution(int[][] edges) {
        answer = new int[4];
		visited = new boolean[1000001];
        cnt = new int[1000001];
		int edgeSize = 0;
		int point = 0;
		graph = new HashMap<>();

		for (int i = 0; i < edges.length; i++) {
			if (!graph.containsKey(edges[i][0])) {
				graph.put(edges[i][0], new LinkedList<>());
			}
			graph.get(edges[i][0]).add(edges[i][1]);
            cnt[edges[i][1]]++;
			if (edgeSize < graph.get(edges[i][0]).size() && cnt[edges[i][0]] == 0) {
				edgeSize = graph.get(edges[i][0]).size();
				point = edges[i][0];
			}
		}

		answer[0] = point;
		for (int i = 0; i < graph.get(point).size(); i++) {
			dfs(graph.get(point).get(i));
		}
		return answer;
    }
    
    public static void dfs(int x) {
		visited[x] = true;
		if (!graph.containsKey(x)) {
			answer[2]++;
		} else if (graph.get(x).size() == 2) {
			answer[3]++;
		} else {
			int next = graph.get(x).get(0);
			if (visited[next]) {
				answer[1]++;
			} else {
				dfs(next);
			}
		}
	}
}