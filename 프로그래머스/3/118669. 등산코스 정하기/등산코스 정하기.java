import java.util.*;
class Solution {
    // 1. 다익스트라 -> 특정 조건을 만족하면서, 모든 정점을 지날 필요가 없고, 시작점과 종점에 대해 자유로움
	// 2. 시작점 -> 산봉우리, 도착한 산봉우리 -> 출발시 시작점, 두 경로는 동일하든 동일하지 않든 결과는 바뀌지 않음 (특정 조건 때문에)
	// 3. O(ElogV) -> O(200,000log50,000) 임으로 한 번의 다익스트라로 해결해야 함
	// 3-2 출발점은 궁금하지 않음, 모든 출발점 -> 산봉우리, 각 산봉우리 대한 intensity가 최소가 되는 산봉우리를 찾는다.
	// 4. 출발점과, 산봉우리는 한 번만 방문 -> 출발점은 나가는 경로만 간선에 추가하고, 산봉우리는 들어오는 간선만 추가



	public static class Node {
		int to;
		int weight;

		public Node(int to, int weight) {
			this.to = to;
			this.weight = weight;
		}
	}

	public static List<List<Node>> graph = new ArrayList<>();
	public static Set<Integer> gate = new HashSet<>();
	public static Set<Integer> summit = new HashSet<>();
	public static int[] intensity;
    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        int[] answer = {0, Integer.MAX_VALUE};
		for (int i = 0; i <= n ; i++) {
			graph.add(new LinkedList<>());
		}

		for (int i = 0; i < gates.length; i++) {
			gate.add(gates[i]);
		}

		for (int i = 0; i < summits.length; i++) {
			summit.add(summits[i]);
		}

		for (int i = 0; i < paths.length; i++) {
			int to = paths[i][0];
			int from = paths[i][1];
			int weight = paths[i][2];

			if (gate.contains(to) || summit.contains(from)) {
				graph.get(to).add(new Node(from, weight));
			} else if (gate.contains(from) || summit.contains(to)) {
				graph.get(from).add(new Node(to, weight));
			} else {
				graph.get(to).add(new Node(from, weight));
				graph.get(from).add(new Node(to, weight));
			}
		}

		intensity = new int[n + 1];
		dijkstra(n, gates);
		Arrays.sort(summits);
		for (int summit : summits) {
			if (intensity[summit] < answer[1]) {
				answer[0] = summit;
				answer[1] = Math.min(answer[1], intensity[summit]);
			}
		}


		return answer;
    }
    
    public static void dijkstra(int n, int[] gates) {
		for (int i = 1; i <= n; i++) {
			intensity[i] = Integer.MAX_VALUE;
		}

		PriorityQueue<Node> pq = new PriorityQueue<>((x, y) -> x.weight - y.weight);

		for (int i = 0; i < gates.length; i++) {
			intensity[gates[i]] = 0;
			pq.add(new Node(gates[i], 0));
		}
		while (!pq.isEmpty()) {
			Node curNode = pq.poll();

			if (intensity[curNode.to] < curNode.weight) {
				continue;
			}

			for (int i = 0; i < graph.get(curNode.to).size(); i++) {
				Node adjNode = graph.get(curNode.to).get(i);

				if (intensity[adjNode.to] > Math.max(intensity[curNode.to], adjNode.weight)) {
					intensity[adjNode.to] = Math.max(intensity[curNode.to], adjNode.weight);
					pq.add(adjNode);
				}
			}
		}
	}
}