import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @packageName : 단계별_문제풀이.너비우선정렬
 * @description : n*m 행렬에서 벽을 한번만 부술 수 있는 경우 끝지점에 도달하는 최솟값을 구하는 프로그램. bfs 활용
 * @author : Younghun Yu
 * @date : 2022.05.16
 * ===========================================================
 *     DATE      AUTHOR      NOTE
 * -----------------------------------------------------------
 * 2022.05.16  Younghun Yu  최초 생성
 */
public class Main {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		// 도착 지점이 시작 지점과 같을 경우의 처리. (1x1 행렬)
		if (n - 1 == 0 && m - 1 == 0) {
			sb.append(1);
		} //
		else {
			// 좌우
			int[] dx = { -1, 1, 0, 0 };
			// 상하
			int[] dy = { 0, 0, -1, 1 };

			// n*m 크기의 맵 배열
			char[][] map = new char[n][m];
			// 거리 측정 배열
			int[][] dist = new int[n][m];
			// 벽을 부순 여부에 따른 방문 기록 배열
			boolean[][][] visited = new boolean[2][n][m];

			Queue<int[]> queue = new LinkedList<>();

			// map 입력
			for (int i = 0; i < n; i++) {
				String s = br.readLine();
				for (int j = 0; j < m; j++) {
					map[i][j] = s.charAt(j);
				}
			}

			// 시작점 : x, y, 벽 부쉈는지 여부
			queue.offer(new int[] { 0, 0, 0 });

			loop : while (!queue.isEmpty()) {
				// 현재 위치
				int[] curLocation = queue.poll();

				// 상, 하, 좌, 우 검사
				for (int i = 0; i < 4; i++) {
					int nx = curLocation[0] + dx[i];
					int ny = curLocation[1] + dy[i];

					if (nx < 0 || nx >= n || ny < 0 || ny >= m) {
						continue;
					}

					// 다음 칸에 벽이 있을 때
					if (map[nx][ny] == '1') {
						// 벽을 부순적이 있는지 체크, 벽을 방문한적이 있는지 체크
						if (curLocation[2] == 0 && !visited[1][nx][ny]) {
							visited[curLocation[2]][nx][ny] = true; // 방문 처리
							dist[nx][ny] = dist[curLocation[0]][curLocation[1]] + 1; // 거리 측정
							queue.offer(new int[] { nx, ny, 1 }); // 다시 큐에 넣어줘서 BFS!
						}
					}
					// 벽이 아닐 경우
					else {
						// 방문하지 않은 경우
						if (!visited[curLocation[2]][nx][ny]) {
							// 방문 처리
							visited[curLocation[2]][nx][ny] = true;
							// 거리 측정
							dist[nx][ny] = dist[curLocation[0]][curLocation[1]] + 1;
							// bfs 반복을 위한 큐에 삽입
							queue.offer(new int[] { nx, ny, curLocation[2] });
						}
					}
					
					// 도착
					if (nx == n - 1 && ny == m - 1) {
						sb.append(dist[nx][ny] + 1);
                        break loop;
					}
				}
			}
		}
        br.close();

		// 도착하지 못했을 경우
		if (sb.length() == 0) {
			sb.append(-1);
		}
			
		System.out.println(sb);
	}
}