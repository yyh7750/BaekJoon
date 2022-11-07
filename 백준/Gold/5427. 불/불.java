import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static class Loc {
		int r, c, cnt;
		char type; // 불인지, 상근이인지

		public Loc(int r, int c, int cnt, char type) {
			super();
			this.r = r;
			this.c = c;
			this.cnt = cnt;
			this.type = type;
		}
	}

	private static int W, H, Ans;
	private static char[][] map;
	private static boolean[][] visited;
	private static List<Loc> fireList;
	private static int[] dr = { -1, 1, 0, 0 };
	private static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		for (int tc = 0; tc < T; tc++) {
			st = new StringTokenizer(br.readLine());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());

			map = new char[H][W];
			visited = new boolean[H][W];
			fireList = new ArrayList<>();

			int sr = 0, sc = 0;
			// '.' = 빈공간, '#' = 벽, '@' = 상근이, '*' = 불
			for (int i = 0; i < H; i++) {
				String str = br.readLine();
				for (int j = 0; j < W; j++) {
					map[i][j] = str.charAt(j);

					// 상근이의 시작위치를 저장
					if (map[i][j] == '@') {
						sr = i;
						sc = j;
					} //
					else if (map[i][j] == '*') {
						// 불의 시작 위치들을 담기 위한 배열
						fireList.add(new Loc(i, j, 0, '*'));
					}
				}
			}

			Ans = Integer.MAX_VALUE;
			// 상근이의 시작 위치를 기준으로 BFS 탐색 시작
			bfs(sr, sc);

			if (Ans == Integer.MAX_VALUE) {
				sb.append("IMPOSSIBLE");
			} //
			else {
				sb.append(Ans);
			}
			sb.append("\n");
		}

		br.close();
		System.out.println(sb);
	}

	/**
	 * @methodName : bfs
	 * @Description
	 */
//	 * 상근이는 불이 번져있는 칸과 번지려고 하는 칸으로 움직이지 못한다.
//	 * 상근이가 있는 칸에 불이 옮겨옴과 동시에 다른 칸으로 이동 가능하다. 
	/**
	 * @param sr : 상근이 시작위치 r
	 * @param sc : 상근이 시작위치 c
	 * @return void
	 *
	 * @date 2022. 11. 7.
	 * @author 유영훈
	 */
	private static void bfs(int sr, int sc) {
		Queue<Loc> Q = new LinkedList<>();

		// 불의 시작정보를 큐에 담아준다
		for (Loc fire : fireList) {
			Q.offer(fire);
		}
		// 상근이의 정보를 담아준다.
		Q.offer(new Loc(sr, sc, 0, '@'));
		visited[sr][sc] = true;

		while (!Q.isEmpty()) {
			Loc cur = Q.poll();

			for (int d = 0; d < 4; d++) {
				int nr = cur.r + dr[d];
				int nc = cur.c + dc[d];

				// 빌딩 탈출 성공
				if (!isChecked(nr, nc) && cur.type == '@') {
					Ans = Math.min(Ans, cur.cnt + 1);
                    return;
				}

				if (isChecked(nr, nc) && map[nr][nc] != '#') {
					// 상근이
					if (isChecked(nr, nc) && cur.type == '@' && !visited[nr][nc] && map[nr][nc] == '.') {
						visited[nr][nc] = true;
						Q.offer(new Loc(nr, nc, cur.cnt + 1, '@'));
					}

					// 불
					if (isChecked(nr, nc) && cur.type == '*' && map[nr][nc] != '*') {
						map[nr][nc] = '*';
						Q.offer(new Loc(nr, nc, 0, '*'));
					}
				}
			}
		}
	}

	// 범위검사
	private static boolean isChecked(int nr, int nc) {
		if (nr >= 0 && nr < H && nc >= 0 && nc < W) {
			return true;
		}
		return false;
	}
}