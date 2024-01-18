import java.util.*;

class Solution {
    
    public static class Node {
		int to;
		int weight;

		public Node(int to, int weight) {
			this.to = to;
			this.weight = weight;
		}
	}
    
    public static List<List<Node>> graph;
	public static int[] dist;
	public static Map<Integer, Integer> result;
    
    public int solution(int n, int[][] edge) {
        int answer = 0;
		graph = new LinkedList<>();
		dist = new int[n + 1];
		result = new HashMap<>();

		for (int i = 0; i <= n; i++) {
			graph.add(new LinkedList<>());
		}

		for (int i = 0; i < edge.length; i++) {
			int to = edge[i][0];
			int from = edge[i][1];

			graph.get(to).add(new Node(from, 1));
			graph.get(from).add(new Node(to, 1));
		}

		for (int i = 1; i <= n; i++) {
			dist[i] = Integer.MAX_VALUE;
		}

		dijkstra(1);

		for (int i = 1; i <= n; i++) {
//			System.out.println(dist[i]);
			answer = Math.max(dist[i], answer);
			result.put(dist[i], result.getOrDefault(dist[i], 0) + 1);
		}
		return result.get(answer);
    }
    
    public static void dijkstra(int start) {
		dist[start] = 0;
		PriorityQueue<Node> pq = new PriorityQueue<>((x, y) -> x.weight - y.weight);
		pq.add(new Node(start, 0));

		while (!pq.isEmpty()) {
			Node curNode = pq.poll();

			if (dist[curNode.to] < curNode.weight) {
				continue;
			}

			for (int i = 0; i < graph.get(curNode.to).size(); i++) {
				Node adjNode = graph.get(curNode.to).get(i);
				if (dist[adjNode.to] > curNode.weight + adjNode.weight) {
					dist[adjNode.to] = curNode.weight + adjNode.weight;
					pq.add(new Node(adjNode.to, dist[adjNode.to]));
				}
			}
		}
	}
}