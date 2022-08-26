import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

	static class Loc {
		int r;
		int c;

		public Loc(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}
	}

	private static int R, C, T;
	private static int[][] map;
	private static int[][] dustMap;
	private static ArrayList<Integer> airCleaner;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());

		map = new int[R][C];
		airCleaner = new ArrayList<>();
		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < C; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == -1) {
					airCleaner.add(i);
				}
			}
		}

		for (int i = 0; i < T; i++) {
			dustMap = new int[R][C];
			diffusionDust();
			airCleaning1();
			airCleaning2();
		}

		System.out.println(getDustSum() + 2);
	}

	private static void diffusionDust() {
		int[] dr = { -1, 1, 0, 0 };
		int[] dc = { 0, 0, -1, 1 };

		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				if (map[r][c] == -1) {
					dustMap[r][c] = -1;
					continue;
				}
				int cnt = 0;
				for (int d = 0; d < 4; d++) {
					int nr = r + dr[d];
					int nc = c + dc[d];

					if (isChecked(nr, nc) && dustMap[nr][nc] != -1) {
						int diffusion = map[r][c] / 5;
						if (diffusion != 0) {
							dustMap[nr][nc] += diffusion;
							cnt++;
						}
					}
				}
				map[r][c] -= (map[r][c] / 5) * cnt;
				dustMap[r][c] += map[r][c];
			}
		}

		map = dustMap;
	}

	private static void airCleaning1() {
		// 상우하좌
		int[] dr = { -1, 0, 1, 0 };
		int[] dc = { 0, 1, 0, -1 };

		int r = airCleaner.get(0);
		int c = 0;
		int sr = r + 1;

		int turn = 0;
		while (turn < 4) {
			int nr = r + dr[turn % 4];
			int nc = c + dc[turn % 4];

			if (nr >= 0 && nr < sr && nc >= 0 && nc < C) {
				if (dustMap[r][c] == -1) {
					r = nr;
					c = nc;
					continue;
				}
				if (dustMap[nr][nc] == -1) {
					dustMap[r][c] = 0;
					break;
				} //
				else {
					dustMap[r][c] = dustMap[nr][nc];
				}
				r = nr;
				c = nc;
			} //
			else {
				turn++;
			}
		}
	}

	private static void airCleaning2() {
		// 하우상좌
		int[] dr = { 1, 0, -1, 0 };
		int[] dc = { 0, 1, 0, -1 };

		int r = airCleaner.get(1);
		int c = 0;
		int sr = r;

		int turn = 0;
		while (turn < 4) {
			int nr = r + dr[turn % 4];
			int nc = c + dc[turn % 4];

			if (nr >= sr && nr < R && nc >= 0 && nc < C) {
				int tmp = dustMap[nr][nc];
				if (dustMap[r][c] == -1) {
					r = nr;
					c = nc;
					continue;
				}
				if (dustMap[nr][nc] == -1) {
					dustMap[r][c] = 0;
					break;
				} //
				else {
					dustMap[r][c] = tmp;
				}
				r = nr;
				c = nc;
			} //
			else {
				turn++;
			}
		}
	}

	private static boolean isChecked(int r, int c) {
		if (r >= 0 && r < R && c >= 0 && c < C) {
			return true;
		}
		return false;
	}

	private static int getDustSum() {
		int sum = 0;

		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				sum += dustMap[i][j];
			}
		}

		return sum;
	}

	private static void print() {
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				System.out.print(dustMap[i][j] + " ");
			}
			System.out.println();
		}
	}
}