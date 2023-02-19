import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	private static boolean[][] chess = null;
	private static int min = Integer.MAX_VALUE;

	public static void main(String[] args) {

		BufferedReader br = null;
		StringTokenizer st = null;
		int n = 0, m = 0;

		try {

			br = new BufferedReader(new InputStreamReader(System.in));
			st = new StringTokenizer(br.readLine());

			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());

			chess = new boolean[n][m];

			for (int i = 0; i < n; i++) {
				String str = br.readLine();

				for (int j = 0; j < m; j++) {
					if (str.charAt(j) == 'W') {
						chess[i][j] = true;
					} //
					else {
						chess[i][j] = false;
					}
				}
			}

			for (int i = 0; i < n - 7; i++) {
				for (int j = 0; j < m - 7; j++) {
					solve(i, j);
				}
			}

			System.out.println(min);

		} //
		catch (IOException ioException) {
			System.out.println("입력 오류. 다시 실행해주세요.");
		} //
		catch (NumberFormatException numberException) {
			System.out.println("숫자를 입력해주세요. 다시 실행해주세요.");
		}
	}

	private static int solve(int x, int y) {
		int lastX = x + 8;
		int lastY = y + 8;
		int count = 0;

		boolean p = chess[x][y];

		for (int i = x; i < lastX; i++) {
			for (int j = y; j < lastY; j++) {
				// 색이 다르면 카운트 증가
				if (chess[i][j] != p) {
					count++;
				}

				// 다음칸 설정
				p = !p;
			}
			p = !p;
		}

		count = Math.min(count, 64 - count);

		min = Math.min(min, count);

		return min;
	}
}