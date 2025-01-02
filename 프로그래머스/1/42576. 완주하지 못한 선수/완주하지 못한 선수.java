import java.util.*;
class Solution {
    public String solution(String[] participant, String[] completion) {
        Map<String, Integer> map = new HashMap<String, Integer>();

		for (String s : participant) {
			map.merge(s, 1, Integer::sum);
		}

		for (String s : completion) {
			map.put(s, map.get(s) - 1);
			if (map.get(s) == 0) {
				map.remove(s);
			}
		}

		Map.Entry<String, Integer> entry = map.entrySet().iterator().next();
		return entry.getKey();
    }
}