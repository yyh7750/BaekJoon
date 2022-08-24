import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	private static int[][] map;
	private static int M, N, cnt;
	private static int[] dr = { -1, 1, 0, 0 };
	private static int[] dc = { 0, 0, -1, 1 };
	private static List<int[]> tomato;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		tomato = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 1) {
					tomato.add(new int[] { i, j });
				}
			}
		}

		bfs();

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(map[i][j] == 0) {
					System.out.println(-1);
					return;
				}
			}
		}
		
		System.out.println(cnt - 1);
	}

	private static void bfs() {
		Queue<int[]> Q = new LinkedList<>();
		for (int i = 0; i < tomato.size(); i++) {
			Q.offer(new int[] { tomato.get(i)[0], tomato.get(i)[1] });
		}

		while (!Q.isEmpty()) {
			int qSize = Q.size();

			for (int i = 0; i < qSize; i++) {
				int[] pollLoc = Q.poll();
				for (int d = 0; d < 4; d++) {
					int nr = pollLoc[0] + dr[d];
					int nc = pollLoc[1] + dc[d];

					if (isChecked(nr, nc) && map[nr][nc] == 0) {
						Q.offer(new int[] { nr, nc });
						map[nr][nc] = 1;
					}
				}
			}
			cnt++;
		}
	}

	private static boolean isChecked(int r, int c) {
		if (r >= 0 && r < N && c >= 0 && c < M) {
			return true;
		}
		return false;
	}
}