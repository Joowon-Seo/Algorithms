import java.util.*;
    // 우선순위와 고유 번호를 기억하는 queue에
	// 우선순위큐로 현재 priorities 중에 가장 우선순위가 높은 값을 유지
class Solution {
    public static class Process {
		int priority;
		int location;

		public Process(int priority, int location) {
			this.priority = priority;
			this.location = location;
		}
	}
    
    public int solution(int[] priorities, int location) {
        int answer = 0;
		Queue<Process> queue = new LinkedList<>();
		PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
		for (int i = 0; i < priorities.length; i++) {
			queue.add(new Process(priorities[i], i));
			pq.add(priorities[i]);
		}

		while (!pq.isEmpty()) {
			Process cur = queue.poll();
			if (cur.priority >= pq.peek()) {
				answer++;
				pq.poll();
				if (cur.location == location) {
					break;
				}
			} else {
				queue.add(cur);
			}
		}
		return answer;
    }
}