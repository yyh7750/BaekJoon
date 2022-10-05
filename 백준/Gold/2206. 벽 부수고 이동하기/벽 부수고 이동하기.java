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
		int chance; // 1이면 벽 부수고 이동 가능, 0이면 불가능

		public Loc(int r, int c, int cnt, int chance) {
			super();
			this.r = r;
			this.c = c;
			this.cnt = cnt;
			this.chance = chance;
		}
	}

	private static int N, M, ans = Integer.MAX_VALUE;
	private static int[][] map;
	private static int[] dr = { 0, 1, 0, -1 };
	private static int[] dc = { 1, 0, -1, 0 };
	private static boolean[][][] visited;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		visited = new boolean[N][M][2];

		for (int i = 0; i < N; i++) {
			char[] inputArr = br.readLine().toCharArray();
			for (int j = 0; j < M; j++) {
				map[i][j] = inputArr[j] - '0';
			}
		}

		bfs(0, 0);

		br.close();
		ans = ans == Integer.MAX_VALUE ? -1 : ans;
		System.out.println(ans);
	}

	private static void bfs(int sr, int sc) {
		Queue<Loc> Q = new LinkedList<>();
		Q.offer(new Loc(sr, sc, 1, 1));
		visited[sr][sc][1] = true;

		while (!Q.isEmpty()) {
			Loc cur = Q.poll();

			for (int d = 0; d < 4; d++) {
				int nr = cur.r + dr[d];
				int nc = cur.c + dc[d];

				if (isChecked(nr, nc)) {
					// 벽을 안부수고 가는 경우
					if(map[nr][nc] == 0 && !visited[nr][nc][cur.chance]) {
						Q.offer(new Loc(nr, nc, cur.cnt+1, cur.chance));
						visited[nr][nc][cur.chance] = true;
					}
					
					// 벽을 부수고 가는 경우
					if(map[nr][nc] == 1 && cur.chance == 1) {
						Q.offer(new Loc(nr, nc, cur.cnt+1, 0));
						visited[nr][nc][0] = true;
					}
				}
			}

			if (cur.r == N - 1 && cur.c == M - 1) {
				ans = Math.min(ans, cur.cnt);
			}
		}
	}

	private static boolean isChecked(int nr, int nc) {
		if (nr >= 0 && nr < N && nc >= 0 && nc < M) {
			return true;
		}
		return false;
	}
}