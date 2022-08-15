import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	private static int N, n;
	private static int result;
	private static int[][] map;
	private static boolean[] visited;

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			sb.append("#").append(tc).append(" ");

			N = Integer.parseInt(br.readLine());
			n = N / 2;	// 뽑아낼 개수 : A음식, B음식 두 가지 경우이므로 재료개수 / 2
			map = new int[N][N];
			visited = new boolean[N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
//			printMap();

			result = Integer.MAX_VALUE;
			combination(0, 0);
			sb.append(result).append("\n");
		}

		br.close();
		System.out.println(sb);
	}

	private static void combination(int idx, int k) {
		if (idx == N) {
			return;
		}
		if (k == n) {
//			printVisited();
			// 계산
			getMin();
			return;
		}

		visited[idx] = true;
		combination(idx + 1, k + 1);
		visited[idx] = false;
		combination(idx + 1, k);
	}

	private static void getMin() {
		int A = 0;
		int B = 0;

		for (int i = 0; i < N; i++) {
			if (visited[i]) {
				for (int j = 0; j < N; j++) {
					if (visited[j]) {
						A += map[i][j];
					}
				}
			} //
			else {
				for (int j = 0; j < N; j++) {
					if (!visited[j]) {
						B += map[i][j];
					}
				}
			}
		}

		result = Math.min(result, Math.abs(A - B));
	}

//	private static void printMap() {
//		for (int i = 0; i < N; i++) {
//			for (int j = 0; j < N; j++) {
//				System.out.print(map[i][j] + " ");
//			}
//			System.out.println();
//		}
//		System.out.println("======================================");
//	}
//
//	private static void printVisited() {
//		for (int i = 0; i < N; i++) {
//			System.out.print(visited[i] + " ");
//		}
//		System.out.println("======================================");
//	}
}