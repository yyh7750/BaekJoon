import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {

	static class Loc {
		int r, c, cnt;

		public Loc(int r, int c, int cnt) {
			super();
			this.r = r;
			this.c = c;
			this.cnt = cnt;
		}
	}

	private static int N, K, ans;
	private static int[][] map;
	private static List<Loc> peakList;
	private static int[] dr = { -1, 1, 0, 0 };
	private static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		for (int tc = 1; tc <= T; tc++) {
			sb.append("#").append(tc).append(" ");

			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			map = new int[N][N];
			int max = 0;

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					max = Math.max(max, map[i][j]);
				}
			}

			peakList = new ArrayList<>();
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][j] == max) {
						peakList.add(new Loc(i, j, 0));
					}
				}
			}

			ans = 0;
			digging();

			sb.append(ans).append("\n");
		}

		br.close();
		System.out.println(sb);
	}

	private static void digging() {
		for (int k = 0; k <= K; k++) {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][j] - k < 0) {
						continue;
					} //
					else {
						map[i][j] -= k;

						for (int idx = 0; idx < peakList.size(); idx++) {
							bfs(peakList.get(idx).r, peakList.get(idx).c);
						}

						map[i][j] += k;
					}
				}
			}
		}
	}

	private static void bfs(int r, int c) {
		Queue<Loc> Q = new LinkedList<>();
		Q.offer(new Loc(r, c, 1));

		while (!Q.isEmpty()) {
			Loc cur = Q.poll();

			for (int d = 0; d < 4; d++) {
				int nr = cur.r + dr[d];
				int nc = cur.c + dc[d];

				if (isChecekd(nr, nc) && map[cur.r][cur.c] > map[nr][nc]) {
					Q.offer(new Loc(nr, nc, cur.cnt + 1));
				}
			}
			ans = Math.max(ans, cur.cnt);
		}
	}

	private static boolean isChecekd(int nr, int nc) {
		if (nr >= 0 && nc >= 0 && nr < N && nc < N) {
			return true;
		}
		return false;
	}
}