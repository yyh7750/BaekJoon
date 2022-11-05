import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	private static int N, M, max = Integer.MIN_VALUE;
	private static int[][] map;
	private static boolean[][] visited;
	private static int[] dr = { -1, 1, 0, 0 };
	private static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		visited = new boolean[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				// 모든 방향(회전)에 대해서 처리를 해주기 위한 방문 체크/해제
				visited[i][j] = true;
				dfs(i, j, 0, map[i][j]);
				visited[i][j] = false;
			}
		}

		br.close();
		System.out.println(max);
	}

	/**
	 * @methodName : dfs
	 * @Description : 방문 체크/해제를 이용해 모든 경우의 도형을 만들어 최댓값을 구하는 메소드
	 * 
	 * @param r
	 * @param c
	 * @param cnt : 도형을 만들기 위한 카운트 변수(도형의 크기 = 4)
	 * @param sum : 도형이 가지고 있는 숫자들의 합
	 * @return void
	 *
	 * @date 2022. 11. 5.
	 * @author 유영훈
	 */
	private static void dfs(int r, int c, int cnt, int sum) {
		if (cnt == 3) {
			// 도형 완성 결과값 갱신
			max = Math.max(max, sum);
			return;
		}

		for (int d = 0; d < 4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];

			if (isChecked(nr, nc) && !visited[nr][nc]) {
				// '凸' 모양일 때
				if (cnt == 1) {
					visited[nr][nc] = true;
					// 2번째 탐색일 경우 '凸'모양을 생각해줘야 한다.
					dfs(r, c, cnt + 1, sum + map[nr][nc]);
					visited[nr][nc] = false;
				}

				visited[nr][nc] = true;
				dfs(nr, nc, cnt + 1, sum + map[nr][nc]);
				visited[nr][nc] = false;
			}
		}
	}

	private static boolean isChecked(int nr, int nc) {
		if (nr >= 0 && nc >= 0 && nr < N && nc < M) {
			return true;
		}
		return false;
	}
}