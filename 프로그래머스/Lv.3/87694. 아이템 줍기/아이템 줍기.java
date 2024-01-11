import java.util.*;
class Solution {
    
    public static boolean[][] map;
	public static boolean[][] visited;
	public static int[] dx = {-1, 1, 0, 0, -1, -1, 1, 1};
	public static int[] dy = {0, 0, -1, 1, -1, 1, -1, 1};
    
    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        map = new boolean[102][102];
		visited = new boolean[102][102];
		for (int i = 0; i < rectangle.length; i++) {
			draw(rectangle[i][0], rectangle[i][1], rectangle[i][2], rectangle[i][3]);
		}

//		for (int i = 0; i < map.length; i++) {
//			System.out.println(Arrays.toString(map[i]));
//		}
		return bfs(characterX * 2, characterY * 2, itemX * 2, itemY * 2);
    }
    
    public static void draw(int leftX, int leftY, int rightX, int rightY) {
		for (int i = leftX * 2; i <= rightX * 2; i++) {
			for (int j = leftY * 2; j <= rightY * 2; j++) {
				map[i][j] = true;
			}
		}
	}
    
    public static int bfs(int x, int y, int itemX, int itemY) {
		Queue<int[]> queue = new LinkedList<>();
		visited[x][y] = true;
		queue.add(new int[] {x, y, 0});

		while (!queue.isEmpty()) {
			int[] cur = queue.poll();

//			System.out.println("curX : " + cur[0] + " curY : " + cur[1]);
			if (cur[0] == itemX && cur[1] == itemY) {
				return cur[2]/2;
			}


			for (int i = 0; i < 4; i++) {
				int nx = cur[0] + dx[i];
				int ny = cur[1] + dy[i];

				if (nx < 0 || ny < 0 || nx >= 101 || ny >= 101) {
					continue;
				}

				if (visited[nx][ny] || !map[nx][ny]) {
					continue;
				}

				if (!roundCheck(nx, ny)) {
					continue;
				}

				queue.add(new int[] {nx, ny, cur[2] + 1});
				visited[nx][ny] = true;
			}
		}

		return -1;
	}
    
    public static boolean roundCheck(int x, int y) {
		int cnt = 0;
		for (int i = 0; i < 8; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];

			if (map[nx][ny]) {
				cnt++;
			}
		}

		return cnt < 8;
	}
}