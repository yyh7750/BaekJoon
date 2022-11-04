import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static class Taxi implements Comparable<Taxi> {
		// 택시의 위치정보 r, c
		// 택시 이동거리 cnt
		int r, c, cnt;

		public Taxi(int r, int c, int cnt) {
			super();
			this.r = r;
			this.c = c;
			this.cnt = cnt;
		}

		@Override
		public int compareTo(Taxi o) {
			if (this.cnt == o.cnt) {
				if (this.r == o.r) {
					return this.c - o.c;
				} //
				return this.r - o.r;
			}
			return this.cnt - o.cnt;
		}

		@Override
		public String toString() {
			return "Taxi [r=" + r + ", c=" + c + ", cnt=" + cnt + "]";
		}
	}

	static class Passenger {
		// 승객 처음 위치 sr, sc
		// 승객 목적지 위치 er, ec
		// 목적지까지 간 거리 cnt
		int sr, sc, er, ec;

		public Passenger(int sr, int sc, int er, int ec) {
			super();
			this.sr = sr;
			this.sc = sc;
			this.er = er;
			this.ec = ec;
		}
	}

	private static int N, M, fuel;
	private static int[][] map;
	// 승객의 위치 정보를 담는 리스트
	private static Passenger[] passengerArr;
	private static int[] dr = { 1, -1, 0, 0 };
	private static int[] dc = { 0, 0, 1, -1 };
	private static Taxi taxiLoc;

	/**
	 * @methodName : main
	 * @Description
	 */
//     * 1. 초기 택시의 위치에서 승객들 간의 최소거리를 구한다. BFS
//     * 2. 최단 거리의 승객을 태우러 간다.
//     * 3. 만약 최단 거리의 승객이 여럿이라면 행 우선순위를 적용한다.
//     * 4. 그마저도 여럿이라면 열 우선순위를 적용한다.
//     * 5. 승객에게 도착하면 그 거리만큼 연료에서 뺀다.
//     * 6. 목적지까지 태워다 주는 과정에서 연료가 바닥나면 -1 출력 후 시스템 종료.
//     * 7. 목적지에 도달하면 목적지까지의 거리를 빼준 후 (목적지까지 간 거리 * 2) 만큼 더해준다. : fuel += 목적지까지 거리;
//     * 8. 모든 승객을 데려다줄 때까지 반복한다.
	/**
	 * @param args
	 * @throws IOException
	 *
	 * @return void
	 *
	 * @date 2022. 11. 4.
	 * @author 유영훈
	 */
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		fuel = Integer.parseInt(st.nextToken());

		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		st = new StringTokenizer(br.readLine());
		// cnt = 0; 초기 택시 상태. 아무 이동거리가 없으므로 0
		taxiLoc = new Taxi(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1, 0);

		passengerArr = new Passenger[M + 2];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int sr = Integer.parseInt(st.nextToken()) - 1;
			int sc = Integer.parseInt(st.nextToken()) - 1;
			int er = Integer.parseInt(st.nextToken()) - 1;
			int ec = Integer.parseInt(st.nextToken()) - 1;
			map[sr][sc] = i + 2;
			passengerArr[i + 2] = new Passenger(sr, sc, er, ec);
		}

		// 승객 태우러 출발
		for (int i = 0; i < M; i++) {
			findPassenger(taxiLoc);
		}

		br.close();
		System.out.println(fuel);
	}

	/**
	 * @methodName : findPassenger
	 * @Description : 승객들의 거리를 구해 최단거리의 승객을 찾는 메소드
	 *
	 * @param taxiLoc : 택시의 현재 위치 정보(초기값, 승객을 내려준 위치)
	 *
	 * @return void
	 *
	 * @date 2022. 11. 4.
	 * @author 유영훈
	 */
	private static void findPassenger(Taxi taxiLoc) {
		PriorityQueue<Taxi> pq = new PriorityQueue<>();
		pq.offer(taxiLoc);
		boolean[][] visited = new boolean[N][N];
		visited[taxiLoc.r][taxiLoc.c] = true;

		while (!pq.isEmpty()) {
			Taxi curTaxi = pq.poll();

			if (map[curTaxi.r][curTaxi.c] > 1) {
				fuel -= curTaxi.cnt;
				int targetIndex = map[curTaxi.r][curTaxi.c];
				map[curTaxi.r][curTaxi.c] = 0;
				curTaxi.cnt = 0;
				drive(curTaxi, targetIndex);
				return;
			}

			for (int d = 0; d < 4; d++) {
				int nr = curTaxi.r + dr[d];
				int nc = curTaxi.c + dc[d];

				if (isChecked(nr, nc) && !visited[nr][nc] && map[nr][nc] != 1) {
					visited[nr][nc] = true;
					pq.offer(new Taxi(nr, nc, curTaxi.cnt + 1));
				}
			}
		}
		
		System.out.println(-1);
		System.exit(0);
	}

	/**
	 * @methodName : drive
	 * @Description : 승객을 택시에 태워 목적지까지 가는 메소드
	 *
	 * @param startLoc    : 승객이 택시에 탑승한 위치 정보
	 * @param targetIndex : 승객의 목적지 위치 정보를 가져올 인덱스
	 *
	 * @return void
	 *
	 * @date 2022. 11. 4.
	 * @author 유영훈
	 * @param map2
	 */
	private static void drive(Taxi startLoc, int targetIndex) {
		Queue<Taxi> Q = new LinkedList<>();
		Q.offer(startLoc);
		boolean[][] visited = new boolean[N][N];
		visited[startLoc.r][startLoc.c] = true;

		// 목적지 위치 정보
		int er = passengerArr[targetIndex].er;
		int ec = passengerArr[targetIndex].ec;

		while (!Q.isEmpty()) {
			Taxi curTaxi = Q.poll();

			for (int d = 0; d < 4; d++) {
				int nr = curTaxi.r + dr[d];
				int nc = curTaxi.c + dc[d];

				if (isChecked(nr, nc) && !visited[nr][nc] && map[nr][nc] != 1) {
					// 도착
					if (nr == er && nc == ec && fuel - curTaxi.cnt - 1 >= 0) {
						fuel += curTaxi.cnt + 1;
						taxiLoc = new Taxi(er, ec, 0);
						return;
					}
					// 연료 없음
					if (fuel - curTaxi.cnt - 1 < 0) {
						System.out.println(-1);
						System.exit(0);
					}

					visited[nr][nc] = true;
					Q.offer(new Taxi(nr, nc, curTaxi.cnt + 1));
				}
			}
		}
		
		System.out.println(-1);
		System.exit(0);
	}

	/**
	 * @methodName : isChecked
	 * @Description : 맵 범위를 벗어나는지 검사하는 메소드
	 *
	 * @param nr : 다음 위치 row 정보
	 * @param nc : 다음 위치 col 정보
	 *
	 * @return boolean : true 이동 가능. false 이동 불가능.
	 *
	 * @date 2022. 11. 4.
	 * @author 유영훈
	 */
	private static boolean isChecked(int nr, int nc) {
		if (nr >= 0 && nc >= 0 && nr < N && nc < N) {
			return true;
		}
		return false;
	}
}