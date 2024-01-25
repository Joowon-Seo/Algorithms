import java.util.*;
class Solution {
    
    // 코인을 사용하는 우선순위

	// 중복없이 1~n에서 n이 6의 배수 임으로 n + 1을 만들 수 있는 조합은 무조건 1개

	// 1. 코인을 1개 사용해서 n + 1을 만들 수 있는가?
	// 2. 코인을 2개 사용해서 n + 1을 만들 수 있는가?
	// 2-1. 뽑은 두 카드를 사용해야 할 때 :

	// 1. 현재 손에 든 것과 합쳐서 n + 1을 만들 수 있는가
	// 2. 최대로 갈 수 있는 라운드에서 하나를 뽑으면 만들 수 있는가?
	// 2-1. 그 때 코인이 있는지 어떻게 아는가?
	// 2-2. 결국 그 조합을 사용한다는 것은 코인을 2개 사용하는 것 따라서 코인을 사용하여 미리 뽑는다. 코인 개수를 관리 할 수 있음
        
    public int solution(int coin, int[] cards) {
        int answer = 1;
		int n = cards.length;
		int life = 0;
		Map<Integer, Integer> hand = new HashMap<>();
		Queue<Integer> queue = new LinkedList<>();
		Map<Integer, Integer> data = new HashMap<>();
		PriorityQueue<Integer> coin1 = new PriorityQueue<>();
		PriorityQueue<Integer> coin2 = new PriorityQueue<>();

		for (int i = 0; i < cards.length; i++) {
			if (i < cards.length/3) {
				if (hand.containsKey(n + 1 - cards[i])) {
					hand.remove(n + 1 - cards[i]);
					life++;
				} else {
					hand.put(cards[i], 0);
				}
			} else {
				data.put(cards[i], (i - cards.length/3)/2 + 1);
				queue.add(cards[i]);
			}

		}

		for (int card : hand.keySet()) {
			hand.put(card, data.get(n + 1 - card));
		}

		while (!queue.isEmpty()) {
			int card1 = queue.poll();
			int card2 = queue.poll();

			System.out.println("round = " + answer + " coin = " + coin + " card1 = " + card1 + " card2 = " + card2 + " life = " + life);

			if (hand.containsKey(n + 1 - card1)) {
				coin1.add(answer);
				hand.remove(n + 1 - card1);
			}

			if (hand.containsKey(n + 1 - card2)) {
				coin1.add(answer);
				hand.remove(n + 1 - card2);
			}

			if (data.containsKey(n + 1 - card1)) {
				coin2.add(data.get(n + 1 - card1));
				data.remove(card1);
			}

			if (data.containsKey(n + 1 - card2)) {
				coin2.add(data.get(n + 1 - card2));
				data.remove(card2);
			}

			if (card1 + card2 == n + 1) {
				coin2.add(answer);
			}

			if (life == 0) {
				if (coin == 0) {
					break;
				}
				if (!coin1.isEmpty()) {
					System.out.println("coin 1개 사용");
					coin1.poll();
					coin--;
					answer++;
					continue;
				}

				if (coin >= 2 && !coin2.isEmpty() && coin2.peek() <= answer) {
					System.out.println("coin 2개 사용");
					coin2.poll();
					coin-=2;
					answer++;
					continue;
				}

				break;
			} else {
				answer++;
				life--;
			}

		}
		return answer;
    }
}