import java.util.*;
class Solution {
    // 1. 두 큐의 원소의 합이 같지 않다면
	// 2. 두 큐 중 원소의 합이 더 큰 큐에서 원소의 합이 작은 큐에 insert를 해준다.
	// 3. 불가능한 케이스
	// 3-1. 총합의 절반이 홀수인 경우
	// 3-2. 가장 큰 원소가 총합의 절반보다 큰 경우
	// 3-3. 원점으로 돌아오는 경우
	// queue1, queue2의 순서가 보장된다.
	// 만약 queue1의 원소들이 queue2로 모두 이동했다면 queue2의 원소들은 queue1에 들어있음 -> 원점으로 돌아옴 -> queue의 길이의 3배
	// queue1이 queue1으로 돌아오는 경우 queue2가 queue2로 돌아오는 경우 -> 원점으로 돌아옴 -> queue의 길이의 4배

	public static Set<Long> sum1Data = new HashSet<>();
//	public static Set<Long> sum2Data = new HashSet<>();
	public static Deque<Integer> queueOne = new LinkedList<>();
	public static Deque<Integer> queueTwo = new LinkedList<>();
	public static long sum1 = 0;
	public static long sum2 = 0;
    public int solution(int[] queue1, int[] queue2) {
        int answer = 0;
        int max = 0;

		for (int i = 0; i < queue1.length; i++) {
			queueOne.add(queue1[i]);
			queueTwo.add(queue2[i]);
			sum1 += queue1[i];
			sum2 += queue2[i];
            max = Math.max(max, Math.max(queue1[i], queue2[i]));
		}

        if (max > (sum1 + sum2) / 2) {
			return -1;
		} else if ((sum1 + sum2) % 2 != 0) {
			return -1;
		}
        
		sum1Data.add(sum1);
//		sum2Data.add(sum2);

		while (sum1 != sum2) {
			if (sum1 > sum2) {
				int popNum = queueOne.poll();
				sum1 -= popNum;
				sum2 += popNum;
				queueTwo.add(popNum);
			} else {
				int popNum = queueTwo.poll();
				sum2 -= popNum;
				sum1 += popNum;
				queueOne.add(popNum);
			}

			if (sum1Data.contains(sum2) && answer >= queue1.length * 2) {
				return -1;
			} else if (answer >= queue1.length * 4) {
                return -1;
            }
        

			answer++;
		}
		return answer;
    }
}