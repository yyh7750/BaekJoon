import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Main {

	private static int[][] map;
	private static StringBuilder sb;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());

		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < N; j++) {
				map[i][j] = str.charAt(j) - '0';
			}
		}

		divide(N, 0, 0);

		br.close();
		System.out.println(sb);
	}

	public static void divide(int n, int r, int c) {
		// StringBuilder에 넣을지 판단할 변수
		boolean p = true;
		// 해당 범위까지 같은 숫자라면 true 아니면 false
		for (int i = r; i < r + n; i++) {
			for (int j = c; j < c + n; j++) {
				if (map[r][c] != map[i][j]) {
					p = false;
				}
			}
		}

		if (p) { // true일 경우 StringBuilder에 추가
			sb.append(map[r][c]);
			return;
		}

		int half = n / 2;

		// 분할
		sb.append('(');
		divide(half, r, c);
		divide(half, r, c + half);
		divide(half, r + half, c);
		divide(half, r + half, c + half);
		sb.append(')');
	}
}