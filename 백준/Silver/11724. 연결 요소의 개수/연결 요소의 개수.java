import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	// 인접행렬 배열
	private static int[][] graph = null;
	// 방문체크 배열
	private static boolean[] visited = null;

	public static void main(String[] args) {

		BufferedReader br = null;
		StringTokenizer st = null;
		int n = 0; // 정점
		int m = 0; // 간선
		int result = 0; // 연결요소의 개수

		try {

			br = new BufferedReader(new InputStreamReader(System.in));
			st = new StringTokenizer(br.readLine());

			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());

			graph = new int[n + 1][n + 1];
			visited = new boolean[n + 1];

			// 인접행렬 입력
			for (int i = 0; i < m; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());

				graph[a][b] = 1;
				graph[b][a] = 1;
			}

			// 문제에서 주어진 범위가 1부터
			for (int i = 1; i <= n; i++) {
				if (!visited[i]) {
					result++;
					dfs(i);
				}
			}
			
			System.out.println(result);

		} //
		catch (IOException ioException) {
			System.out.println("입력 오류. 다시 실행해주세요.");
		} //
		catch (NumberFormatException numberException) {
			System.out.println("숫자를 입력해주세요. 다시 실행해주세요.");
		}
	}

	private static void dfs(int index) {
		visited[index] = true;

		// 간선 연결과 방문체크를 확인하면서 dfs 반복
		for (int i = 1; i < graph.length; i++) {
			if (graph[index][i] == 1 && !visited[i]) {
				dfs(i);
			}
		}
	}
}