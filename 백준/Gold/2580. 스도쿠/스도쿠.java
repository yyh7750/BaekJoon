import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	private static final int N = 9;
	private static int[][] map;
	private static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		map = new int[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		br.close();
		dfs(0, 0);
	}

	private static void dfs(int r, int c) {
		// 한 행이 다 찼을 때
		if (c == N) {
			dfs(r + 1, 0);
			return;
		}
		// 스도쿠가 완성 됐을 때
		if (r == N) {
			for (int i = 0; i < map.length; i++) {
				for (int j = 0; j < map.length; j++) {
					sb.append(map[i][j]).append(" ");
				}
				sb.append("\n");
			}

			System.out.println(sb);
			System.exit(0);
		}

		if (map[r][c] == 0) {
			for (int i = 1; i <= N; i++) {
				if (checkRow(r, i) && checkCol(c, i) && checkRect(r, c, i)) {
					map[r][c] = i;
					dfs(r, c + 1);
					map[r][c] = 0;
				} //
			}
		} //
		else {
			dfs(r, c + 1);
		}
	}

	private static boolean checkRect(int r, int c, int value) {
		int x = (r / 3) * 3;
		int y = (c / 3) * 3;

		for (int i = x; i < x + 3; i++) {
			for (int j = y; j < y + 3; j++) {
				if (map[i][j] == value) {
					return false;
				}
			}
		}
		return true;
	}

	private static boolean checkCol(int col, int value) {
		for (int i = 0; i < N; i++) {
			if (map[i][col] == value) {
				return false;
			}
		}
		return true;
	}

	private static boolean checkRow(int row, int value) {
		for (int i = 0; i < N; i++) {
			if (map[row][i] == value) {
				return false;
			}
		}
		return true;
	}
}