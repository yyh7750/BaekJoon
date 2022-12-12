class Solution {
    
    boolean[][] visited;
	int[] dr = { -1, 1, 0, 0 };
	int[] dc = { 0, 0, -1, 1 };
	int cnt;

	public int[] solution(int m, int n, int[][] picture) {
		int numberOfArea = 0;
		int maxSizeOfOneArea = 0;

		visited = new boolean[m][n];

		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (!visited[i][j] && picture[i][j] != 0) {
					dfs(i, j, picture);
					numberOfArea++;
					maxSizeOfOneArea = Math.max(maxSizeOfOneArea, cnt);
				    cnt = 0;
				}
			}
		}

		int[] answer = new int[2];
		answer[0] = numberOfArea;
		answer[1] = maxSizeOfOneArea;
		return answer;
	}

	private void dfs(int r, int c, int[][] picture) {
		visited[r][c] = true;
        cnt++;

		for (int d = 0; d < 4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];

			if (nr < 0 || nc < 0 || nr >= picture.length || nc >= picture[0].length) {
				continue;
			}

			if (!visited[nr][nc] && picture[r][c] == picture[nr][nc]) {
				dfs(nr, nc, picture);
			}
		}
	}
}