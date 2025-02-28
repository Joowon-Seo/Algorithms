import java.util.*;
class Solution {
    
    public static List<Set<Integer>> combinationList = new ArrayList();
    
    public int solution(int n, int[][] q, int[] ans) {
        // set에 입력한 정수를 다 넣는다.
        // list에 숫자들을 넣고 5개 뽑는 경우의 수를 모두 구한다.
        // List안에 모든 케이스를 Set자료구조에 넣는다.
        // 입력한 정수를 순회하면서 시스템 응답과 일치하는지 확인하고
        // 모든 시스템응답이 일치하면 answer ++;
        
        int answer = 0;
        // 입력한 정수 Set으로 모든 경우의 수 구하기
        int[] arr = new int[n];
        for(int i = 0; i < n; i ++) {
            arr[i] = i + 1;
        }
        
        
        int[] result = new int[5];
        combination(arr.length, 5, 0, 0, arr, result);
    
        
        
        // 비밀코드로 사용이 가능한지 확인하기
        for(int i = 0; i < combinationList.size(); i ++) {
            Set<Integer> code = combinationList.get(i);
            boolean flag = true;
            for(int j = 0; j < q.length; j++) {
                if(ans[j] != containCount(code, q[j])) {
                    flag = false;
                    break;
                }
            }
            
            if(flag) {
                answer++;
                // System.out.println(code);
            }
        }
        return answer;
    }
    
    public static void combination(int n, int r, int start, int idx, int[] arr, int[] result) {
        if(idx == r) {
            Set<Integer> set = new HashSet();
            for(int i = 0; i < 5; i++) {
                set.add(result[i]);
            }
            // System.out.println(set);
            combinationList.add(set);
            return;
        }
        
        for(int i = start; i < n; i++) {
            result[idx] = arr[i];
            combination(n, r, i + 1, idx + 1, arr, result);
        }
    }
    
    public static int containCount(Set<Integer> set, int[] arr) {
        int count = 0;
        for(int num : arr) {
            if(set.contains(num)) {
                count++;
            }
        }
        return count;
    }
    
}