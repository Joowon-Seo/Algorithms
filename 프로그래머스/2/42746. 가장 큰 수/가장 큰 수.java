import java.util.*;
class Solution {
    public String solution(int[] numbers) {
        StringBuilder sb = new StringBuilder();
		String[] str = new String[numbers.length];
		for (int i = 0; i < numbers.length; i++) {
			str[i] = String.valueOf(numbers[i]);
		}

		Arrays.sort(str, (o1, o2) -> (o2 + o1).compareTo(o1 + o2));
		for (String s : str) {
			sb.append(s);
		}
        
        if (sb.toString().charAt(0) == '0') {
			return "0";
		}

		return sb.toString();
    }
}