/*
    solution - 1 greedy
    두 수를 정렬 -> 비교
    점수 : 23.8
    
    solution - 2 greedy + two pointer
    만약 A > B 일 때, 이 때 B는 경기를 안치른 B 중 가장 큰 수
    이길 수 있는 A가 존재하는지 확인, A만 인덱스를 바꿈
*/

import java.util.*;
class Solution {
    public int solution(int[] A, int[] B) {
        int answer = 0;
        
        Arrays.sort(A);
        Arrays.sort(B);
                
        // B[] 인덱스
        int j = B.length - 1;
        for (int i = A.length - 1; i >= 0; i--) {
            if(A[i] < B[j]) {
                answer++;
                j--;
            }
        }
        
        return answer;
    }
}