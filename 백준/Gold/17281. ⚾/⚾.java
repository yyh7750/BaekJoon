import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	private static int N, Ans;
	private static int[][] hitters;
	private static int[] turn;
	private static boolean[] sel;

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st = null;

		hitters = new int[N + 1][10];
		sel = new boolean[10];
		turn = new int[10];

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= 9; j++) {
				hitters[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		sel[4] = true;
		turn[4] = 1;

		permutation(2);
		
		br.close();
		System.out.println(Ans);
	}

	private static void permutation(int n) {
		if (n == 10) {
			play();
			return;
		}

		for (int i = 1; i <= 9; i++) {
			if (sel[i]) {
				continue;
			}

			sel[i] = true;
			turn[i] = n;
			permutation(n + 1);
			sel[i] = false;
		}
	}

	private static void play() {
		int score = 0;
		int startHitter = 1;
		boolean[] base;

		for (int i = 1; i <= N; i++) {
			int out = 0;
			base = new boolean[4];

			loop: while (true) {
				for (int j = startHitter; j <= 9; j++) {
					int hitter = hitters[i][turn[j]];

					switch (hitter) {

					case 0:
						out++;
						break;

					case 1:
						for (int k = 3; k >= 1; k--) {
							if (base[k]) {
								// 3루 선수 득점
								if (k == 3) {
									score++;
									base[k] = false;
									continue;
								}
								// 1루씩 진루
								base[k + 1] = true;
								base[k] = false;
							}
						}
						// 홈 -> 1루
						base[1] = true;
						break;

					case 2:
						for (int k = 3; k >= 1; k--) {
							if (base[k]) {
								// 2루, 3루 득점
								if (k == 3 || k == 2) {
									score++;
									base[k] = false;
									continue;
								}
								// 1루 -> 3루
								base[k + 2] = true;
								base[k] = false;
							}
						}
						// 홈 -> 2루
						base[2] = true;
						break;

					case 3:
						for (int k = 3; k >= 1; k--) {
							// 1루부터 3루까지 전부 득점
							if (base[k]) {
								score++;
								base[k] = false;
							}
						}
						// 홈 -> 3루
						base[3] = true;
						break;

					case 4:
						for (int k = 3; k >= 1; k--) {
							// 1루부터 3루까지 전부 득점
							if (base[k]) {
								score++;
								base[k] = false;
							}
						}
						// 득점
						score++;
						break;
					}

					if (out == 3) {
						// 다음 타자로 순서 바꾸기
						startHitter = j + 1;

						if (startHitter == 10) {
							startHitter = 1;
						}

						break loop;
					}
				}

				startHitter = 1;
			}
		}

		Ans = Math.max(Ans, score);
	}
}