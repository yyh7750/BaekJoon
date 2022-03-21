import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	private static int[][] graph = null;
	private static boolean[][] visited = null;

	public static void main(String[] args) {

		BufferedReader br = null;
		StringTokenizer st = null;

		// 테스트 케이스 개수
		int t = 0;

		try {

			br = new BufferedReader(new InputStreamReader(System.in));
			t = Integer.parseInt(br.readLine());

			for (int c = 0; c < t; c++) {
				// 결과값
				int result = 0;

				st = new StringTokenizer(br.readLine());
				int m = Integer.parseInt(st.nextToken());
				int n = Integer.parseInt(st.nextToken());
				int k = Integer.parseInt(st.nextToken());

				graph = new int[m][n];
				visited = new boolean[m][n];

				// 배추가 심어진 위치
				for (int i = 0; i < k; i++) {
					st = new StringTokenizer(br.readLine());
					graph[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())] = 1;
				}

				for (int i = 0; i < m; i++) {
					for (int j = 0; j < n; j++) {
						// 배추가 있으며 방문하지 않은 곳이면 dfs 탐색
						if (graph[i][j] == 1 && !visited[i][j]) {
							dfs(i, j);
							result++;
						}
					}
				}
				
				System.out.println(result);
			}
		} //
		catch (IOException ioException) {
			System.out.println("입력 오류. 다시 실행해주세요.");
		} //
		catch (NumberFormatException numberException) {
			System.out.println("숫자를 입력해주세요. 다시 실행해주세요.");
		}
	}

	private static void dfs(int x, int y) {
		// 상하
		int[] dx = { -1, 1, 0, 0 };
		// 좌우
		int[] dy = { 0, 0, -1, 1 };

		visited[x][y] = true;

		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];

			if (nx >= 0 && ny >= 0 && nx < graph.length && ny < graph[0].length) {
				if (graph[nx][ny] == 1 && !visited[nx][ny]) {
					dfs(nx, ny);
				}
			}
		}
	}
}