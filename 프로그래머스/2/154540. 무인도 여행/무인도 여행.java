import java.util.*;
class Solution {
    
    public static int[] dx = {-1, 1, 0, 0};
    public static int[] dy = {0, 0, -1, 1};
    
    public static int[][] map;
    public static boolean[][] visited;
    
    public static LinkedList<Integer> result = new LinkedList();
    
    public int[] solution(String[] maps) {
    
        map = new int[maps.length][maps[0].length()];
        visited = new boolean[maps.length][maps[0].length()];
        
        for (int i = 0; i < maps.length; i++) {
            String str = maps[i];
            for (int j = 0; j < maps[0].length(); j++) {
                if (str.charAt(j) == 'X') {
                    map[i][j] = 0;
                } else {
                    map[i][j] = str.charAt(j) - '0';
                }
            }
        }
        
        for (int i = 0; i < map.length; i ++) {
            for (int j = 0; j < map[0].length; j++) {
                if (map[i][j] != 0 && !visited[i][j]) {
                    result.add(bfs(i, j));
                }
            }
        }
        Collections.sort(result);
        int[] answer = new int[result.size()];
        
        for (int i = 0; i < result.size(); i++) {
            answer[i] = result.get(i);
        }
        
        for (int i = 0; i < map.length; i++) {
            System.out.println(Arrays.toString(map[i]));
        }
        
        if(answer.length == 0) {
            return new int[] {-1};
        }
        return answer;
    }
    
    public static int bfs(int x, int y) {
        int sum = map[x][y];
        Queue<int[]> queue = new LinkedList();
        queue.add(new int[] {x, y});
        visited[x][y] = true;
        
        while(!queue.isEmpty()) {
            
            int[] cur = queue.poll();
            int curX = cur[0];
            int curY = cur[1];
            
            for (int i = 0; i < 4; i++) {
                int nextX = curX + dx[i];
                int nextY = curY + dy[i];
                
                if(nextX < 0 || nextY < 0 || nextX >= map.length || nextY >= map[0].length) {
                    continue;
                }
                
                if(map[nextX][nextY] == 0 || visited[nextX][nextY]) {
                    continue;
                }
                
                queue.add(new int[] {nextX, nextY});
                visited[nextX][nextY] = true;
                sum += map[nextX][nextY];
            }
            
        }
        
        if (sum == 0) {
        return -1;
        } else {
            return sum;
        }
        
    }
    
    
}