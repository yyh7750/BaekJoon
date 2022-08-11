import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	private static int N, L, max;
	private static int[] jumsu;
	private static int[] cal;
	private static boolean[] checked;

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			sb.append("#").append(tc).append(" ");
			st = new StringTokenizer(br.readLine());

			N = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());

			jumsu = new int[N];
			cal = new int[N];
			checked = new boolean[N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				jumsu[i] = Integer.parseInt(st.nextToken());
				cal[i] = Integer.parseInt(st.nextToken());
			}

			max = 0;
			
			recursion(0);
			sb.append(max).append("\n");
		}

		br.close();
		System.out.println(sb);
	}

	// idx = 현재위치, jSum = 점수합계, cSum = 칼로리합계
	private static void recursion(int idx) {
		int jSum = 0;
		int cSum = 0;
		
		if (idx == N) {
			for (int i = 0; i < checked.length; i++) {
				if (checked[i]) {
					if(cSum + cal[i] > L) {
						return;
					}
					jSum += jumsu[i];
					cSum += cal[i];
				}
			}
			max = Math.max(max, jSum);
			return;
		}

		// 담는 경우
		checked[idx] = true;
		recursion(idx + 1);

		// 안담는 경우
		checked[idx] = false;
		recursion(idx + 1);
	}
}