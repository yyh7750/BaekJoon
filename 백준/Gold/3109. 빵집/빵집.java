import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	private static int[] dr = { -1, 0, 1 }; // 우상, 우, 우하
	private static int[] dc = { 1, 1, 1 };
	private static char[][] map;
	private static boolean[][] visited;
	private static int R, C, result;
	private static boolean check;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		map = new char[R][C];
		visited = new boolean[R][C];
		for (int i = 0; i < R; i++) {
			map[i] = br.readLine().toCharArray();
		}

		for (int r = 0; r < R; r++) {
			check = false;
			dfs(r, 0);
		}

		br.close();
//
//		for (int i = 0; i < R; i++) {
//			for (int j = 0; j < C; j++) {
//				System.out.print(visited[i][j] + " ");
//			}
//			System.out.println();
//		}
		System.out.println(result);
	}

	private static void dfs(int r, int c) {
		if (c == C - 1) {
			result++;
			check = true;
			return;
		}

		for (int d = 0; d < 3; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];

			if (isChecked(nr, nc) && map[nr][nc] == '.' && !visited[nr][nc]) {
				visited[nr][nc] = true;
				dfs(nr, nc);
				if (check) {
					return;
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