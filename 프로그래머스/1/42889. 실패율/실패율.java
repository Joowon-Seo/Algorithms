import java.util.*;
class Solution {
    
    public static class Stage implements Comparable<Stage>{
		int num;
		int reachCnt;
		int failCnt;
		Double failPercent;

		public Stage(int num, int reachCnt, int failCnt, Double failPercent) {
			this.num = num;
			this.reachCnt = reachCnt;
			this.failCnt = failCnt;
			this.failPercent = failPercent;
		}

		@Override
		public int compareTo(Stage o) {
			if (Objects.equals(this.failPercent, o.failPercent)) {
				return Integer.compare(this.num, o.num);
			} else {
				return o.failPercent.compareTo(this.failPercent);
			}
		}
	}
    
    public int[] solution(int N, int[] stages) {
        int[] answer = new int[N];
		List<Stage> stageList = new ArrayList<>();

		for (int i = 1; i <= N; i++) {
			stageList.add(new Stage(i, 0, 0, 0.00));
		}

		for (int i = 0; i < stages.length; i++) {

			int curReachStageNum = stages[i];

			if (curReachStageNum == N + 1) {
				for (int j = 0; j < stageList.size(); j++) {
					stageList.get(j).reachCnt++;
				}
			} else {
				for (int j = 0; j < curReachStageNum; j++) {
					stageList.get(j).reachCnt++;
				}
				stageList.get(curReachStageNum - 1).failCnt++;
			}
		}

		for (int i = 0; i < stageList.size(); i++) {
			Stage stage = stageList.get(i);
			stage.failPercent = (stage.reachCnt == 0) ? 0 : (double) stage.failCnt/stage.reachCnt;
		}

		Collections.sort(stageList);
		for (int i = 0; i < N; i++) {
			answer[i] = stageList.get(i).num;
		}
		return answer;
    }
}