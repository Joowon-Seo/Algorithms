import java.util.*;
class Solution {
    public int solution(String[] friends, String[] gifts) {
        // A > B, A <- B
        // A == B or 0, 선물지수 큰 사람이 받음
        // 선물 지수 : 준 선물 - 받은 선물
        // 선물지수까지 같다면 선물을 주고받지 않음
        
        // A B : A -> B
        
        // gifts의 데이터를 HashMap에 저장하고 준 데이터를 count
        // friends를 2중 순회하여 모든 경우의 수를 구하고
        // friends[i]로 다음달에 받는 선물 개수를 관리
        int answer = 0;
        Map<String, Integer> map = new HashMap();
        Map<String, Integer> giveMap = new HashMap();
        Map<String, Integer> takeMap = new HashMap();
        
        for(int i = 0; i < gifts.length; i++) {
            String[] str = gifts[i].split(" ");
            String A = str[0];
            String B = str[1];
            map.put(gifts[i], map.getOrDefault(gifts[i], 0) + 1);
            giveMap.put(A, giveMap.getOrDefault(A, 0) + 1);
            takeMap.put(B, takeMap.getOrDefault(B, 0) + 1);
        }
        
        for(int i = 0; i < friends.length; i++) {
            for(int j = i + 1; j < friends.length; j++) {
                // 누가 더 많이 주었는가?
                String A = friends[i];
                String B = friends[j];
                int AB = map.getOrDefault(A + " " + B, 0);
                int BA = map.getOrDefault(B + " " + A, 0);
                
                if(AB > BA) {
                    map.put(A, map.getOrDefault(A, 0) + 1);
                } else if(BA > AB) {
                    map.put(B, map.getOrDefault(B, 0) + 1);
                } else if(AB == BA) {
                    // 선물지수
                    int A_GiftPoint = giveMap.getOrDefault(A, 0) - takeMap.getOrDefault(A, 0);
                    int B_GiftPoint = giveMap.getOrDefault(B, 0) - takeMap.getOrDefault(B, 0);
                    if(A_GiftPoint > B_GiftPoint) {
                        map.put(A, map.getOrDefault(A, 0) + 1);
                    } else if(A_GiftPoint < B_GiftPoint) {
                        map.put(B, map.getOrDefault(B, 0) + 1);
                    }
                } 
            }
        }
        for(int i = 0; i < friends.length; i ++) {
            answer = Math.max(answer, map.getOrDefault(friends[i], 0));
        }
        return answer;
    }
}