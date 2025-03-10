import java.util.*;
class Solution {
    public int solution(int[][] info, int n, int m) {
        // 모든 경우의 수 : 2^40 -> 완탐 불가능
        // greedy 최적의 해를 찾지 못함 -> edgeCase
        // dp
        // 냅색과 유사 특정 물건을 넣는다/안 넣는다.의 형태
        // 제한된 조건 : 누적 흔적이 < n을 넘지 않을 때의 A의 최소값
        // dp의 기준은?
        // [훔친 물건에 대한][A의 누적 흔적] = B의 누적 흔적을 최소값으로 관리해서 결과를 도출
        // -> B의 누적 흔적은 다양한 결과가 나오는데, 그 중 m보다 작은 값으로 누적 a흔적의 값을 내는 경우의 수가 중요하기 때문에 최소값으로 관리한다.
        
        // 지금까지 훔친물건에 대해서 i를 훔쳤을 때, A 도둑의 흔적이 a인 상태에서 B도둑의 흔적이 얼마나 쌓였는지를 기록해야함
        // 여기서 A의 흔적은 0~n-1까지 가능함으로 모든 상태를 확인해야함.
        
        // case1) A가 훔친다. -> dp[i + 1][newA] = Math.min(dp[i + 1][newA], b);
        // case2) B가 훔친다. -> dp[i + 1][a] = Math.min(dp[i+1][a], newB);
        int INF = Integer.MAX_VALUE/2;
        
        int[][] dp = new int[info.length + 1][n];
        for(int i = 0; i < dp.length; i ++) {
            Arrays.fill(dp[i], INF);
        }
        
        dp[0][0] = 0;
        
        for(int i = 0; i < info.length; i ++) {
            int trailA = info[i][0];
            int trailB = info[i][1];
            
            for(int a = 0; a < n; a ++) {
                int b = dp[i][a];
                if(b == INF) continue;
                
                int newA = a + trailA;
                int newB = b + trailB;
                
                // A가 훔치는 경우
                // A의 누적흔적만 증가
                if(newA < n) {
                    dp[i + 1][newA] = Math.min(dp[i + 1][newA], b);
                }
                
                
                // B가 훔치는 경우
                // B의 누적흔적만 증가
                if(newB < m) {
                    dp[i+1][a] = Math.min(dp[i + 1][a], newB);
                }
            }
        }
        
        // dp[N][a]를 순회하면서
        // 조건을 만족하는 a를 찾으면 해당 a가 결과
        // 찾지못한다면 -1 return
        int answer = -1;
        for(int a = 0; a < n; a++) {
            if(dp[info.length][a] < m) {
                answer = a;
                break;
            }
        }
        return answer;
    }
}