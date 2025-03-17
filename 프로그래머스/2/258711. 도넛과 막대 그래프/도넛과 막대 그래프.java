import java.util.*;
class Solution {

    public static Map<Integer, List<Integer>> graph;
    public int[] solution(int[][] edges) {
        // 단방향 간선
        // 간선의 정보가 최대 1,000,000 -> O(N)안에 해결
        // 한번 순회로 알 수 있는 정보를 정리하자
        
        // 도넛 크기n, 정점n, 간선n
        // 어디서 시작하든 상관 없음 결국 돌아옴
        // 정점의 개수와 간선의 개수가 같음
        // 나가는 간선이 1개 존재
        // 끝 정점에서는 간선이 없음
        
        // 막대 정점 크기n, 정점n, 간선 n-1
        // 어디서 시작하든 끝이 있음
        // 정점의 개수 > 간선의 개수
        
        // 8자 모양 정점 크기n, 정점2n+1, 간선 2n+2
        // 어디서 시작하든 상관 없음 결국 돌아옴
        // 정점의 개수와 < 간선의 개수
        // 나가는 간선이 2개 이상인게 존재함
        
        // 임의의 생성한 정점
        // 나가는 간선만 존재해야 함 (2개 이상) -> 도넛 모양 그래프, 막대 모양 그래프, 8자 모양 그래프의 수의 합은 2이상입니다.
        
        
        // 간선의 정보를 받는다.
        // 생성한 정점을 찾는다.
        
        // 생성한 정점을 기준으로 하나씩 그래프를 순회시키고
        // 간선의 개수, 정점의 개수로 어떤 그래프인지 판단 할 수 있음
        // 결과 반환 
        
        graph = new HashMap();
        int startPoint = 0;
        for(int i = 1; i<= 1000000; i ++) {
            graph.put(i, new ArrayList());
        }
        
        // 주는 간선일 때, put
        Set<Integer> startPoints = new HashSet();
        // 받는 간선일 때, put
        Set<Integer> endPoints = new HashSet();
        
        for(int i = 0; i < edges.length; i++) {
            int to = edges[i][0];
            int from = edges[i][1];
            
            graph.get(to).add(from);
            startPoints.add(to);
            endPoints.add(from);
        }
        
        for(int point : endPoints) {
            startPoints.remove(point);
        }
        
        for(int point : startPoints) {
            if(graph.get(point).size() >= 2) {
                startPoint = point;
                break;
            }
        }
        
        int[] answer = new int[4];
        answer[0] = startPoint;
        for(int i = 0; i < graph.get(startPoint).size(); i++) {
            int cur = graph.get(startPoint).get(i);
            int type = getGraphType(cur);
            
            if(type == 0) {
                answer[1] += 1;
            } else if(type == 1) {
                answer[2] += 1;
            } else if(type == 8) {
                answer[3] += 1;
            }
            
        }
        
        
        return answer;
    }
    
    // 타원 0
    // 막대 1
    // 8자 8
    public static int getGraphType(int point) {
        Set<Integer> visited = new HashSet();
        visited.add(point);
        Deque<Integer> dq = new ArrayDeque();
        dq.add(point);
        
        while(!dq.isEmpty()) {
            int cur = dq.poll();
            
            if(graph.get(cur).size() == 0) {
                return 1;
            }
            
            if(graph.get(cur).size() >= 2) {
                return 8;
            }
            
            int next = graph.get(cur).get(0);
            if(visited.contains(next)) {
                return 0;
            }
            
            dq.add(next);
            visited.add(next);
            
        }
        
        return -1;
    }
}