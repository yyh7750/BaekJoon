import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	private static int[][] arr;
	private static int[][] copy;
	private static int N, M, R;

	private static int n;
	private static int m;
	private static int[][] one;
	private static int[][] two;
	private static int[][] three;
	private static int[][] four;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());

		arr = new int[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		st = new StringTokenizer(br.readLine());
		while (st.hasMoreTokens()) {
			int cmd = Integer.parseInt(st.nextToken());

			switch (cmd) {
			case 1:
				lotate1();
				break;
			case 2:
				lotate2();
				break;
			case 3:
				lotate3();
				break;
			case 4:
				lotate4();
				break;
			case 5:
				lotate5();
				break;
			case 6:
				lotate6();
				break;
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				sb.append(arr[i][j]).append(" ");
			}
			sb.append("\n");
		}

		br.close();
		System.out.println(sb);
	}

	// 1. 상하반전
	private static void lotate1() {
		copy = new int[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				copy[N - i - 1][j] = arr[i][j];
			}
		}

		arr = copy;
	}

	// 2. 좌우반전
	private static void lotate2() {
		copy = new int[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				copy[i][M - j - 1] = arr[i][j];
			}
		}

		arr = copy;
	}

	// 3. 오른쪽으로 90도 회전
	private static void lotate3() {
		copy = new int[M][N];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				copy[j][N - i - 1] = arr[i][j];
			}
		}

		arr = copy;
		int temp = N;
		N = M;
		M = temp;
	}

	// 4. 왼쪽으로 90도 회전
	private static void lotate4() {
		copy = new int[M][N];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				copy[M - j - 1][i] = arr[i][j];
			}
		}

		arr = copy;
		int temp = N;
		N = M;
		M = temp;
	}

	// 4번까지 진행 후 4그룹으로 분류
	// 1 2
	// 4 3

	// 5.
	// 1번그룹 -> 2번그룹으로
	// 2번그룹 -> 3번그룹으로
	// 3번그룹 -> 4번그룹으로
	// 4번그룹 -> 1번그룹으로
	private static void lotate5() {
		makeHalfArr();

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (i < n && j < m) {
					arr[i][j] = four[i][j];
				} //
				else if (i < n && j >= m) {
					arr[i][j] = one[i][j - m];
				} //
				else if (i >= n && j < m) {
					arr[i][j] = three[i - n][j];
				} //
				else if (i >= n && j >= m) {
					arr[i][j] = two[i - n][j - m];
				}
			}
		}
	}

	// 6.
	// 1번그룹 -> 4번그룹으로
	// 2번그룹 -> 1번그룹으로
	// 3번그룹 -> 2번그룹으로
	// 4번그룹 -> 3번그룹으로
	private static void lotate6() {
		makeHalfArr();

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (i < n && j < m) {
					arr[i][j] = two[i][j];
				} //
				else if (i < n && j >= m) {
					arr[i][j] = three[i][j - m];
				} //
				else if (i >= n && j < m) {
					arr[i][j] = one[i - n][j];
				} //
				else if (i >= n && j >= m) {
					arr[i][j] = four[i - n][j - m];
				}
			}
		}
	}

	private static void makeHalfArr() {
		n = N / 2;
		m = M / 2;

		one = new int[n][m];
		two = new int[n][m];
		three = new int[n][m];
		four = new int[n][m];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (i < n && j < m) {
					one[i][j] = arr[i][j];
				} //
				else if (i < n && j >= m) {
					two[i][j - m] = arr[i][j];
				} //
				else if (i >= n && j >= m) {
					three[i - n][j - m] = arr[i][j];
				} //
				else if (i >= n && j < m) {
					four[i - n][j] = arr[i][j];
				}
			}
		}
	}
}