import java.util.*;
class Solution {
    
    // 중앙 삼각형은 무조건 사용함
	// 주어진 데이터가 총 10만임으로 O(n), O(nlong)으로 해결해야 함

	// 위쪽 삼각형이 존재하는 경우 or 없는 경우로 나누어짐
	// 1. 위쪽 삼각형을 선택하는 경우 (tops[i] == 1 만 가능) -> 마름모
	// 2. 왼쪽 삼각형을 선택하는 경우 -> 마름모
	// 3. 오른쪽 삼각형을 선택하는 경우 -> 마름모
	// 3-1. 다음 중앙 삼각형은 왼쪽 삼각형을 선택할 수 없음 -> 오른쪽 아래 삼각형을 선택하는 경우와, 그렇지 않은 경우를 나누어서 계산해야 함
	// 4. 그 외에는 정삼각형을 사용해서 타일을 채움

	// tops[i] == 1 경우
	// 1. 오른쪽 삼각형을 선택하는 경우, 이전 삼각형에서 어떤 경우를 선택 여부와 상관 없이 오른쪽 삼각형을 선택할 수 있음
	// -> rightSelect[k] = rightNotSelect[k - 1] + rightSelect[k - 1]

	// 2, 오른쪽 삼각형을 선택하지 않는 경우
	// 2-1, 이전 삼각형이 오른쪽을 선택했을 경우, 위쪽 또는 정삼각형을 선택할 수 있음
	// 2-2, 이전 삼각형이 오른쪽을 선택하지 않았을 경우, 왼쪽 또는 위쪽 또는 정삼각형을 선택할 수 있음
	// -> rightNotSelect[k] = rightSelect[k - 1] * 2 + rightNotSelect[k - 1] * 3

	// tops[i] == 0 경우
	// 1. 오른쪽 삼각형을 선택하는 경우, tops[i] == 1인 경우와 같음
	// -> rightSelect[k] = leftSelect[k - 1] + rightSelect[k - 1]
	// 2. 오른쪽 삼각형을 선택하지 않는 경우
	// 2-1. 이전 삼각형이 오른쪽을 선택했을 경우 정삼각형만 선택할 수 있음
	// 2-2. 이전 삼각형이 오른쪽을 선택하지 않았을 경우 왼쪽 또는 정삼각형을 선택할 수 있음
	// -< rightNotSelect[k] = rightSelect[k - 1] + rightNotSelect[k - 1] * 2
    
    public int solution(int n, int[] tops) {
        int[] rightSelect = new int[n + 1];
		int[] rightNotSelect = new int[n + 1];

		rightSelect[1] = 1;
		rightNotSelect[1] = tops[0] == 1 ? 3 : 2;
		for (int i = 2; i <= n; i++) {
			if (tops[i - 1] == 1) {
				rightNotSelect[i] = (rightSelect[i - 1] * 2 + rightNotSelect[i - 1] * 3) % 10007;
			} else {
				rightNotSelect[i] = (rightSelect[i - 1] + rightNotSelect[i - 1] * 2) % 10007;
			}
			rightSelect[i] = (rightNotSelect[i - 1] + rightSelect[i - 1]) % 10007;
		}
		return (rightSelect[n] + rightNotSelect[n]) % 10007;
    }
}