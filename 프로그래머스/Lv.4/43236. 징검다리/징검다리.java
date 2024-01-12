import java.util.*;
class Solution {
    public int solution(int distance, int[] rocks, int n) {
        int answer = 0;
		int left = 0;
		int right = distance;

		// 주어진 데이터가 정령 x
		Arrays.sort(rocks);

		while (left <= right) {
			// 각 바위 사이 거리의 최소값
			int mid = (left + right) / 2;

			int pre = 0;
			int cnt = 0;
			for (int i = 0; i < rocks.length; i++) {
				if (rocks[i] - pre < mid) {
					cnt++;
				} else {
					pre = rocks[i];
				}
			}
			if (distance - pre < mid) {
				cnt++;
			}

			// 만족한다는 것은 : 제거한 바위 수를 증가시켜야 함
			// 바위 간의 거리를 증가시키면, 제거할 바위의 개수가 증가함
			if (cnt <= n) {
				left = mid + 1;
				answer = mid;
			} else {
				right = mid - 1;
			}
		}

		return answer;
    }
}