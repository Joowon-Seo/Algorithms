import java.util.*;
class Solution {
    public static int[][] storage;
    public int solution(int n, int w, int num) {
        int answer = 0;
        // 상자의 최대 개수가 100이므로 O(N^4) 이내에 해결
        storage = new int[100][w];

        // 짝수행이면 오른쪽
        // 홀수행이면 왼쪽
        for(int i = 0; i < n; i ++) {
            int row = i/w;
            int col;
            if(row%2 == 0) {
                col = i%w;
            } else {
                col = (w - 1) - i%w;
            }
            storage[row][col] = i;
        }
        
//         for(int i = 0; i < storage.length; i ++) {
//          System.out.println(Arrays.toString(storage[i]));
    
//         }
        
    
        // 배열을 순회하면서 num을 찾으면 그 위에 몇 개의 상자가 존재하는지 count
        for(int i = 0; i < storage.length; i++) {
            for(int j = 0; j < w; j ++) {
                if(storage[i][j] + 1 == num) {
                    // System.out.println(storage[i][j]);
                    // number가 1일 때, 상자가 없는 부분도 해당 로직을 타게됨 따라서 answer가 오답으로 바뀜
                    answer = dfs(i, j, 1);
                    return answer;
                }
            }
        }
    
    
        return answer;
    }
    
    public static int dfs(int row, int col, int cnt) {
        if(row + 1 >= 100 || storage[row + 1][col] == 0) {
            // System.out.println(storage[row+1][col]);
            return cnt;
        }
        return dfs(row + 1, col, cnt + 1);
    }
}