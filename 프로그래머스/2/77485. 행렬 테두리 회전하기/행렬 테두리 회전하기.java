import java.util.*;


class Solution {
    public static int[][] map;
    public int[] solution(int rows, int columns, int[][] queries) {
        int[] answer = new int[queries.length];
		map = new int[rows][columns];
		int num = 1;
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				map[i][j] = num++;
			}
		}

//		for (int i = 0; i < rows; i++) {
//			System.out.println(Arrays.toString(map[i]));
//		}
		for (int i = 0; i < queries.length; i++) {
			answer[i] = RR(queries[i][0] - 1, queries[i][1] - 1, queries[i][2] - 1,
					queries[i][3] - 1);
//			for (int j = 0; j < rows; j++) {
//				System.out.println(Arrays.toString(map[j]));
//			}
		}
		return answer;
    }
    public static int RR(int x1, int y1, int x2, int y2) {
		int temp = map[x1 + 1][y1];
		int min = temp;
		for (int i = y1; i < y2; i++) {
			int cur = map[x1][i];
			map[x1][i] = temp;
			temp = cur;
			min = Math.min(min, cur);
		}

		for (int i = x1; i < x2; i++) {
			int cur = map[i][y2];
			map[i][y2] = temp;
			temp = cur;
			min = Math.min(min, cur);
		}

		for (int i = y2; i > y1; i--) {
			int cur = map[x2][i];
			map[x2][i] = temp;
			temp = cur;
			min = Math.min(min, cur);
		}

		for (int i = x2; i > x1; i--) {
			int cur = map[i][y1];
			map[i][y1] = temp;
			temp = cur;
			min = Math.min(min, cur);
		}

		return min;
	}
}