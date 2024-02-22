import java.util.*;
class Solution {
    // 자신의 수익이 발생하면, 10%는 추천인에게 준다. 나머지는 자신이 가짐 -> DFS
	// 추천인이 없다면 90%만 자신이 가짐
	// 이익이 10 미만이면 이익을 분배하지 않음

	public static Map<String, String> parent;
	public static Map<String, Integer> money;
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        int[] answer = new int[enroll.length];
		parent = new HashMap<>();
		money = new HashMap<>();
		for (int i = 0; i < enroll.length; i++) {
			parent.put(enroll[i], referral[i]);
			money.put(enroll[i], 0);
		}

		for (int i = 0; i < seller.length; i++) {
			dfs(seller[i], amount[i] * 100);
		}

		for (int i = 0; i < enroll.length; i++) {
			answer[i] = money.get(enroll[i]);
		}
		return answer;
    }
    
    public static void dfs(String seller, int profit) {
		if (profit < 10) {
			money.put(seller, money.get(seller) + profit);
			return;
		}

		int parentProfit = profit/10;
		int sellerProfit = profit - parentProfit;
		money.put(seller, money.get(seller) + sellerProfit);
		if (!Objects.equals(parent.get(seller), "-")) {
			dfs(parent.get(seller), parentProfit);
		}
	}
}