import java.util.*;
class Solution {
    public int solution(String[] lines) {
        int answer = 0;
		int[] start = new int[lines.length];
		int[] end = new int[lines.length];

		for (int i = 0; i < lines.length; i++) {
			String[] traffic = lines[i].split(" ");
			String[] endTimeData = traffic[1].split(":");
			double endTime = Double.parseDouble(endTimeData[0]) * 3600 + Double.parseDouble(endTimeData[1])* 60 + Double.parseDouble(endTimeData[2]);
			double startTime = endTime - Double.parseDouble(traffic[2].substring(0, traffic[2].length() - 1)) + 0.001;

			start[i] = (int) (startTime * 1000);
			end[i] = (int) (endTime * 1000) + 1000;
		}

		for (int i = 0; i < lines.length; i++) {
			int cnt = 0;
			for (int j = i; j < lines.length; j++) {
				if (end[i] > start[j]) {
					cnt++;
				}
			}
			answer = Math.max(answer, cnt);

		}
		return answer;
    }
}