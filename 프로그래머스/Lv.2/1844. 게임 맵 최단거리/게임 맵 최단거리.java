import java.util.*;
class Solution {
    public static int[] dx = {-1, 1, 0 , 0};
	public static int[] dy = {0, 0, -1, 1};
	public static boolean[][] visited;
	public static int[][] map;
    public int solution(int[][] maps) {
        map = maps;
		visited = new boolean[maps.length][map[0].length];
		return bfs(0, 0);
    }
    public static int bfs(int x, int y) {
		Queue<int[]> queue = new LinkedList<>();
		visited[x][y] = true;
		queue.add(new int[] {x, y, 1});

		while (!queue.isEmpty()) {
			int[] cur = queue.poll();

			if (cur[0] == map.length - 1 && cur[1] == map[0].length - 1) {
				return cur[2];
			}

			for (int i = 0; i < 4; i++) {
				int nX = cur[0] + dx[i];
				int nY = cur[1] + dy[i];


				if (nX < 0 || nY < 0 || nX >= map.length || nY >= map[0].length) {
					continue;
				}

				if (visited[nX][nY] || map[nX][nY] == 0) {
					continue;
				}

				queue.add(new int[] {nX, nY, cur[2] + 1});
                visited[nX][nY] = true;
			}
		}

		return -1;
	}
}