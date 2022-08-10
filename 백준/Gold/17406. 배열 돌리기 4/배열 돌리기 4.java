import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	private static int[][] arr;
	private static int[] permutation;
	private static int[][] subArr;
	private static boolean[] visited;
	private static boolean[][] checked;
	private static int[][] copy;
	// 우 하 좌 상
	private static int[] dr = { 0, 1, 0, -1 };
	private static int[] dc = { 1, 0, -1, 0 };
	private static int N, M, K, sr, sc;
	private static int min = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		arr = new int[N][M];
		permutation = new int[K];
		subArr = new int[K][3];
		visited = new boolean[K];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			subArr[i][0] = Integer.parseInt(st.nextToken());
			subArr[i][1] = Integer.parseInt(st.nextToken());
			subArr[i][2] = Integer.parseInt(st.nextToken());
		}
		permutation(0);
		
		br.close();
		System.out.println(min);
	}

	private static void permutation(int idx) {
		if (idx == K) {

			copy = new int[N][M];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					copy[i][j] = arr[i][j];
				}
			}

			for (int i = 0; i < K; i++) {
				int r = subArr[permutation[i]][0] - 1;
				int c = subArr[permutation[i]][1] - 1;
				int s = subArr[permutation[i]][2];
				sr = r - s;
				sc = c - s;
				checked = new boolean[N][M];
				rotation(sr, sc, s, copy[r - s][c - s], 0);
			}
			
			getMinValue(copy);
			return;
		}

		for (int i = 0; i < K; i++) {
			if (!visited[i]) {
				visited[i] = true;
				permutation[idx] = i;
				permutation(idx + 1);
				visited[i] = false;
			}
		}
	}

	private static void rotation(int r, int c, int s, int cur, int loop) {
		if (loop == s) {
			return;
		}

		int turn = 0;
		while (turn < 4) {
			int nr = r + dr[turn % 4];
			int nc = c + dc[turn % 4];

			if (isChecked(s * 2, nr, nc) && !checked[nr][nc]) {
				int tmp = copy[nr][nc];
				checked[nr][nc] = true;
				copy[nr][nc] = cur;
				cur = tmp;
				r = nr;
				c = nc;
			} //
			else {
				turn++;
			}
		}

		rotation(r + 1, c + 1, s, copy[r + 1][c + 1], loop + 1);
	}
	
	private static void getMinValue(int[][] copy) {
		for(int i = 0; i < copy.length; i++) {
            int sum = 0;
            for(int j = 0; j < copy[i].length; j++) {
                sum += copy[i][j];
            }
            min = Math.min(min, sum);
        }
	}

	private static boolean isChecked(int s, int r, int c) {
		if (r >= sr && r <= sr + s && c >= sc && c <= sc + s) {
			return true;
		}
		return false;
	}
}