import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	private static int[] price;
	private static int[] month;
	private static int Ans;

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		for (int tc = 1; tc <= T; tc++) {
			sb.append("#").append(tc).append(" ");

			price = new int[4];
			month = new int[12];

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 4; i++) {
				price[i] = Integer.parseInt(st.nextToken());
			}

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 12; i++) {
				month[i] = Integer.parseInt(st.nextToken());
			}

			Ans = Integer.MAX_VALUE;
			dfs(0, 0);
			sb.append(Ans).append("\n");
		}

		br.close();
		System.out.println(sb);
	}

	private static void dfs(int idx, int sum) {
		if (idx >= 12) {
			// 1년 요금제 비교
			sum = Math.min(sum, price[3]);
			Ans = Math.min(Ans, sum);
			return;
		}

		if (sum >= Ans) {
			return;
		}

		// 1일 요금제
		dfs(idx + 1, sum + price[0] * month[idx]);
		// 1달 요금제
		dfs(idx + 1, sum + price[1]);
		// 3달 요금제
		dfs(idx + 3, sum + price[2]);
	}
}