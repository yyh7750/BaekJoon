import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// bfs Q 하나만 이용하여 풀어보기
public class Main {

	static class Loc {
		int r;
		int c;
		int cnt;
		char type; // 물인지, 고슴도치인지

		public Loc(int r, int c, int cnt, char type) {
			super();
			this.r = r;
			this.c = c;
			this.cnt = cnt;
			this.type = type;
		}
	}

	private static int N, M;
	private static char[][] map;
	private static boolean[][] visited;
	private static int[] dr = { 1, -1, 0, 0 };
	private static int[] dc = { 0, 0, 1, -1 };
	private static ArrayList<Loc> waterList;
	private static boolean flag;
	private static String ans;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[N][M];
		visited = new boolean[N][M];
		waterList = new ArrayList<>();

		int sr = 0, sc = 0;

		for (int i = 0; i < N; i++) {
			String input = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = input.charAt(j);

				if (map[i][j] == 'S') {
					sr = i;
					sc = j;
				} //
				else if (map[i][j] == '*') {
					waterList.add(new Loc(i, j, 0, '*'));
				}
			}
		}

		flag = false;
		bfs(sr, sc);

		br.close();
		System.out.println(ans);
	}

	private static void bfs(int sr, int sc) {
		Queue<Loc> Q = new LinkedList<>();

		for (Loc water : waterList) {
			Q.offer(water);
		}
		Q.offer(new Loc(sr, sc, 0, 'S'));

		while (!Q.isEmpty()) {
			Loc cur = Q.poll();

			if (cur.type == 'S' && map[cur.r][cur.c] == 'D') {
				ans = cur.cnt + "";
				flag = true;
				break;
			}

			for (int d = 0; d < 4; d++) {
				int nr = cur.r + dr[d];
				int nc = cur.c + dc[d];

				if (isChecked(nr, nc) && map[nr][nc] != 'X') {
					// 악마일 경우
					if (cur.type == '*' && map[nr][nc] != 'D' && map[nr][nc] != '*') {
						map[nr][nc] = '*';
						Q.offer(new Loc(nr, nc, 0, '*'));
					}

					// 수현이일 경우
					if (cur.type == 'S' && map[nr][nc] != '*' && !visited[nr][nc]) {
						visited[nr][nc] = true;
						Q.offer(new Loc(nr, nc, cur.cnt + 1, 'S'));
					}
				}
			}
		}

		if (!flag) {
			ans = "KAKTUS";
		}
	}

	private static boolean isChecked(int nr, int nc) {
		if (nr >= 0 && nr < N && nc >= 0 && nc < M) {
			return true;
		}
		return false;
	}
}