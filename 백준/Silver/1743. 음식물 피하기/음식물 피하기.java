import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static class Node {
		int r;
		int c;

		public Node(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}
	}

	private static int N, M, K, cnt, result;
	private static int[][] map;
	private static boolean[][] visited;
	private static int[] dr = { -1, 1, 0, 0 };
	private static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		visited = new boolean[N][M];

		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			map[x - 1][y - 1] = 1;
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (!visited[i][j] && map[i][j] == 1) {
					bfs(i, j);
					result = Math.max(result, cnt);
				}
			}
		}
		
		br.close();
		System.out.println(result);
	}

	private static void bfs(int r, int c) {
		visited[r][c] = true;
		cnt = 1;

		Queue<Node> Q = new LinkedList<>();
		Q.offer(new Node(r, c));

		while (!Q.isEmpty()) {
			Node node = Q.poll();

			for (int d = 0; d < 4; d++) {
				int nr = node.r + dr[d];
				int nc = node.c + dc[d];
				
				if(isChecked(nr, nc) && !visited[nr][nc] && map[nr][nc] == 1) {
					Q.offer(new Node(nr, nc));
					visited[nr][nc] = true;
					cnt++;
				}
			}
		}
	}

	private static boolean isChecked(int r, int c) {
		if (r >= 0 && r < N && c >= 0 && c < M) {
			return true;
		}
		return false;
	}
}