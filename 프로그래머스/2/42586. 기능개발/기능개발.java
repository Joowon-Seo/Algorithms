import java.util.*;
class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        List<Integer> list = new ArrayList<>();
		Queue<Integer> queue = new LinkedList<>();

		// 1. 각 작업이 완료되는 "필요한 일수" 계산하여 큐에 추가
		for (int i = 0; i < progresses.length; i++) {
			int days = (int) Math.ceil((100.0 - progresses[i]) / speeds[i]);
			queue.offer(days);
		}

		// 2. 배포 진행 (큐 활용하여 O(N)으로 최적화)
		while (!queue.isEmpty()) {
			int days = queue.poll(); // 현재 가장 앞에 있는 작업 완료 일수
			int count = 1;

			while (!queue.isEmpty() && queue.peek() <= days) {
				count++;
				queue.poll();
			}
			list.add(count); // 배포된 기능 개수 저장
		}

		return list.stream().mapToInt(i -> i).toArray();
    }
}