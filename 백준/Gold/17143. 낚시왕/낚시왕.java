import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static class Shark {
		int r;
		int c;
		int s; // 속력
		int d; // 이동방향 -> 1:위, 2:아래, 3:오른쪽, 4: 왼쪽
		int z; // 크기

		public Shark(int r, int c, int s, int d, int z) {
			super();
			this.r = r;
			this.c = c;
			this.s = s;
			this.d = d;
			this.z = z;
		}
	}

	private static int R, C, M, Ans;
	private static Shark[][] map;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new Shark[R][C];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int z = Integer.parseInt(st.nextToken());

			map[r][c] = new Shark(r, c, s, d, z);
		}

		for (int i = 0; i < C; i++) {
			mvAngler(i);
			mvShark();
		}
		
		br.close();
		System.out.println(Ans);
	}

	/**
	 * Description : 상어 이동.
	 * 
	 * @return void
	 *
	 * @date 2022. 10. 5.
	 * @author 유영훈
	 */
	private static void mvShark() {
		Queue<Shark> Q = new LinkedList<>();

		// 상어 위치 큐에 추가
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				if (map[r][c] != null) {
					Q.offer(new Shark(r, c, map[r][c].s, map[r][c].d, map[r][c].z));
				}
				map[r][c] = null;
			}
		}

		while (!Q.isEmpty()) {
			Shark shark = Q.poll();

			int speed = shark.s;
			for (int d = 0; d < speed; d++) {
				// 1 -> 위:[-1, 0] 아래:[1, 0], 오른쪽:[0, 1], 왼쪽:[0, -1]
				int nr = shark.r;
				int nc = shark.c;
				int dr = 0;
				int dc = 0;

				if (shark.d == 1) {
					dr = -1;
					dc = 0;
				} //
				else if (shark.d == 2) {
					dr = 1;
					dc = 0;
				} //
				else if (shark.d == 3) {
					dr = 0;
					dc = 1;
				} //
				else if (shark.d == 4) {
					dr = 0;
					dc = -1;
				}

				nr += dr;
				nc += dc;

				if (isChecked(nr, nc)) {
					shark.r = nr;
					shark.c = nc;
				} //
				else {
					shark.r -= dr;
					shark.c -= dc;
					if (shark.d == 1 || shark.d == 2) {
						shark.d = shark.d == 1 ? 2 : 1;
					} //
					else if (shark.d == 3 || shark.d == 4) {
						shark.d = shark.d == 3 ? 4 : 3;
					}
				}
			}

			checkShark(shark);
		}
	}
	
	/**
	 * Description : 상어가 겹쳐있는지 확인
	 * 
	 * @return void
	 *
	 * @date 2022. 10. 5.
	 * @author 유영훈
	 * @param shark
	 */
	private static void checkShark(Shark shark) {
		if (map[shark.r][shark.c] != null) {
			if (shark.z > map[shark.r][shark.c].z) {
				map[shark.r][shark.c] = shark;
			}
		} //
		else {
			map[shark.r][shark.c] = shark;
		}
	}

	/**
	 * Description : 낚시꾼 이동. 이동한 곳에 상어가 있다면 크기 추가.
	 * 
	 * @param c
	 * @return void
	 *
	 * @date 2022. 10. 5.
	 * @author 유영훈
	 */
	private static void mvAngler(int c) {
		for (int r = 0; r < R; r++) {
			if (map[r][c] != null) {
				Ans += map[r][c].z;
				map[r][c] = null;
				break;
			}
		}
	}

	private static boolean isChecked(int nr, int nc) {
		if (nr >= 0 && nr < R && nc >= 0 && nc < C) {
			return true;
		}
		return false;
	}
}