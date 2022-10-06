import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @packageName : a형보충
 * @Description : 1-상하좌우, 2-상하, 3-좌우, 4-상우, 5-하우, 6-하좌, 7-상좌
 * 
 * @date 2022. 10. 6.
 * @author 유영훈
 */
public class Solution {

	static class Loc {
		int r;
		int c;
		int l;

		public Loc(int r, int c, int l) {
			super();
			this.r = r;
			this.c = c;
			this.l = l;
		}
	}

	private static int N, M, R, C, L, ans;
	private static int[][] map;
	private static boolean[][] visited;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();

		for (int tc = 1; tc <= T; tc++) {
			sb.append("#").append(tc).append(" ");
			StringTokenizer st = new StringTokenizer(br.readLine());

			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());

			map = new int[N][M];
			visited = new boolean[N][M];

			// map input
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < M; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			ans = 1;
			bfs();
			
			sb.append(ans).append("\n");
		}
		
		br.close();
		System.out.println(sb);
	}

	/**
	 * @methodName : bfs
	 * @Description : 탈주범이 다녀간 공간을 세기 위한 bfs 메소드
	 * 
	 * @return void
	 *
	 * @date 2022. 10. 6.
	 * @author 유영훈
	 */
	private static void bfs() {
		Queue<Loc> Q = new LinkedList<>();
		Q.offer(new Loc(R, C, 1));
		visited[R][C] = true;

		while (!Q.isEmpty()) {
			Loc cur = Q.poll();

			int tunnel = map[cur.r][cur.c];
			for (int[] nTunnel : findDirection(tunnel)) {
				int nr = cur.r + nTunnel[0];
				int nc = cur.c + nTunnel[1];

				if (isChecked(nr, nc) && !visited[nr][nc] && map[nr][nc] != 0) {
					if (L <= cur.l) {
						continue;
					}

					// 다음 터널이 현재 터널과 연결되어 있는지 확인해야 한다.
					boolean flag = checkNextTunnel(nr, nc, findDirection(map[nr][nc]), cur);

					if (flag) {
						visited[nr][nc] = true;
						Q.offer(new Loc(nr, nc, cur.l + 1));
						ans++;
					}
				}
			}
		}
	}

	/**
	 * @methodName : checkNextTunnel
	 * @Description : 현재 터널과 다음 터널의 방향이 이어져있는지 확인하기 위한 메소드
	 * 
	 * @param r : 다음 터널의 좌표 r
	 * @param c : 다음 터널의 좌표 c
	 * @param nextTunnel : 다음 터널의 방향으로 이루어진 배열
	 * @param cur : 현재 터널 객체
	 * @return
	 * @return boolean : true-이어져 있다. false-아니다.
	 *
	 * @date 2022. 10. 6.
	 * @author 유영훈
	 */
	private static boolean checkNextTunnel(int r, int c, int[][] nextTunnel, Loc cur) {
		// 다음 터널이 가지고 있는 방향 중 한 방향이라도 현재 터널로 향한다면 이어져 있다.
		for (int[] nTunnel : nextTunnel) {
			int nr = r + nTunnel[0];
			int nc = c + nTunnel[1];

			if (nr == cur.r && nc == cur.c) {
				return true;
			}
		}

		return false;
	}

	/**
	 * @methodName : findDirection
	 * @Description : 터널의 번호에 따른 방향 처리를 위한 메소드
	 * 
	 * @param tunnel : 터널 번호
	 * @return
	 * @return int[][] : 델타 탐색을 위한 배열
	 *
	 * @date 2022. 10. 6.
	 * @author 유영훈
	 */
	private static int[][] findDirection(int tunnel) {
		switch (tunnel) {
		case 1:
			return new int[][] { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } }; // 상하좌우
		case 2:
			return new int[][] { { 1, 0 }, { -1, 0 } }; // 상하
		case 3:
			return new int[][] { { 0, 1 }, { 0, -1 } }; // 좌우
		case 4:
			return new int[][] { { -1, 0 }, { 0, 1 } }; // 상우
		case 5:
			return new int[][] { { 1, 0 }, { 0, 1 } }; // 하우
		case 6:
			return new int[][] { { 1, 0 }, { 0, -1 } }; // 하좌
		case 7:
			return new int[][] { { -1, 0 }, { 0, -1 } }; // 상좌
		}

		return new int[][] {}; // 0일때
	}

	private static boolean isChecked(int nr, int nc) {
		if (nr >= 0 && nr < N && nc >= 0 && nc < M) {
			return true;
		}
		return false;
	}
}