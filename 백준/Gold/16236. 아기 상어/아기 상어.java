import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

//	* 아기 상어 최초의 크기는 2이다.
//	* 자신보다 크기가 큰 물고기가 있는 칸은 지나갈 수 없다.
//	* 자신과 크기가 같은 물고기가 있는 칸은 지나갈 수만 있다.
//	* 자신보다 작은 물고기는 먹을 수 있다.
public class Main {

	static class Shark implements Comparable<Shark> {
		int r, c, cnt;

		public Shark(int r, int c, int cnt) {
			super();
			this.r = r;
			this.c = c;
			this.cnt = cnt;
		}

		@Override
		public int compareTo(Shark o) {
			if (this.cnt == o.cnt) {
				if (this.r == o.r) {
					return this.c - o.c;
				}
				return this.r - o.r;
			}
			return this.cnt - o.cnt;
		}

		@Override
		public String toString() {
			return "Shark [r=" + r + ", c=" + c + ", cnt=" + cnt + "]";
		}
	}

	private static int N, dist, eat, size = 2; // 총 이동거리, 물고기를 먹을때 몇마리 먹었는지 세기 위한 변수, 상어 초기 사이즈
	private static boolean flag;
	private static int[][] map; // N x N 크기 배열
	private static boolean[][] visited;
	private static int[] dr = { 1, -1, 0, 0 };
	private static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st = null;

		map = new int[N][N];
		int sr = 0, sc = 0;

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 9) {
					sr = i;
					sc = j;
				}
			}
		}

//	* 1. 먹을 수 있는 물고기를 탐색한다.
//	* 2. 먹을 수 있는 물고기가 여러마리라면 가장 가까운 물고기를 먹으러 간다.
//	* 3. 최단거리가 같은 물고기가 있다면 행우선, 열우선 순으로 물고기를 먹는다.
		map[sr][sc] = 0;
		bfs(sr, sc);

		br.close();
		System.out.println(dist);
	}

	private static void bfs(int sr, int sc) {
		// 먹을 수 있는 물고기를 계속 찾기 위한 반복문
		while (true) {
			PriorityQueue<Shark> pq = new PriorityQueue<>();
			pq.offer(new Shark(sr, sc, 0));
			visited = new boolean[N][N];
			visited[sr][sc] = true;
		
			flag = false;
			while (!pq.isEmpty()) {
				Shark shark = pq.poll();

				if (map[shark.r][shark.c] > 0 && map[shark.r][shark.c] < size) {
					// 밥 먹었어요
					flag = true;
					eat++;
					dist += shark.cnt;
					map[shark.r][shark.c] = 0;
					sr = shark.r;
					sc = shark.c;

					if (eat == size) {
						size++;
						eat = 0;
					}
					break;
				}

				for (int d = 0; d < 4; d++) {
					int nr = shark.r + dr[d];
					int nc = shark.c + dc[d];

					if (isChecked(nr, nc) && !visited[nr][nc] && map[nr][nc] <= size) {
						visited[nr][nc] = true;
						pq.offer(new Shark(nr, nc, shark.cnt + 1));
					}
				}
			}
			
			if (!flag) {
				break;
			}
		}
	}

	private static boolean isChecked(int nr, int nc) {
		if (nr >= 0 && nc >= 0 && nr < N && nc < N) {
			return true;
		}
		return false;
	}
}