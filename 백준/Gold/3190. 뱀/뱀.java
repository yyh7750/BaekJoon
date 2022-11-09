import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static class Loc {
		int r, c;

		public Loc(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}
	}

	// 격자판 크기 N, 사과 개수 K
	private static int N, K, L, time;
	private static int[][] map;
	// 처음 시작방향 : 우 (0)
	// 우, 하, 좌, 상 -> 0, 1, 2, 3
	private static int[] dr = { 0, 1, 0, -1 };
	private static int[] dc = { 1, 0, -1, 0 };
	private static int[][] direction;

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		K = Integer.parseInt(br.readLine());
		StringTokenizer st = null;

		map = new int[N][N];
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;
			map[r][c] = 1;
		}

		L = Integer.parseInt(br.readLine());
		// 방향을 꺾을 수 있는 방향 개수 L, 왼쪽, 오른쪽
		// 방향 전환해주는 배열을 시계 방향으로 했기 때문에
		// 왼쪽일 경우 -1, 오른쪽일 경우 1
		direction = new int[L][2];
		for (int i = 0; i < L; i++) {
			st = new StringTokenizer(br.readLine());
			direction[i][0] = Integer.parseInt(st.nextToken());
			String direct = st.nextToken();
			direction[i][1] = direct.equals("D") ? 1 : -1;
		}

		go(0, 0);

		br.close();
		System.out.println(time);
	}

	private static void go(int r, int c) {
		Queue<Loc> Q = new ArrayDeque<>();
		Q.offer(new Loc(0, 0));

		// 현재 진행중인 방향을 주어진 시간만큼 돌았을 때
		// 값을 갱신시켜 방향을 전환시켜줄 변수
		int directIdx = 0;
		int curDir = 0; // 초기 방향 오른쪽

		while (true) {
			int nr = r + dr[curDir];
			int nc = c + dc[curDir];

			time++;
			r = nr;
			c = nc;

			// 게임 끝
			if (isChecked(nr, nc, Q)) {
				return;
			}

			// 사과를 먹을 경우
			if (map[nr][nc] == 1) {
				Q.offer(new Loc(nr, nc));
				map[nr][nc] = 0;
			}
			// 아닐 경우
			else {
				Q.offer(new Loc(nr, nc));
				map[nr][nc] = 0;
				Q.poll();
			}

			if (directIdx < L) {
				if (time == direction[directIdx][0]) {
					curDir = getNextDirection(curDir, direction[directIdx][1]);
					directIdx++;
				}
			}
		}
	}

	private static int getNextDirection(int curDir, int turnDir) {
		int nextDir = (curDir + turnDir) % 4;
		return nextDir == -1 ? 3 : nextDir;
	}

	private static boolean isChecked(int nr, int nc, Queue<Loc> Q) {
		// 범위값 벗어날떼
		if (nr < 0 || nc < 0 || nr >= N || nc >= N) {
			return true;
		}
		// 몸통에 부딪힐때
		for (Loc body : Q) {
			if (nr == body.r && nc == body.c) {
				return true;
			}
		}

		return false;
	}
}