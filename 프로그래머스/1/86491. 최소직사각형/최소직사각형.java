class Solution {
    public int solution(int[][] sizes) {
		int wallet_w = 0;
		int wallet_l = 0;

		for (int i = 0; i < sizes.length; i++) {
			int w = sizes[i][0];
			int l = sizes[i][1];

			if (w >= l) {
				wallet_w = Math.max(wallet_w, w);
				wallet_l = Math.max(wallet_l, l);
			} else {
				wallet_w = Math.max(wallet_w, l);
				wallet_l = Math.max(wallet_l, w);
			}
		}

		return wallet_w * wallet_l;
	}
}