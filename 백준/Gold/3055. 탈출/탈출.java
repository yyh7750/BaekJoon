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

		public Loc(int r, int c, int cnt) {
			super();
			this.r = r;
			this.c = c;
			this.cnt = cnt;
		}
	}

	private static int R, C, result = Integer.MIN_VALUE;
	private static char[][] map;
	private static int[] dr = { -1, 1, 0, 0 };
	private static int[] dc = { 0, 0, -1, 1 };
	private static Queue<Loc> amurQ, waterQ;
	private static boolean[][] amurVisited;
	private static boolean[][] waterVisited;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R][C];
		amurQ = new LinkedList<>();
		waterQ = new LinkedList<>();

		for (int i = 0; i < R; i++) {
			String row = br.readLine();
			for (int j = 0; j < C; j++) {
				map[i][j] = row.charAt(j);

				if (map[i][j] == 'S') {
					amurQ.offer(new Loc(i, j, 0));
				} //
				else if (map[i][j] == '*') {
					waterQ.offer(new Loc(i, j, 0));
				}
			}
		}

		amurBfs();

		System.out.println(result == Integer.MIN_VALUE ? "KAKTUS" : result);
	}

	private static void amurBfs() {
		amurVisited = new boolean[R][C];

		while (!amurQ.isEmpty()) {
			waterBfs();
			int qSize = amurQ.size();

			for (int i = 0; i < qSize; i++) {
				Loc cur = amurQ.poll();

				if (map[cur.r][cur.c] == 'D') {
					result = cur.cnt;
				}

				for (int d = 0; d < 4; d++) {
					int nr = cur.r + dr[d];
					int nc = cur.c + dc[d];

					if (isChecked(nr, nc) && !amurVisited[nr][nc] && map[nr][nc] != '*' && map[nr][nc] != 'X') {
						amurVisited[nr][nc] = true;
						amurQ.offer(new Loc(nr, nc, cur.cnt + 1));
					}
				}
			}
		}
	}

	private static void waterBfs() {
		waterVisited = new boolean[R][C];

		int qSize = waterQ.size();

		for (int i = 0; i < qSize; i++) {
			Loc cur = waterQ.poll();

			for (int d = 0; d < 4; d++) {
				int nr = cur.r + dr[d];
				int nc = cur.c + dc[d];

				if (isChecked(nr, nc) && !waterVisited[nr][nc] && map[nr][nc] == '.') {
					waterVisited[nr][nc] = true;
					map[nr][nc] = '*';
					waterQ.offer(new Loc(nr, nc, 0));
				}
			}
		}
	}

	private static boolean isChecked(int r, int c) {
		if (r >= 0 && r < R && c >= 0 && c < C) {
			return true;
		}
		return false;
	}
}