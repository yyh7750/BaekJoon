import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	private static int[][] map, dp;
	private static int[] dr = { 0, 1, 0, -1 };
	private static int[] dc = { 1, 0, -1, 0 };
	private static int M, N, Ans;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		map = new int[M][N];
		dp = new int[M][N];

		for (int i = 0; i < dp.length; i++) {
			Arrays.fill(dp[i], -1);
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

//		printMap();

		br.close();

		Ans = dfs(0, 0);
		System.out.println(Ans);
	}

	private static int dfs(int r, int c) {
		if (r == M - 1 && c == N - 1) {
			return 1;
		}

		if (dp[r][c] != -1) {
			return dp[r][c];
		}

		dp[r][c] = 0;

		for (int d = 0; d < 4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];

			if (isChecked(nr, nc) && map[r][c] > map[nr][nc]) {
				dp[r][c] = dp[r][c] + dfs(nr, nc);
			}
		}

		return dp[r][c];
	}

	private static boolean isChecked(int nr, int nc) {
		if (nr >= 0 && nr < M && nc >= 0 && nc < N) {
			return true;
		}
		return false;
	}

	private static void printMap() {
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
	}
}