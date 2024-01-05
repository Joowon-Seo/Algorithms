import java.util.*;
class Solution {
    public static int cnt;

    public int solution(int[] numbers, int target) {
        dfs(numbers, 0, target, 0);
		return cnt;
    }
    
    public static void dfs(int[] number, int idx, int target, int result) {
		if (idx == number.length) {
			if (target == result) {
				cnt++;
			}
			return;
		}

		dfs(number, idx + 1, target, result + number[idx]);
		dfs(number, idx + 1, target, result - number[idx]);
	}
}