import java.util.*;
class Solution {
    public String[] solution(String[] record) {
        String[] answer;
		Map<String, String> nicknames = new HashMap<>();
		List<String[]> sq = new ArrayList<>();

		for (int i = 0; i < record.length; i++) {
			String[] cur = record[i].split(" ");
			String action = cur[0];
			String id = cur[1];

			if (Objects.equals(action, "Enter")) {
				String nickName = cur[2];
				sq.add(new String[] {action, id});
				nicknames.put(id, nickName);
			} else if (Objects.equals(action, "Leave")) {
				sq.add(new String[] {action, id});
			} else {
				String nickName = cur[2];
				nicknames.put(id, nickName);
			}

		}

		answer = new String[sq.size()];

		for (int i = 0; i < sq.size(); i++) {
			String[] cur = sq.get(i);
			String action = cur[0];
			String id = cur[1];
			String message;
			if (Objects.equals(action, "Enter")) {
				message = "님이 들어왔습니다.";
			} else {
				message = "님이 나갔습니다.";
			}
			answer[i] = nicknames.get(id) + message;
		}

		return answer;
    }
}