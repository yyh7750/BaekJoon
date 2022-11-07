import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static class Loc {
		int r, c, cnt, chance;

		public Loc(int r, int c, int cnt, int chance) {
			super();
			this.r = r;
			this.c = c;
			this.cnt = cnt;
			this.chance = chance;
		}
	}

	private static int N, M;
	private static int[][] map;
	private static boolean[][][] visited;
	private static int[] dr = { -1, 1, 0, 0 };
	private static int[] dc = { 0, 0, -1, 1 };
	private static int Ans = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		visited = new boolean[N][M][2];

		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = str.charAt(j) - '0';
			}
		}

		bfs(0, 0);

		br.close();
		System.out.println(Ans == Integer.MAX_VALUE ? -1 : Ans);
	}

	// 벽을 부쉈는지 안부쉈는지에 대한 처리가 필요.
	// -> 3차원 방문배열을 이용하여 cur.chance를 기준으로 벽을 부순 상태인지 아닌지에 대한 처리를 해준다.
	private static void bfs(int r, int c) {
		Queue<Loc> Q = new LinkedList<>();
		Q.offer(new Loc(r, c, 1, 1));
		visited[r][c][1] = true; // 찬스가 남아 있는 경우. (벽을 부수지 않은 상태)

		while (!Q.isEmpty()) {
			Loc cur = Q.poll();
			
			if (cur.r == N - 1 && cur.c == M - 1) {
				Ans = cur.cnt;
				return;
			}

			for (int d = 0; d < 4; d++) {
				int nr = cur.r + dr[d];
				int nc = cur.c + dc[d];

				if (isChecked(nr, nc)) {
					// 벽일때
					if (map[nr][nc] == 1) {
						// 부술 수 있다면 부수고 간다.
						if (cur.chance == 1) {
							visited[nr][nc][0] = true;
							Q.offer(new Loc(nr, nc, cur.cnt + 1, 0));
						}
					}
					// map[nr][nc] == 0 빈 공간일 경우 그냥 간다.
					else {
						if (!visited[nr][nc][cur.chance]) {
							visited[nr][nc][cur.chance] = true;
							Q.offer(new Loc(nr, nc, cur.cnt + 1, cur.chance));
						}
					}
				}
			}
		}
	}

	private static boolean isChecked(int nr, int nc) {
		if (nr >= 0 && nc >= 0 && nr < N && nc < M) {
			return true;
		}
		return false;
	}
}