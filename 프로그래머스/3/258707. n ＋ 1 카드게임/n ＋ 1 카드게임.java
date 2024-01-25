import java.util.*;
class Solution {
    
    // 코인을 사용하는 우선순위

	// 중복없이 1~n에서 n이 6의 배수 임으로 n + 1을 만들 수 있는 조합은 무조건 1개

	// 1. 코인을 1개 사용해서 n + 1을 만들 수 있는가?
	// 2. 코인을 2개 사용해서 n + 1을 만들 수 있는가?
	// 2-1. 뽑은 두 카드를 사용해야 할 때 :

	// 1. 현재 손에 든 것과 합쳐서 n + 1을 만들 수 있는가
	// 2. 최대로 갈 수 있는 라운드에서 하나를 뽑으면 만들 수 있는가?
	// 2-1. 그 때 코인이 있는지 어떻게 아는가?
	// 2-2. 결국 그 조합을 사용한다는 것은 코인을 2개 사용하는 것 따라서 코인을 사용하여 미리 뽑는다. 코인 개수를 관리 할 수 있음
    
    static int n;
    
    public int solution(int coin, int[] cards) {
        n = cards.length;
        
        boolean hand[] = new boolean[n+1];
        boolean paid[] = new boolean[n+1];
        int coinLeft = coin;
        
        for(int i = 0; i < n/3; i++){
            hand[cards[i]] = true;
            paid[cards[i]] = true;
        }
        
        int answer = 1;
        for(int i = n/3; i < n; i+=2){
            if(coinLeft > 0){
                hand[cards[i]] = true;
                hand[cards[i+1]] = true;
            }
            
            boolean pass = false;
            int minCost = 3;
            int cardThrown = -1;
            for(int j = 1; j <= n; j++){
                if(!hand[j]){
                    continue;
                }
                
                if(hand[n + 1 - j]){
                    int cost = (paid[j] ? 0 : 1) + (paid[n + 1 - j] ? 0 : 1);
                    if(coinLeft < cost || minCost <= cost){
                        continue;
                    }
                 
                    pass = true;
                    cardThrown = j;
                    minCost = cost;
                }
            }
            
            if(!pass){
                break;
            } 
            hand[cardThrown] = false;
            hand[n + 1 - cardThrown] = false;
            coinLeft -= minCost;
                
            answer ++;
        }

        return answer;
    }
}