import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	private static int white;
	private static int blue;

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int N = Integer.parseInt(br.readLine());

		int[][] arr = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		divide(arr, 0, 0, N);

		br.close();
		System.out.printf("%d\n%d", white, blue);
	}

	private static void divide(int[][] arr, int r, int c, int n) {
		for (int i = r; i < r + n; i++) {
			for (int j = c; j < c + n; j++) {
				if (arr[r][c] != arr[i][j]) {
					divide(arr, r, c, n / 2);
					divide(arr, r + (n / 2), c, n / 2);
					divide(arr, r, c + (n / 2), n / 2);
					divide(arr, r + (n / 2), c + (n / 2), n / 2);
					return;
				}
			}
		}

		if (arr[r][c] == 1) {
			blue++;
		} //
		else {
			white++;
		}
	}
}