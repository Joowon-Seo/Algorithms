import java.util.*;
class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        List<Integer> list = new ArrayList<>();
		int idx = 0;
		while (idx < progresses.length) {
			int cnt = 0;
			while (progresses[idx] < 100) {
				for (int i = idx; i < progresses.length; i++) {
					progresses[i] += speeds[i];
				}
			}

			while (idx < progresses.length && progresses[idx] >= 100) {
				cnt++;
				idx++;
			}
			list.add(cnt);
		}

		return list.stream().mapToInt(i -> i).toArray();
    }
}