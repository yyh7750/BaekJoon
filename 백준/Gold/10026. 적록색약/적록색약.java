import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

	private static int N;
	private static int[] dr = { -1, 1, 0, 0 };
	private static int[] dc = { 0, 0, -1, 1 };
	private static boolean[][] blind;
	private static boolean[][] normal;

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		char[][] map = new char[N][N];
		char[][] copyMap = new char[N][N];
		for (int i = 0; i < N; i++) {
			String row = br.readLine();
			for (int j = 0; j < N; j++) {
				map[i][j] = row.charAt(j);
				copyMap[i][j] = map[i][j];
			}
		}

		for (int i = 0; i < copyMap.length; i++) {
			for (int j = 0; j < copyMap.length; j++) {
				if (copyMap[i][j] == 'R') {
					copyMap[i][j] = 'G';
				}
			}
		}

		blind = new boolean[N][N];
		normal = new boolean[N][N];
		int bCnt = 0;
		int nCnt = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (!blind[i][j]) {
					bfs(i, j, true, copyMap[i][j], copyMap); // 색약
					bCnt++;
				}
				if (!normal[i][j]) {
					bfs(i, j, false, map[i][j], map); // 일반인
					nCnt++;
				}
			}
		}

		br.close();
		System.out.printf("%d %d", nCnt, bCnt);
	}

	private static void bfs(int r, int c, boolean blindness, char cur, char[][] map) {
		Queue<int[]> Q = new LinkedList<>();
		Q.offer(new int[] { r, c });

		while (!Q.isEmpty()) {
			int[] pollLoc = Q.poll();

			for (int d = 0; d < 4; d++) {
				int nr = pollLoc[0] + dr[d];
				int nc = pollLoc[1] + dc[d];

				if (isChecked(nr, nc)) {
					if (blindness) {
						blind[r][c] = true;
						if (!blind[nr][nc] && map[nr][nc] == cur) {
							blind[nr][nc] = true;
							Q.add(new int[] { nr, nc });
						}
					} //
					else {
						normal[r][c] = true;
						if (!normal[nr][nc] && map[nr][nc] == cur) {
							normal[nr][nc] = true;
							Q.add(new int[] { nr, nc });
						}
					}
				}
			}
		}
	}

	private static boolean isChecked(int r, int c) {
		if (r >= 0 && r < N && c >= 0 && c < N) {
			return true;
		}
		return false;
	}
}