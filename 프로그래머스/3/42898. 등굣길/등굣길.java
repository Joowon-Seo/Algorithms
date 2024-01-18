import java.util.*;
class Solution {
    public int solution(int m, int n, int[][] puddles) {
        int[][] map = new int[n + 1][m + 1];
		boolean[][] data = new boolean[n + 1][m + 1];
		int[] factorial = new int[m + n - 1];

		if (puddles.length == 0) {
			factorial[0] = 1;
			factorial[1] = 1;
			for (int i = 2; i <= m + n - 2; i++) {
				factorial[i] = factorial[i - 1] * i % 1000000007;
			}

			return factorial[m + n - 2] / (factorial[m - 1] * factorial[n - 1]) % 1000000007;
		}

		for (int i = 0; i < puddles.length; i++) {
				data[puddles[i][1]][puddles[i][0]] = true;
			}

		map[1][1] = 1;

		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= m; j++) {
				if (data[i][j]) {
					continue;
				}

				if (i == 1 && j != m && !data[1][j + 1]) {
					map[i][j + 1] = map[i][j];
				}

				if (j == 1 && i != n && !data[i + 1][1]) {
					map[i + 1][1] = map[i][j];
				}

				if (i != 1 && j != m && !data[i][j + 1]) {
					map[i][j + 1] = (map[i - 1][j + 1] + map[i][j]) % 1000000007;
				}

				if (i != n && j != 1 && !data[i + 1][j]) {
					map[i + 1][j] = (map[i + 1][j - 1] + map[i][j]) % 1000000007;
				}
			}
		}

//		for (int i = 0; i < map.length; i++) {
//			System.out.println(Arrays.toString(map[i]));
//		}

		return map[n][m];
    }
}