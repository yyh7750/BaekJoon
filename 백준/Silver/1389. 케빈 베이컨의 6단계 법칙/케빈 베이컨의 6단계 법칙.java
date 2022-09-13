import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	private static int N, M, min, result;
	private static final int INF = 5001;
	private static int[][] adjArr;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		adjArr = new int[N + 1][N + 1];

		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if (i == j) {
					continue;
				}
				adjArr[i][j] = INF;
			}
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			adjArr[from][to] = 1;
			adjArr[to][from] = 1;
		}

		min = INF;
		solution();

		br.close();
		System.out.println(result);
	}

	/**
	 * Description : 플로이드 워셜 알고리즘을 이용한 문제해결
	 * 
	 * @return void
	 *
	 * @date 2022. 9. 13.
	 * @author 유영훈
	 */
	private static void solution() {
		for (int k = 1; k <= N; k++) {
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					// x에서 y로 가는 경우 vs 현재 노드에서 x로 가는경우 + y에서 현재 노드로 가는 경우를 비교
					if (adjArr[i][j] > adjArr[i][k] + adjArr[k][j]) {
						adjArr[i][j] = adjArr[i][k] + adjArr[k][j];
					}
				}
			}
		}

		for (int i = 1; i <= N; i++) {
			int sum = 0;
			// 기준 노드에서 모든 노드를 방문할때의 비용합 sum
			for (int j = 1; j <= N; j++) {
				sum += adjArr[i][j];
			}

			if (min > sum) {
				min = sum;
				result = i;
			}
		}
	}
}