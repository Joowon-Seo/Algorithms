import java.util.*;

// 길이가 짝수
// 시작은 (, 끝은 )
// )가 나온다면 이전에 (가 무조건 존재해야 함
// 스택에 ( 가 나오면 넣고, )가 나오면 뺌
// 최종적으로 스택이 비어 있다면 ok
class Solution {
    boolean solution(String s) {
        int len = s.length();
		if (len % 2 != 0) {
			return false;
		} else if (s.charAt(0) != '(') {
			return false;
		} else if (s.charAt(len - 1) != ')') {
			return false;
		} else {
			Stack<Character> stack = new Stack<>();
			for (int i = 0; i < len; i++) {
				if (s.charAt(i) == '(') {
					stack.add('(');
				} else if (stack.isEmpty()){
					return false;
				} else {
					stack.pop();
				}
			}

			return stack.isEmpty();
		}
    }
}