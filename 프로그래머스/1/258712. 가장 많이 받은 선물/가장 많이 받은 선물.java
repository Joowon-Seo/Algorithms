import java.util.*;
class Solution {
    
    public static Map<String, Integer> idx;
	public static int[][] data;
    
    public int solution(String[] friends, String[] gifts) {
        int answer = 0;
		idx = new HashMap<>();

		for (int i = 0; i < friends.length; i++) {
			idx.put(friends[i], i);
		}
		data = new int[friends.length + 1][friends.length + 1];

		for (int i = 0; i < gifts.length; i++) {

			String[] cur = gifts[i].split(" ");
			int from = idx.get(cur[0]);
			int to = idx.get(cur[1]);
			data[from][to]++;
			data[from][friends.length]++;
			data[friends.length][to]++;
		}

//		for (int i = 0; i < data.length; i++) {
//			System.out.println(Arrays.toString(data[i]));
//		}

		for (int i = 0; i < data.length - 1; i++) {
			int cnt = 0;
			for (int j = 0; j < data.length - 1; j++) {
				if (i == j) continue;
				if (giveAndTake(i, j)) cnt++;
			}
			answer = Math.max(cnt, answer);
		}
		return answer;
    }
    
    public static boolean giveAndTake(int a, int b) {
		int aGive = data[a][b];
		int bGive = data[b][a];

		if (aGive == bGive) {
			int aPoint = data[a][data.length - 1] - data[data.length - 1][a];
			int bPoint = data[b][data.length - 1] - data[data.length - 1][b];
			return aPoint > bPoint;
		} else {
			return aGive > bGive;
		}
	}
}