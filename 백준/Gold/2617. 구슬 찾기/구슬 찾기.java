import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	private static int N, M, result;
	private static int[][] adjArr;
	private static int INF = 5000;

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
		}

		floydWashall();
		getResult();
		
		br.close();
		System.out.println(result);
	}

	/**
	 * Description : 모든 정점에서 다른 모든 정점으로의 최단거리를 구하는 알고리즘.
	 * 
	 * @return void
	 *
	 * @date 2022. 9. 14.
	 * @author 유영훈
	 */
	private static void floydWashall() {
		for (int k = 1; k <= N; k++) {
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					adjArr[i][j] = Math.min(adjArr[i][j], adjArr[k][j] + adjArr[i][k]);
				}
			}
		}
	}

	/**
	 * Description : 플로이드워셜로 간선 비용을 구했을 때 노드의 중간 값보다 크거나 작은 값을 찾는 메소드
	 * 
	 * @return void
	 *
	 * @date 2022. 9. 14.
	 * @author 유영훈
	 */
	private static void getResult() {
		int[] lowArr = new int[N + 1];
		int[] highArr = new int[N + 1];

		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if (adjArr[i][j] < INF && adjArr[i][j] > 0) {
					lowArr[i]++;
					highArr[j]++;
				}
			}
		}

		for (int i = 1; i <= N; i++) {
			if (lowArr[i] >= N / 2 + 1) {
				result++;
			}
			if (highArr[i] >= N / 2 + 1) {
				result++;
			}
		}
	}
}