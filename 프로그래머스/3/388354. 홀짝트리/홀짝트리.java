import java.util.*;
class Solution {
    public static Map<Integer, Boolean> visited;
    public static Map<Integer, List<Integer>> tree;
    public int[] solution(int[] nodes, int[][] edges) {
        int[] answer = new int[2];
        
        // 모든 노드에 대해서 루트로 두고 완전탐색 불가능
        // nodes 400,000 -> 한 번의 순회로 최대한 많은 정보를 얻고
        // 해당 트리가 어떤 트리가 가능한지 파악해야함
        // 노드번호, 간선의 개수를 알면 해당 노드번호가 루트일 때
        // 자식노드일 때 해당 노드가 어떤 노드인지 알 수 있음
        tree = new HashMap();
        visited = new HashMap();
        
        for(int i = 0; i < nodes.length; i++) {
            tree.put(nodes[i], new ArrayList());
            visited.put(nodes[i], false);
        }
        
        // 간선의 정보로 tree 만들기
        for(int i = 0; i < edges.length; i++) {
            int from = edges[i][0];
            int to = edges[i][1];
            tree.get(from).add(to);
            tree.get(to).add(from);
        }
        
        // nodes를 순회하면서 트리를 찾고, 해당 트리가 가능한 타입 찾기
        for(int i = 0; i < nodes.length; i++) {
            int cur = nodes[i];
            if(!visited.get(cur)) {
                // tree 사이즈가 1인경우
                if(tree.get(cur).size() == 0) {
                    if(cur%2 == 0) {
                        answer[0] ++;
                    } else {
                        answer[1] ++;
                    }
                    continue;
                }
                
                // 그렇지 않은 경우
                int treeType = splitTreeType(cur);
                if(treeType == 1) {
                    answer[0] ++;
                } else if(treeType == 2) {
                    answer[1] ++;
                } else if(treeType == 3) {
                    answer[0] ++;
                    answer[1] ++;
                }
            }
        }
        return answer;
    }
    
    // 일반트리 0
    // 홀짝트리 1
    // 역홀짝트리 2
    // 둘다가능 3
    public static int splitTreeType(int nodeNumber) {
        boolean oddEvenTree = false;
        boolean reverseOddEvenTree = false;
        
        visited.put(nodeNumber, true);
        Deque<Integer> deque = new ArrayDeque();
        deque.add(nodeNumber);

        List<int[]> data = new ArrayList();
        while(!deque.isEmpty()) {
            int cur = deque.poll();
            visited.put(cur, true);
            int ifRoot = 0;
            int ifChild = 0;
            int edgeCnt = tree.get(cur).size();
            int[] nodeType = new int[2];
            
            // 루트노드일 때
            if(cur%2 == edgeCnt%2) {
                // 홀짝노드
                nodeType[0] = 0;
                nodeType[1] = 1;
            } else {
                // 역홀짝노드
                nodeType[0] = 1;
                nodeType[1] = 0;
            }
            
             // 자식노드일 때 (아마 반대인 것 같긴함 root랑)
            // if(cur%2 == (edgeCnt - 1)%2) {
            //     // 홀짝노드
            //     nodeType[1] = 0;
            // } else {
            //     // 역홀짝노드
            //     nodeType[1] = 1;
            // }
            data.add(nodeType);
            for(int i = 0; i < edgeCnt; i ++) {
                int next = tree.get(cur).get(i);
                if(!visited.get(next)) {
                    deque.add(next);
                }
            }
        }
        
        for(int i = 0; i < data.size(); i++) {
            int root = data.get(i)[0];
            boolean flag = true;
            for(int j = 0; j < data.size(); j++) {
                if(i != j) {
                    if(root == data.get(j)[1]) {
                        continue;
                    } else {
                        flag = false;
                        break;
                    }
                }
            }
            
            if(flag) {
                if(root == 0) {
                    oddEvenTree = true;
                }
                
                if(root == 1) {
                    reverseOddEvenTree = true;
                }
            }
        }
        
        if(oddEvenTree&&reverseOddEvenTree) return 3;
        if(oddEvenTree) return 1;
        if(reverseOddEvenTree) return 2;
        return 0;
    }
}