import java.util.LinkedList;
import java.util.Queue;

public class Solution {

	private class Loc {
		int r, c, cnt;

		public Loc(int r, int c, int cnt) {
			super();
			this.r = r;
			this.c = c;
			this.cnt = cnt;
		}
	}

	public int solution(int[][] maps) {

		int n = maps.length;
		int m = maps[0].length;

		int answer = bfs(n, m, maps);

		return answer;
	}

	/**
	 * @methodName : bfs
	 * @Description :
	 * 
	 * @param n
	 * @param m
	 * @param maps
	 * @return int
	 *
	 * @date 2022. 12. 6.
	 * @author 유영훈
	 */
	private int bfs(int n, int m, int[][] maps) {
		int[] dr = { -1, 1, 0, 0 };
		int[] dc = { 0, 0, -1, 1 };
		boolean[][] visited = new boolean[n][m];

		Queue<Loc> Q = new LinkedList<>();
		Q.offer(new Loc(n - 1, m - 1, 1));
		visited[n - 1][m - 1] = true;

		while (!Q.isEmpty()) {
			Loc cur = Q.poll();

			for (int d = 0; d < 4; d++) {
				int nr = cur.r + dr[d];
				int nc = cur.c + dc[d];

				if (nr == 0 && nc == 0) {
					return cur.cnt + 1;
				}

				if (isChecked(nr, nc, n, m) && !visited[nr][nc] && maps[nr][nc] == 1) {
					Q.offer(new Loc(nr, nc, cur.cnt + 1));
					visited[nr][nc] = true;
				}
			}
		}

		return -1;
	}

	private boolean isChecked(int nr, int nc, int n, int m) {
		if (nr >= 0 && nc >= 0 && nr < n && nc < m) {
			return true;
		}
		return false;
	}
}