import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static class Loc {
		int r;
		int c;
		int cnt;
		int chance;

		public Loc(int r, int c, int cnt, int chance) {
			super();
			this.r = r;
			this.c = c;
			this.cnt = cnt;
			this.chance = chance;
		}
	}

	private static int K, W, H, ans = Integer.MAX_VALUE;
	private static int[][] map;
	private static boolean[][][] visited;
	private static int[] dr = { 0, 1, 0, -1 };
	private static int[] dc = { 1, 0, -1, 0 };
	private static int[] hr = { 2, 2, 1, 1, -1, -2, -2, -1 };
	private static int[] hc = { -1, 1, 2, -2, -2, -1, 1, 2 };

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		K = Integer.parseInt(br.readLine());

		StringTokenizer st = new StringTokenizer(br.readLine());
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());

		map = new int[H][W];
		visited = new boolean[H][W][K + 1];

		for (int i = 0; i < H; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < W; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		bfs(0, 0);

		br.close();
		System.out.println(ans == Integer.MAX_VALUE ? -1 : ans);
	}

	private static void bfs(int r, int c) {
		Queue<Loc> Q = new LinkedList<>();
		Q.offer(new Loc(r, c, 0, K));
		visited[r][c][K] = true;

		while (!Q.isEmpty()) {
			Loc cur = Q.poll();

			if (cur.chance > 0) {
				for (int d = 0; d < 8; d++) {
					int nr = cur.r + hr[d];
					int nc = cur.c + hc[d];

					if (!isChecked(nr, nc) || map[nr][nc] == 1) {
						continue;
					}
					if (visited[nr][nc][cur.chance - 1]) {
						continue;
					}

					Q.offer(new Loc(nr, nc, cur.cnt + 1, cur.chance - 1));
					visited[nr][nc][cur.chance - 1] = true;
				}
			}

			for (int d = 0; d < 4; d++) {
				int nr = cur.r + dr[d];
				int nc = cur.c + dc[d];

				if (!isChecked(nr, nc) || map[nr][nc] == 1) {
					continue;
				}
				if (visited[nr][nc][cur.chance]) {
					continue;
				}

				Q.offer(new Loc(nr, nc, cur.cnt + 1, cur.chance));
				visited[nr][nc][cur.chance] = true;
			}

			if (cur.r == H - 1 && cur.c == W - 1) {
				ans = Math.min(ans, cur.cnt);
			}
		}
	}

	private static boolean isChecked(int nr, int nc) {
		if (nr >= 0 && nr < H && nc >= 0 && nc < W) {
			return true;
		}
		return false;
	}
}
