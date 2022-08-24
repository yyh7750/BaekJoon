import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	private static int N, cnt;
	private static int[][] map;

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		dfs(0, 1, 1);

		System.out.println(cnt);
	}

	/**
	 * Description : 파이프를 움직이는 메소드. N,N에 다다르면 return;
	 *
	 * @param r    : row
	 * @param c    : col
	 * @param turn : 방향전환을 위한 int 변수. 0:세로, 1:가로, 2:대각선
	 * @return void
	 * 
	 * @date 2022. 8. 24.
	 * @author 유영훈
	 */
	private static void dfs(int r, int c, int turn) {
		if (r == N - 1 && c == N - 1) {
			cnt++;
			return;
		}

		switch (turn) {
		case 0:
			if (isChecked(r + 1, c) && map[r + 1][c] == 0) {
				dfs(r + 1, c, 0);
			}
			break;
		case 1:
			if (isChecked(r, c + 1) && map[r][c + 1] == 0) {
				dfs(r, c + 1, 1);
			}
			break;
		case 2:
			if (isChecked(r + 1, c) && map[r + 1][c] == 0) {
				dfs(r + 1, c, 0);
			}
			if (isChecked(r, c + 1) && map[r][c + 1] == 0) {
				dfs(r, c + 1, 1);
			}
			break;
		}

		// 벽에 긁히면 안되기 때문에 (r+1,c), (c+1,r) 값을 포함하여 검사
		if (isChecked(r + 1, c + 1) && map[r + 1][c + 1] == 0 && map[r + 1][c] == 0 && map[r][c + 1] == 0) {
			dfs(r + 1, c + 1, 2);
		}
	}

	private static boolean isChecked(int r, int c) {
		if (r >= 0 && r < N && c >= 0 && c < N) {
			return true;
		}
		return false;
	}
}