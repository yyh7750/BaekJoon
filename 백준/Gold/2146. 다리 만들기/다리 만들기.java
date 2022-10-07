import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static class Loc {
		int r;
		int c;
		int cnt;
		int area;

		public Loc(int r, int c, int cnt, int area) {
			super();
			this.r = r;
			this.c = c;
			this.cnt = cnt;
			this.area = area;
		}
	}

	private static int N, ans = Integer.MAX_VALUE;
	private static int[] dr = { 0, 1, 0, -1 };
	private static int[] dc = { 1, 0, -1, 0 };
	private static int[][] map;
	private static boolean[][] visited;
	private static List<Loc> edgeList;

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st = null;

		map = new int[N][N];
		visited = new boolean[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int idx = 1;
		edgeList = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (!visited[i][j] && map[i][j] == 1) {
					findArea(i, j, idx);
					idx++;
				}
			}
		}

		for (Loc edge : edgeList) {
			visited = new boolean[N][N];
			bfs(edge);
		}

		br.close();
		System.out.println(ans);
	}

	/**
	 * @methodName : bfs
	 * @Description : 대륙을 잇는 다리의 최소 길이를 구하는 bfs 메소드
	 * 
	 * @return void
	 *
	 * @date 2022. 10. 7.
	 * @author 유영훈
	 * @param edge
	 */
	private static void bfs(Loc edge) {
		Queue<Loc> Q = new LinkedList<>();
		// 가장자리 큐에 추가
		Q.add(edge);
		visited[edge.r][edge.c] = true;

		while (!Q.isEmpty()) {
			Loc cur = Q.poll();

			for (int d = 0; d < 4; d++) {
				int nr = cur.r + dr[d];
				int nc = cur.c + dc[d];

				if (isChecked(nr, nc) && map[nr][nc] == 0 && !visited[nr][nc]) {
					visited[nr][nc] = true;
					Q.offer(new Loc(nr, nc, cur.cnt + 1, cur.area));
				} //
				else if (isChecked(nr, nc) && cur.area != map[nr][nc] && !visited[nr][nc]) {
					ans = Math.min(ans, cur.cnt);
				}
			}
		}
	}

	/**
	 * @methodName : findArea
	 * @Description : 떨어진 대륙들을 구분시키고 가장자리를 구하기 위한 bfs 메소드
	 *
	 * @param r
	 * @param c
	 * @param area : 대륙을 구분하기 위한 대륙 번호 ex) 1대륙, 2대륙, 3대륙, ...
	 *
	 * @return void
	 *
	 * @date 2022. 10. 7.
	 * @author 유영훈
	 */
	private static void findArea(int r, int c, int area) {
		Queue<Loc> Q = new LinkedList<>();
		Q.offer(new Loc(r, c, 0, 0));
		visited[r][c] = true;
		map[r][c] = area;

		while (!Q.isEmpty()) {
			Loc cur = Q.poll();

			boolean flag = false;

			for (int d = 0; d < 4; d++) {
				int nr = cur.r + dr[d];
				int nc = cur.c + dc[d];

				if (isChecked(nr, nc) && !visited[nr][nc] && map[nr][nc] == 1) {
					visited[nr][nc] = true;
					Q.offer(new Loc(nr, nc, 0, area));
					map[nr][nc] = area;
				}

				// 가장자리 찾기
				if (isChecked(nr, nc) && map[nr][nc] == 0) {
					flag = true;
				}
			}

			if (flag) {
				edgeList.add(new Loc(cur.r, cur.c, 0, area));
			}
		}
	}

	private static boolean isChecked(int nr, int nc) {
		if (nr >= 0 && nr < N && nc >= 0 && nc < N) {
			return true;
		}
		return false;
	}
}