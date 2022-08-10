import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	private static int[][] arr;
	private static boolean[][] visited;
	// 하 우 상 좌
	private static int[] dc = { 1, 0, -1, 0 };
	private static int[] dr = { 0, 1, 0, -1 };
	private static int N, M, R;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());

		arr = new int[N][M];
		visited = new boolean[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int round = Math.min(N, M) / 2;

		recursion(0, 0, arr[0][0], 0, round);

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				System.out.print(arr[i][j] + " ");
			}
			System.out.println();
		}
	}

	private static void recursion(int c, int r, int cur, int loop, int round) {
		if (round == 0) {
			return;
		}

		int turn = 0;

		while (turn < 4) {
			int nc = c + dc[turn % 4];
			int nr = r + dr[turn % 4];

			if (isChecked(nc, nr) && !visited[nc][nr]) {
				int tmp = arr[nc][nr];
				if (loop + 1 == R) {
					visited[nc][nr] = true;
				}
				arr[nc][nr] = cur;
				cur = tmp;
				c = nc;
				r = nr;
			} //
			else {
				turn++;
			}
		}

		loop++;
		if (loop == R) {
			recursion(c + 1, r + 1, arr[c + 1][r + 1], 0, round - 1);
		} //
		else {
			recursion(c, r, arr[c][r], loop, round);
		}
	}

	private static boolean isChecked(int c, int r) {
		if (c >= 0 && c < N && r >= 0 && r < M) {
			return true;
		}
		return false;
	}
}