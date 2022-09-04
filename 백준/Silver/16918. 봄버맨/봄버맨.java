import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	private static int R, C, N, time = 1;
	private static int[][] map;
	private static int[] dr = { -1, 1, 0, 0 };
	private static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());

		map = new int[R][C];
		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			String row = st.nextToken();
			for (int j = 0; j < C; j++) {
				if (row.charAt(j) == '.') {
					map[i][j] = 0;
				} //
				else {
					map[i][j] = 3;
				}
			}
		}

		while (time++ < N) {
			if (time % 2 == 0) {
				setBomb(); // 전체 폭탄 깔기
			} //
			else {
				bomb(); // 3초 지난 폭탄 폭발
			}
		}

		br.close();
		printMap();
	}

	/**
	 * Description : 전체 폭탄 깔기. 3초 타이머.
	 * 
	 * @return void
	 *
	 * @date 2022. 9. 5.
	 * @author 유영훈
	 */
	private static void setBomb() {
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (map[i][j] == 0) {
					map[i][j] = time + 3;
				} //
			}
		}
	}

	/**
	 * Description : 3초 지난 폭탄만 터트리기
	 * 
	 * @return void
	 *
	 * @date 2022. 9. 5.
	 * @author 유영훈
	 */
	private static void bomb() {
		List<int[]> loc = new ArrayList<int[]>();
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				// 3초 전에 심은 폭탄들이 터질 차례
				if (map[r][c] == time) {
					map[r][c] = 0;
					for (int d = 0; d < 4; d++) {
						int nr = r + dr[d];
						int nc = c + dc[d];

						if (isChecked(nr, nc)) {
							loc.add(new int[] { nr, nc });
						}
					}
				}
			}
		}

		for (int i = 0; i < loc.size(); i++) {
			map[loc.get(i)[0]][loc.get(i)[1]] = 0;
		}
	}

	/**
	 * Description : 네 방향으로 폭탄 폭발 시키기 전 배열 인덱스 범위 검사
	 * 
	 * @param r : row
	 * @param c : col
	 * @return
	 * @return boolean
	 *
	 * @date 2022. 9. 5.
	 * @author 유영훈
	 */
	private static boolean isChecked(int r, int c) {
		if (r >= 0 && r < R && c >= 0 && c < C) {
			return true;
		}
		return false;
	}

	/**
	 * Description : 최종 배열 출력
	 * 
	 * @return void
	 *
	 * @date 2022. 9. 5.
	 * @author 유영훈
	 */
	private static void printMap() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (map[i][j] > 0) {
					sb.append('O');
				} //
				else {
					sb.append('.');
				}
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
}