import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	private static StringBuilder sb = new StringBuilder();
	private static int N;

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		solution(0, 0);

		br.close();
		System.out.println(sb);
	}

	private static void solution(int cnt, int prime) {
		if (cnt == N) {
			// n자리까지 채운 후
            // n - 1, ..., 3, 2, 1자리로 돌아가 0~9 반복
			sb.append(prime).append("\n");
			return;
		}

		// 0~9까지 모든 수 탐색
		for (int i = 0; i <= 9; i++) {
			// 1, 2, 3, ..., n 자릿수가 소수여야 하므로 소수 * 10 + 0~9
			int temp = i + (prime * 10);
			if (temp > 1) {
				// 소수 검증
				if (isPrime(temp)) {
					solution(cnt + 1, temp);
				}
			}
		}
	}

	private static boolean isPrime(int n) {
		for (int i = 2; i <= Math.sqrt(n); i++) {
			if (n % i == 0) {
				return false;
			}
		}

		return true;
	}
}