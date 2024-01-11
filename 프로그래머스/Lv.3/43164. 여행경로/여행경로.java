import java.util.*;
class Solution {
    
    // 1. 모든 도시를 방문
	// 2. 모든 티켓을 사용
	// 3. 가능한 경로가 여려개 이면, 알파벳 순서가 앞서가는 경로를 선택

	// -> 완전 탐색, DFS

	public static Map<String, List<String>> graph;
	public static String[] answer;
    
    public String[] solution(String[][] tickets) {
        graph = new HashMap<>();
		String[] result = new String[tickets.length + 1];
		for (int i = 0; i < tickets.length; i++) {
			if (!graph.containsKey(tickets[i][0])) {
				graph.put(tickets[i][0], new ArrayList<>());
			}
			graph.get(tickets[i][0]).add(tickets[i][1]);
		}
		result[0] = "ICN";
		dfs("ICN", 1, result, graph);
		return answer;
    }
    
    public static void dfs(String from, int idx, String[] result, Map<String, List<String>> tickets) {
		if (!tickets.containsKey(from) || tickets.get(from).size() == 0) {
			if (idx == result.length) {
//				System.out.println(Arrays.toString(result));
				if (answer == null) {
					answer = result;
					return;
				}

				// 여러 경로가 생기는 경우 : 이전 위치가 동일하면서, 다음 위치가 다른 경우
				for (int i = 1; i < result.length; i++) {
					if (Objects.equals(answer[i - 1], result[i - 1]) && answer[i].compareTo(result[i]) > 0) {
						answer = result;
						break;
					} else if (Objects.equals(answer[i - 1], result[i - 1]) && answer[i].compareTo(result[i]) < 0) {
						break;
					}
				}
			}
			return;
		}

		for (int i = 0; i < tickets.get(from).size(); i++) {
			Map<String, List<String>> copyTickets = new HashMap<>();
			for(Map.Entry<String, List<String>> entry : tickets.entrySet()) {
				copyTickets.put(entry.getKey(), new ArrayList<>(entry.getValue()));
//				System.out.println("from : " + from + " " + copyTickets.get(from));
			}
			String to = copyTickets.get(from).remove(i);
			String[] copyResult = result.clone();
			copyResult[idx] = to;
			dfs(to, idx + 1, copyResult, copyTickets);
		}
	}
}