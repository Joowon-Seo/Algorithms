import java.util.*;

// arr를 순회하면서, 가장 List에 넣은 요소와 현재 arr의 요소가 다르다면 list에 데이터를 넣어줌
public class Solution {
    public int[] solution(int []arr) {
        List<Integer> list = new LinkedList<>();

		list.add(arr[0]);
		for (int i = 1; i < arr.length; i++) {
			if (list.get(list.size() - 1) != arr[i]) {
				list.add(arr[i]);
			}
		}

		return list.stream().mapToInt(Integer::intValue).toArray();
    }
}