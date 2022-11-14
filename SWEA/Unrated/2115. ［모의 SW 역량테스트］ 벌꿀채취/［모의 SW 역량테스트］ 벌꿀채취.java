import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	private static int N, M, C, ans, maxCost;
	private static int firstCost;
	private static int secondCost;
	private static int[][] map;

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		for (int tc = 1; tc <= T; tc++) {
			sb.append("#").append(tc).append(" ");

			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());

			map = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			ans = 0;
			firstCost = 0;
			secondCost = 0;

			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N - M + 1; c++) {
//					System.out.println("1 loc : " + r + " " + c);
					maxCost = 0;
					getSelectLocValue(r, c, 0, 0, 0);
					firstCost = maxCost;
//					System.out.println("1 cost : " + firstCost);
					combination(r, c + M, 0);
//					System.out.println("================");
				}
			}

			sb.append(ans).append("\n");
		}

		br.close();
		System.out.println(sb);
	}

	/**
	 * @methodName : combination
	 * @Description : 벌꿀1의 모든 경우의 수를 탐색하는 메소드 (완전탐색. 조합)
	 * 
	 * @param r
	 * @param c
	 * @param idx
	 * @return void
	 *
	 * @date 2022. 11. 13.
	 * @author 유영훈
	 */
	private static void combination(int r, int c, int idx) {
		if (idx == 1) {
//			System.out.println("2 loc : " + r + " " + c);
			maxCost = 0;
			getSelectLocValue(r, c, 0, 0, 0);
			secondCost = maxCost;
//			System.out.println("2 cost : " + secondCost);

			ans = Math.max(ans, firstCost + secondCost);
//			System.out.println("ans : " + ans);
//			System.out.println("-----------------------");
			return;
		}

		for (int row = r; row < N; row++) {
			for (int col = c; col < N - M + 1; col++) {
				combination(row, col, idx + 1);
			}
			c = 0;
		}
	}

	/**
	 * 
	 * @methodName : getSelectLocValue
	 * @Description : 일꾼이 일하는 지역에서 채취한 꿀의 총 이득을 반환한다.
	 * 
	 * @param r    : 일꾼이 벌꿀 채취하는 위치 r
	 * @param c    : 일꾼이 벌꿀 채취하는 위치 c
	 * @param cnt  : M개 칸만큼 채취하는지 확인하는 변수
	 * @param sum  : 채취한 꿀의 합. 더한 값이 C를 넘으면 안된다.
	 * @param cost : 최종 비용
	 *
	 * @return void
	 *
	 * @date 2022. 11. 13.
	 * @author 유영훈
	 */
	private static void getSelectLocValue(int r, int c, int cnt, int sum, int cost) {
		if (sum > C) {
			return;
		}
		if (r >= N || c >= N) {
			if (sum <= C) {
				maxCost = Math.max(maxCost, cost);
			}
			return;
		}
		if (cnt == M) {
			maxCost = Math.max(maxCost, cost);
			return;
		}

		getSelectLocValue(r, c + 1, cnt + 1, sum + map[r][c], cost + (map[r][c] * map[r][c]));
		getSelectLocValue(r, c + 1, cnt + 1, sum, cost);
	}
}