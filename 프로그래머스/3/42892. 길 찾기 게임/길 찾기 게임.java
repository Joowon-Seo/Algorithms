import java.util.*;
class Solution {
    
    // 주어진 노드 데이터를 이진트리로 분리 또는 읽는 방법
	// 루트는 y값이 최대 값
	// 같은 레벨은 y값이 같음
	// 왼쪽 서브트리는 부모 노드의 x값보다 작다
	// 오른쪽 서브트리는 부모 노드의 x값보다 크다
	// 전위 순회 : 현 왼 오
	// 후위 순회 : 왼 오 현

	// x값을 기준으로 정렬
	// 현재 범위 내에서 가장 큰 y값을 찾는다
	// 왼쪽 오른쪽 서브트리를 구분한다.
    
    public static int[] preResult, postResult;
	public static int preIdx = 0, postIdx = 0;
	public static Map<Integer, Integer> data = new HashMap<>();
    public int[][] solution(int[][] nodeinfo) {
        int[][] answer = new int[2][nodeinfo.length];
		preResult = new int[nodeinfo.length];
		postResult = new int[nodeinfo.length];

		for (int i = 0; i < nodeinfo.length; i++) {
			data.put(nodeinfo[i][0], i + 1);
		}

		preOrder(nodeinfo);
		postOrder(nodeinfo);

		answer[0] = preResult;
		answer[1] = postResult;
		return answer;
    }
    
    public static void preOrder(int[][] nodeinfo) {
		Arrays.sort(nodeinfo, Comparator.comparingInt(o -> o[0]));
		int maxY = -1;
		int rootIdx = 0;
		for (int i = 0; i < nodeinfo.length; i++) {
			if (nodeinfo[i][1] > maxY) {
				maxY = nodeinfo[i][1];
				rootIdx = i;
			}
		}
		preResult[preIdx++] = data.get(nodeinfo[rootIdx][0]);
		if (rootIdx > 0) {
			preOrder(Arrays.copyOfRange(nodeinfo, 0, rootIdx));
		}

		if (rootIdx < nodeinfo.length - 1) {
			preOrder(Arrays.copyOfRange(nodeinfo, rootIdx + 1, nodeinfo.length));
		}
	}

	public static void postOrder(int[][] nodeinfo) {
		Arrays.sort(nodeinfo, Comparator.comparingInt(o -> o[0]));
		int maxY = -1;
		int rootIdx = 0;
		for (int i = 0; i < nodeinfo.length; i++) {
			if (nodeinfo[i][1] > maxY) {
				maxY = nodeinfo[i][1];
				rootIdx = i;
			}
		}
		if (rootIdx > 0) {
			postOrder(Arrays.copyOfRange(nodeinfo, 0, rootIdx));
		}

		if (rootIdx < nodeinfo.length - 1) {
			postOrder(Arrays.copyOfRange(nodeinfo, rootIdx + 1, nodeinfo.length));
		}

		postResult[postIdx++] = data.get(nodeinfo[rootIdx][0]);
	}
}