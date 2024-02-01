import java.util.*;
class Solution {
    // O(5^7 x 100)

	// 이모티콘을 정가부터, 10, 20, 30, 40 할인율을 적용한 모든 경우의 수를 구해줌
	// 이모티콘의 개수 만큼 순회하면서 할인율을 선택함
	// 특정 케이스에서 유저의 기준에 부합하는지 확인하고, 부합하면 서비스가입 그렇지 않으면 매출 증가
    public static int resultSub = 0;
	public static int resultPay = 0;
    public int[] solution(int[][] users, int[] emoticons) {
        int[][] moneys = new int[emoticons.length][5];
		int[] discounts = new int[emoticons.length];
		for (int i = 0; i < emoticons.length; i++) {
			moneys[i][0] = emoticons[i];
			moneys[i][1] = emoticons[i] / 10 * 9;
			moneys[i][2] = emoticons[i] / 10 * 8;
			moneys[i][3] = emoticons[i] / 10 * 7;
			moneys[i][4] = emoticons[i] / 10 * 6;
		}

		getTotal(moneys, 0, emoticons.length, discounts, users);
		return new int[] {resultSub, resultPay};
    }
    
    public static void getTotal(int[][] moneys, int i, int n, int[] discounts, int[][] users) {
		if (i == n) {
			int sub = 0;
			int pay = 0;
			for (int j = 0; j < users.length; j++) {
				int sum = 0;
				for (int k = 0; k < discounts.length; k++) {
					if (users[j][0] % 10 == 0) {
						if (discounts[k] >= users[j][0]/10) {
							sum += moneys[k][discounts[k]];
						}
					} else {
						if (discounts[k] > users[j][0]/10) {
							sum += moneys[k][discounts[k]];
						}
					}
				}

				if (sum >= users[j][1]) {
					sub++;
				} else {
					pay += sum;
				}
			}

			if (sub > resultSub) {
				resultSub = sub;
				resultPay = pay;
			} else if (sub == resultSub) {
				resultPay = Math.max(resultPay, pay);
			}
			return;
		}

		for (int j = 0; j < 5; j++) {
			discounts[i] = j;
			getTotal(moneys, i + 1, n, discounts, users);
		}
	}
}