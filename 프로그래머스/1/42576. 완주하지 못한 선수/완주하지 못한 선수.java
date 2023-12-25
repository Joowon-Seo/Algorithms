import java.util.*;
class Solution {
    public String solution(String[] participant, String[] completion) {
        String answer = "";
		Arrays.sort(participant);
		Arrays.sort(completion);

		answer = participant[participant.length - 1];
		for (int i = 0; i < participant.length - 1; i++) {
			if (!Objects.equals(participant[i], completion[i])) {
				answer = participant[i];
				break;
			}
		}
		return answer;
    }
}