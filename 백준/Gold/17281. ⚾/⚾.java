import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	private static int N, ans, score;
	private static int[][] action; // 각 선수가 이닝에서 얻는 결과
	private static int[] order; // 타순
	private static boolean[] sel;

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		N = Integer.parseInt(br.readLine());

		action = new int[N][9];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 9; j++) {
				action[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// 4번 타자의 순서는 정해져 있다.
		sel = new boolean[9];
		sel[3] = true;
		order = new int[9];
		order[3] = 0;

		// 1번째로 플레이하는 선수는 4번 타자로 고정이기 때문에 2번째 순서부터 시작
		permutation(1);

		br.close();
		System.out.println(ans);
	}

	private static void permutation(int idx) {
		if (idx == 9) {
			// 게임 시작
			playGame();
			return;
		}

		for (int i = 0; i < 9; i++) {
			if (sel[i]) {
				continue;
			}
			sel[i] = true;
			order[i] = idx;
			permutation(idx + 1);
			sel[i] = false;
		}
	}

	private static void playGame() {
		// 각 선수들의 이닝에서 얻는 결과와 타순을 받와 왔으므로
		// 그에 해당하는 점수를 도출한다.

		int hitter = 0;
		score = 0;
		
		for (int inning = 0; inning < N; inning++) {
			// 안타를 쳤을 경우 몇루에 선수들이 있는지 확인하기 위한 배열
			boolean[] base = new boolean[4];

			// 삼진아웃이 안되고 계속 점수를 낼 수 있는 경우를 고려한다.
			// 타자의 순서가 한 사이클을 돌면 초기화 해준다.
			// 인자는 처음 치는 타자의 순서인 0
			hitter = getHitterOrder(inning, hitter, base);
//			System.out.println(hitter);
		}

		ans = Math.max(ans, score);
	}

	private static int getHitterOrder(int inning, int hitter, boolean[] base) {
		// 아웃카운트 3개를 세기 위한 변수
		int out = 0;

		inning: while (true) {
			for (int orderIdx = hitter; orderIdx < 9; orderIdx++) {
				// 해당 순서 타자의 결과
				int hitResult = action[inning][order[orderIdx]];
				
				switch (hitResult) {
				case 0:
					out++;
					break;

				case 1:
					for (int j = 3; j >= 1; j--) {
						if (base[j]) {
							if (j == 3) {
								score++;
								base[j] = false;
								continue;
							}
							base[j] = false;
							base[j + 1] = true;
						}
					}
					base[1] = true;
					break;

				case 2:
					for (int j = 3; j >= 1; j--) {
						if (base[j]) {
							if (j >= 2) {
								score++;
								base[j] = false;
								continue;
							}
							base[j] = false;
							base[j + 2] = true;
						}
					}
					base[2] = true;
					break;

				case 3:
					for (int j = 3; j >= 1; j--) {
						if (base[j]) {
							score++;
							base[j] = false;
						}
					}
					base[3] = true;
					break;

				case 4:
					for (int j = 3; j >= 1; j--) {
						if (base[j]) {
							base[j] = false;
							score++;
						}
					}
					score++;
					break;
				}

				if (out == 3) {
					// 아웃 카운트 3개라면 다음 타자로 순서 바꾼 후 이닝 종료
					hitter = orderIdx + 1;
					if (hitter == 9) {
						hitter = 0;
					}
					break inning;
				}
			}
			// 계속 안타 칠 경우 타자 다시 초기화
			hitter = 0;
		}

		return hitter;
	}
}