import java.util.*;
/*
    solution - 1 fail : 반례 존재 많음
    greedy
    
    가능한 중앙값과 가까운 자연수를 원소로 같는 집합을 만들기
    
    1. s가 n보다 커야함
    2-1. s의 나머지 연산이 0인 경우 : (s/n) ^ n
    2.2  s의 나머지 연산이 0이 아닌 경우 : 집합의 모든 원소 중 (n - 1)개의 원소는 (s/n + 1) + (s - (n - 1) * (s/n + 1))
    
    solution - 2
    
    이전 방법에서 s%n == 0 이라면 (s/n)을 사용한다는 점은 변함 없음
    다만 2-2에서 (n - 1)개의 원소를 (s/n + 1)로 사용하는 것이 오판
    s%n == 0 이 될 때 까지, 남은 원소의 개수를 update하면서 진행 (s/n + 1)
    s%n == 0이 된다면 나머지 원소는 모두 s/n이 됨
    
*/
class Solution {
    public int[] solution(int n, int s) {
        int[] answer = new int[n];
        
        if(s < n) {
            return new int [] {-1};
        }
        
        int idx = answer.length - 1;
        
        while (s%n != 0) {
            answer[idx--] = s/n + 1;
            
            s -= (s/n + 1);
            n--;
        }
        
        
        for(int i = idx; i >= 0; i--) {
            answer[i] = s/n;
        }
        
        
        return answer;
    }
}