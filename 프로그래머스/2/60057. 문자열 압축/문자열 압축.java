import java.util.*;
class Solution {
    // 딱 나누어 떨어지는 단위로 잘라야 함
	// 특정 단위로 잘랐을 때 문자열의 길이가 가장 짧은 문자열의 길이를 반환
	// 압축이 되는 횟수 / 10 + 1= 숫자 문자열의 길이
	// 단위 = 압축한 문자열의 길이
    public int solution(String s) {
        int answer = s.length();
		for (int i = 1; i <= s.length() / 2; i++) {
			int len = i;
			int equalCnt = 1;
			String pre = s.substring(0, i);
			for (int j = 1; j < s.length() / i; j++) {
				String cur = s.substring(j * i, j * i + i);
				if (pre.equals(cur)) {
					equalCnt++;
				} else {
					// if (j == 1) {
					// 	len += i;
					// }
					len += i;
					if (equalCnt >= 2) {
						len += String.valueOf(equalCnt).length();
					}
					equalCnt = 1;
				}
				pre = cur;
			}
			// len += i;
			if (equalCnt >= 2) {
				len += String.valueOf(equalCnt).length();
			}
			len += s.length() % i;
			answer = Math.min(answer, len);
		}
		return answer;
    }
}