import java.util.*;
class Solution {
    public String solution(String number, int k) {
        Stack<Character> stack = new Stack<>();

		for (int i = 0; i < number.length(); i++) {
			char current = number.charAt(i);

			while (!stack.isEmpty() && k > 0 && stack.peek() < current) {
				stack.pop();
				k--;
			}

			stack.push(current);
		}

		// 남은 k개의 문자를 제거
		while (k > 0 && !stack.isEmpty()) {
			stack.pop();
			k--;
		}

		StringBuilder answer = new StringBuilder();
		while (!stack.isEmpty()) {
			answer.insert(0, stack.pop());
		}

		return answer.toString();
    }
}