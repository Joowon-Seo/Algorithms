import java.util.*;
	// 종료시간이 빠른 작업 중 가장 먼저 시작하는 작업을 우선순위로 둠
class Solution {
    public static class Job implements Comparable<Job> {
		int start;
		int end;

		public Job(int start, int end) {
			this.start = start;
			this.end = end;
		}

		@Override
		public int compareTo(Job o) {
			if (this.end == o.end) {
				return Integer.compare(this.start, o.start);
			}
			return Integer.compare(this.end, o.end);
		}
	}
    
    public int solution(int[][] jobs) {
        int answer = 0;
		int time = 0;
		PriorityQueue<Job> pq1 = new PriorityQueue<>();
		PriorityQueue<Job> pq2 = new PriorityQueue<>(new Comparator<Job>() {
			@Override
			public int compare(Job o1, Job o2) {
				if (o1.start == o2.start) {
					return Integer.compare(o1.end, o2.end);
				} else {
					return Integer.compare(o1.start, o2.start);
				}
			}
		});
		for (int i = 0; i < jobs.length; i++) {
			pq2.add(new Job(jobs[i][0], jobs[i][1]));
		}
        
        while (!pq2.isEmpty() && time > pq2.peek().start) {
			pq1.add(pq2.poll());
		}

		while (!pq1.isEmpty() || !pq2.isEmpty()) {
			if (pq1.isEmpty()) {
				pq1.add(pq2.poll());
			}
			Job cur = pq1.poll();
			if (cur.start > time) {
				time = cur.start;
			}
			time += cur.end;
			answer += time - cur.start;

//			System.out.println("start : " + cur.start);
			while (!pq2.isEmpty() && time > pq2.peek().start) {
				pq1.add(pq2.poll());
			}
		}
		return answer/ jobs.length;
    }
}