import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) {

		BufferedReader br = null;
		StringTokenizer st = null;
		int n = 0;
		int m = 0;
		int[] arr = null;

		try {

			br = new BufferedReader(new InputStreamReader(System.in));
			st = new StringTokenizer(br.readLine());

			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());

			arr = new int[n];

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < n; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}

			System.out.println(blackJackSolve(n, m, arr));

		} //
		catch (IOException ioException) {
			System.out.println("입력 오류");
		} //
		catch (NumberFormatException numException) {
			System.out.println("숫자를 입력하세요");
		}
	}

	private static int blackJackSolve(int n, int m, int[] arr) {

		int result = 0;

		for (int i = 0; i < n - 2; i++) {
			if (arr[i] >= m) {
				continue;
			}
			//
			for (int j = i + 1; j < n - 1; j++) {
				if (arr[i] + arr[j] >= m) {
					continue;
				}
				//
				for (int k = j + 1; k < n; k++) {
					int sum = arr[i] + arr[j] + arr[k];

					if (sum < m && result < sum) {
						result = sum;
					} //
					else if (sum == m) {
						return sum;
					}
				}
			}
		}

		return result;
	}
}
