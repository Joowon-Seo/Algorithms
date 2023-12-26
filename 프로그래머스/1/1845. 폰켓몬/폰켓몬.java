import java.util.*;

// 포켓몬의 종류를 Set으로 파악
// 뽑을 수 있는 포켓몬의 수와 포켓몬의 종류를 비교
// 1. 포켓몬 종류가 뽑아야 하는 포켓몬 보다 많거나 같다면 return 뽑아야 하는 포켓몬의 수
// 2. 포켓몬 종류가 뽑아야 하는 포켓몬 보다 작다면 return 포켓몬의 종류
class Solution {
    public int solution(int[] nums) {
        Set<Integer> set = new HashSet<>();
		for (int i = 0; i < nums.length; i++) {
			set.add(nums[i]);
		}

		int temp = nums.length / 2;

		if (set.size() >= temp) {
			return temp;
		} else {
			return set.size();
		}
    }
}