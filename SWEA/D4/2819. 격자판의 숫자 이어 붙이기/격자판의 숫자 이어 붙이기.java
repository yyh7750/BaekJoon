import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Solution {

	// 우하좌상
	private static int[] dr = { 0, 1, 0, -1 };
	private static int[] dc = { 1, 0, -1, 0 };
	private static int N = 4;
	private static String[][] map;
	private static Set<String> set;

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();

		for (int tc = 1; tc <= T; tc++) {
			sb.append("#").append(tc).append(" ");

			map = new String[N][N];
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = st.nextToken();
				}
			}

			set = new HashSet<>();
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					dfs(i, j, 1, map[i][j]);
				}
			}

			sb.append(set.size()).append("\n");
		}

		br.close();
		System.out.println(sb);
	}

	private static void dfs(int r, int c, int depth, String numStr) {
		if (depth == 7) {
			set.add(numStr);
			return;
		}

		for (int d = 0; d < 4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];

			if (isChecked(nr, nc)) {
				dfs(nr, nc, depth + 1, numStr + map[nr][nc]);
			}
		}
	}

	private static boolean isChecked(int nr, int nc) {
		if (nr >= 0 && nr < N && nc >= 0 && nc < N) {
			return true;
		}
		return false;
	}
}