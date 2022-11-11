import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	private static int N, B; // 점원 수, 선반 높이 제한
	private static int Ans;
	private static int[] clerk;

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;

		// 탑의 높이는 탑을 쌓는 해당 점원의 키와 같다.
		// 선반의 높이가 B 이상인 경우 탑 중에서 높이가 가장 낮은 탑을 알아낸다.
		// 만들 수 있는 B이상의 탑 높이와 B의 차이가 가장 작은 것을 출력하는 문제.
		// 부분집합을 이용한다.

		for (int tc = 1; tc <= T; tc++) {
			sb.append("#").append(tc).append(" ");

			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());

			clerk = new int[N]; // 점원들의 키를 담는 배열

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				clerk[i] = Integer.parseInt(st.nextToken());
			}

			Ans = Integer.MAX_VALUE;
			backtracking(0, 0);

			sb.append(Ans - B).append("\n");
		}

		br.close();
		System.out.println(sb);
	}

	private static void backtracking(int idx, int sum) {
		if (idx == N) {
			if (sum >= B) {
				Ans = Math.min(Ans, sum);
			}
			return;
		}
		if (sum > B) {
			// 계산
			Ans = Math.min(Ans, sum);
			return;
		}

		backtracking(idx + 1, sum + clerk[idx]);
		backtracking(idx + 1, sum);
	}
}