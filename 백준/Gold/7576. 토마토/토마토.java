import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	private static int[][] map = null;

	public static void main(String[] args) {

		BufferedReader br = null;
		StringTokenizer st = null;

		int n = 0;
		int m = 0;

		try {

			br = new BufferedReader(new InputStreamReader(System.in));
			st = new StringTokenizer(br.readLine());

			// 토마토 밭 크기 입력
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());

			map = new int[m][n];

			// 토마토 밭 입력
			for (int i = 0; i < m; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < n; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			int result = bfs();

			System.out.println(result);

		} //
		catch (IOException ioException) {
			System.out.println("입력 오류. 다시 실행해주세요.");
		} //
		catch (NumberFormatException numberException) {
			System.out.println("숫자를 입력해주세요. 다시 실행해주세요.");
		}
	}

	private static int bfs() {
		// 상하
		int[] dx = { -1, 1, 0, 0 };
		// 좌우
		int[] dy = { 0, 0, -1, 1 };

		Queue<int[]> queue = new LinkedList<>();

		// 익은 토마토의 위치를 큐에 담아준다.
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				if (map[i][j] == 1) {
					queue.add(new int[] { i, j });
				}
			}
		}

		// 토마토 익을 때까지의 날짜 구하기 위한 연산
		while (!queue.isEmpty()) {
			int[] cLocation = queue.poll();

			for (int i = 0; i < 4; i++) {
				int nx = cLocation[0] + dx[i];
				int ny = cLocation[1] + dy[i];

				if (nx >= 0 && ny >= 0 && nx < map.length && ny < map[0].length && map[nx][ny] == 0) {
					map[nx][ny] = map[cLocation[0]][cLocation[1]] + 1;
					queue.add(new int[] { nx, ny });
				}
			}
		}

		int result = 0;

		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				// 0이 있는 경우 토마토가 익지 않은 것이기에 -1 출력후 종료
				if (map[i][j] == 0) {
					return -1;
				}
				// 최댓값 계산
				result = Math.max(result, map[i][j]);
			}
		}

		// 기다린 날짜는 최댓값에서 -1
		return result - 1;
	}
}