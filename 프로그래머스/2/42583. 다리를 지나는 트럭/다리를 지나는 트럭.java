import java.util.*;
class Solution {
    public static class Truck {
		int weight;
		int nextTime;

		public Truck(int weight, int nextTime) {
			this.weight = weight;
			this.nextTime = nextTime;
		}
	}


	public static Queue<Truck> bridge = new LinkedList<>();
	public static Queue<Integer> trucks = new LinkedList<>();
	public static int time = 1, bridge_weight;
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        for (int i = 0; i < truck_weights.length; i++) {
			trucks.add(truck_weights[i]);
		}

		while (!trucks.isEmpty()) {
			if (bridge.size() >= bridge_length || weight < bridge_weight + trucks.peek()) {
				out();
			} else {
				in(trucks.poll(), bridge_length);
			}
		}

		while (!bridge.isEmpty()) {
			out();
		}
		return time;
    }
    public static void in(int truck_weight, int bridge_length) {
		bridge.add(new Truck(truck_weight, time + bridge_length));
		bridge_weight += truck_weight;
		time++;
	}

	public static void out() {
		Truck truck = bridge.poll();
		time = Math.max(truck.nextTime, time);
		bridge_weight -= truck.weight;
	}
}