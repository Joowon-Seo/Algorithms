import java.util.*;
class Solution {
    public int[] solution(String[] operations) {
        int[] answer = new int[2];
		List<Integer> list = new LinkedList<>();
		boolean isSort = false;

		for (int i = 0; i < operations.length; i++) {
			String[] cur = operations[i].split(" ");
			String operation1 = cur[0];
			String operation2 = cur[1];

			if (Objects.equals(operation1, "I")) {
				list.add(Integer.parseInt(operation2));
				isSort = false;
			} else if (Objects.equals(operation1, "D") && Objects.equals(operation2, "1") && list.size() != 0) {
				if (isSort) {
					list.remove(list.size() - 1);
				} else {
					Collections.sort(list);
					isSort = true;
					list.remove(list.size() - 1);
				}
			} else if (Objects.equals(operation1, "D") && Objects.equals(operation2, "-1") && list.size() != 0) {
				if (isSort) {
					list.remove(0);
				} else {
					Collections.sort(list);
					isSort = true;
					list.remove(0);
				}
			}
		}

		if (list.size() != 0) {
            Collections.sort(list);
			answer[0] = list.get(list.size() - 1);
			answer[1] = list.get(0);
		}
		return answer;
    }
}