import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	private static int N, M;
	private static final int MAX = 15000; // 30개 * 500g (추 개수, 추 1개 최대 무게)
	private static int[] w; // 주어지는 추를 담을 배열
	private static boolean[][] dp;

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(br.readLine());
		w = new int[N + 1];
		dp = new boolean[31][MAX + 1];

		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			w[i] = Integer.parseInt(st.nextToken());
		}

		dp(0, 0);

		M = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());

		for (int i = 0; i < M; i++) {
			int checkWeight = Integer.parseInt(st.nextToken());

			if (checkWeight > MAX) {
				sb.append("N");
			} //
			else {
				if (dp[N][checkWeight]) {
					sb.append("Y");
				} //
				else {
					sb.append("N");
				}
			}
			sb.append(" ");
		}

		br.close();
		System.out.println(sb.toString().trim());
	}

	private static void dp(int idx, int weight) {
		if (dp[idx][weight]) {
			return;
		}

		dp[idx][weight] = true;

		if (idx == N) {
			return;
		}

        dp(idx + 1, weight);
		dp(idx + 1, weight + w[idx + 1]);
		dp(idx + 1, Math.abs(weight - w[idx + 1]));
	}
}