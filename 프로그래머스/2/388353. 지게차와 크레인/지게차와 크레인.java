import java.util.*;
class Solution {
    public static int[] dx = {-1, 1, 0, 0};
    public static int[] dy = {0, 0, -1, 1};
    public static char[][] containerMap;
    public static boolean[][] possible;
    public int solution(String[] storage, String[] requests) {
        // 외부 창고와 연결돼있다는 것 -> 컨테이너로 둘러싸여있지 않은 것
        // 크레인을 사용하면 조건을 따지지 않고 컨테이너를 꺼낼 수 있다. ("BB") 와 같은 요청
        // 요청이 들어오면 동시에 처리하기 때문에 꺼낼 수 있는 컨테이너의 위치를 따로 저장했다가 한 번에 처리해야함
        // 모든 요청을 처리하고 남은 컨테이너의 숫자를 return
        
        // 컨테이너를 2차원 배열에 넣는다.
        containerMap = new char[storage.length][storage[0].length()];
        possible = new boolean[storage.length][storage[0].length()];
        for(int i = 0; i < storage.length; i ++) {
            String containers = storage[i];
            for(int j = 0; j < storage[0].length(); j ++) {
                containerMap[i][j] = containers.charAt(j);
            }
        }
        // 요청을 A, AA를 구분하고 알맞게 요청을 처리한다.
        for(int i = 0; i < requests.length; i ++) {
            String request = requests[i];
            char alphabet = request.charAt(0);
            Deque<int[]> deletedContainer = new ArrayDeque();
            
            // A요청인 경우 -> 2차 배열을 순회하면서 특정 단어를 찾고, 상하좌우 공백 체크
            // -> 오류 발생
            // BFS로 해당 컨테이너 위치에서 모서리까지 도달 할 수 있는지를 파악해야함
            // 공백이 존재하면 삭제할 배열에 해당 행/열을 저장한다.
            // 모든 컨테이너를 확인하고 삭제할 배열에 있는 컨테이너를 '.'으로 바꾼다.
            if(request.length() == 1) {
                for(int j = 0; j < containerMap.length; j ++) {
                    for(int k = 0; k < containerMap[0].length; k++) {
                        if(containerMap[j][k] == alphabet && isDeleteContainer(j, k)) {
                             deletedContainer.add(new int[] {j, k});
                        }
                    }
                }
                while(!deletedContainer.isEmpty()) {
                    int[] container = deletedContainer.poll();
                    int x = container[0];
                    int y = container[1];
                    containerMap[x][y] = '.';
                }
            }
            
            
            // AA요청인 경우 -> 순회를하면서 해당 단어가 보이면 '.' 바로 공백을 넣는다.
            if(request.length() == 2) {
                for(int j = 0; j < containerMap.length; j ++) {
                    for(int k = 0; k < containerMap[0].length; k++) {
                        if(containerMap[j][k] == alphabet) {
                             containerMap[j][k] = '.';
                            deletedContainer.add(new int[] {j, k});
                        }
                    }
                }
            }
            
            
        }
        
        
        // 모든 요청을 처리하고, 모든 2차 배열을 순회하면서 '.'이 아니라면 count를 한다.
        int answer = 0;
        for(int i = 0; i < containerMap.length; i++) {
            // System.out.println(Arrays.toString(containerMap[i]));
            for(int j = 0; j< containerMap[0].length; j++) {
                if(containerMap[i][j] != '.') {
                    answer++;
                }
            }
        }
        
        return answer;
    }
    
    public static boolean isDeleteContainer(int x, int y) {
        boolean[][] visited = new boolean[containerMap.length][containerMap[0].length];
        Deque<int[]> queue = new ArrayDeque();
        queue.add(new int[] {x, y});
        visited[x][y] = true;
        
        while(!queue.isEmpty()) {
            int[] cur = queue.poll();
            
            for(int i = 0; i < 4; i ++) {
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];

                if(nx < 0 || ny < 0 || nx >= containerMap.length || ny >= containerMap[0].length) {
                    possible[x][y] = true;
                    return true;
                }
                
                if(visited[nx][ny]) {
                    continue;
                }
                
                if(containerMap[nx][ny] == '.') {
                    if(possible[nx][ny]) {
                        possible[x][y] = true;
                        return true;
                    }
                    visited[nx][ny] = true;
                    queue.add(new int[]{nx, ny});
                }
            }
        }
        
        
        return false;
    }
}