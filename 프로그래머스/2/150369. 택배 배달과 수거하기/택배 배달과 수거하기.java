import java.util.*;

    // 나갈 때는 택배를 준다
	// 들어올 때는 수거를 한다.
	// 동선은 둘 중 가장 멀리 있는 거리가 된다.

	// 모든 배열을 계속 순회하기 보다는, 각 위치 값을 PriorityQueue에 넣어서 순회 횟수를 감소 시킨다.
	// 배달/수거할 때 여러 곳을 방문할 수 있음
class Solution {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long answer = 0;

		PriorityQueue<int[]> deliveryPq = new PriorityQueue<>((x, y) -> y[0] - x[0]);
		PriorityQueue<int[]> pickupPq = new PriorityQueue<>((x, y) -> y[0] - x[0]);

		for (int i = 0; i < n; i++) {
			if (deliveries[i] != 0) {
				deliveryPq.add(new int[] {i + 1, deliveries[i]});
			}

			if (pickups[i] != 0) {
				pickupPq.add(new int[] {i + 1, pickups[i]});
			}
		}

		while (!deliveryPq.isEmpty() && !pickupPq.isEmpty()) {

			int distance = Math.max(deliveryPq.peek()[0], pickupPq.peek()[0]);
			answer += distance;

			int dCnt = 0;
			while (!deliveryPq.isEmpty() && dCnt < cap) {
				int[] d = deliveryPq.poll();
				if (dCnt + d[1] <= cap) {
					dCnt += d[1];
				} else {
					deliveryPq.add(new int[] {d[0], d[1] - (cap - dCnt)});
					dCnt = cap;
				}
			}

			int pCnt = 0;
			while (!pickupPq.isEmpty() && pCnt < cap) {
				int[] p = pickupPq.poll();
				if (pCnt + p[1] <= cap) {
					pCnt += p[1];
				} else {
					pickupPq.add(new int[] {p[0], p[1] - (cap - pCnt)});
					pCnt = cap;
				}
			}
		}

		while (!deliveryPq.isEmpty()) {
			int[] d = deliveryPq.poll();
			answer += d[0];
			if (d[1] > cap) {
				deliveryPq.add(new int[] {d[0], d[1] - cap});
				continue;
			}

			int cnt = d[1];
			while (!deliveryPq.isEmpty() && cnt < cap) {
				int[] temp = deliveryPq.poll();
				if (cnt + temp[1] <= cap) {
					cnt += temp[1];
				} else {
					deliveryPq.add(new int[] {temp[0], cap - cnt});
					cnt = cap;
				}
			}
		}

		while (!pickupPq.isEmpty()) {
			int[] p = pickupPq.poll();
			answer += p[0];

			if (p[1] > cap) {
				pickupPq.add(new int[] {p[0], p[1] - cap});
				continue;
			}

			int cnt = p[1];
			while (!pickupPq.isEmpty() && cnt < cap) {
				int[] temp = pickupPq.poll();
				if (cnt + temp[1] <= cap) {
					cnt += temp[1];
				} else {
					pickupPq.add(new int[] {temp[0], cap - cnt});
					cnt = cap;
				}
			}
		}

		return answer * 2;
    }
}