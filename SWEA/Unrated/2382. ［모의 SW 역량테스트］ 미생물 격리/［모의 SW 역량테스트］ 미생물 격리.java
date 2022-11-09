import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {

	static class Microbe {
		int r, c, microbeCnt, direct;

		public Microbe(int r, int c, int microbeCnt, int direct) {
			super();
			this.r = r;
			this.c = c;
			this.microbeCnt = microbeCnt;
			this.direct = direct;
		}

		@Override
		public String toString() {
			return "@";
		}
	}

	// 맵의 크기 N, 격리 시간 M, 미생물 군집개수 K
	private static int N, M, K;
	// 상, 하, 좌, 우
	private static int[] dr = { -1, 1, 0, 0 };
	private static int[] dc = { 0, 0, -1, 1 };
	private static List<Microbe> microbes;
	private static Microbe[][] map;

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		for (int tc = 1; tc <= T; tc++) {
			sb.append("#").append(tc).append(" ");

			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());

			map = new Microbe[N][N];
			microbes = new ArrayList<>();
			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine());
				int sr = Integer.parseInt(st.nextToken());
				int sc = Integer.parseInt(st.nextToken());
				int microbeCnt = Integer.parseInt(st.nextToken());
				int direct = Integer.parseInt(st.nextToken());
				microbes.add(new Microbe(sr, sc, microbeCnt, direct));
				map[sr][sc] = microbes.get(i);
			}

			for (int i = 0; i < M; i++) {
				// start
				moveMicrobe();
			}

			sb.append(getResult()).append("\n");
		}

		br.close();
		System.out.println(sb);
	}

	private static int getResult() {
		int result = 0;

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] != null) {
					result += map[i][j].microbeCnt;
				}
			}
		}

		return result;
	}

	/**
	 * @methodName : moveMicrobe
	 * @Description : 움직일때 방향 -> 상:1, 하:2, 좌:3, 우:4
	 * 
	 * @param map      : 미생물 군집이 존재하는 N*N 크기의 맵
	 * @param microbes : 미생물 군집의 정보가 담긴 리스트
	 * @return void
	 *
	 * @date 2022. 11. 9.
	 * @author 유영훈
	 */
	private static void moveMicrobe() {
		Queue<Microbe> Q = new LinkedList<>();
		for (Microbe microbe : microbes) {
			Q.offer(microbe);
		}

		microbes = new ArrayList<>();
		List<Microbe>[][] combList = new ArrayList[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				combList[i][j] = new ArrayList<>();
			}
		}

		while (!Q.isEmpty()) {
			Microbe cur = Q.poll();

			int nr = cur.r + dr[cur.direct - 1];
			int nc = cur.c + dc[cur.direct - 1];

			if (isChecked(nr, nc)) {
				// 가장자리에 도착하면 미생물 수를 줄이고 방향을 반대로 바궈준다.
				if (nr == N - 1 || nc == N - 1 || nr == 0 || nc == 0) {
					map[cur.r][cur.c] = null;
					cur = killMicrobe(nr, nc, cur);
					map[nr][nc] = cur;
				} //
				else {
					map[cur.r][cur.c] = null;
					cur = new Microbe(nr, nc, cur.microbeCnt, cur.direct);
					combList[nr][nc].add(cur);
				}
			}
		}

		combine(combList);
	}

	private static void combine(List<Microbe>[][] combList) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (combList[i][j] != null) {
					// 하나만 있다면 바로 쓴다.
					if (combList[i][j].size() == 1) {
						map[i][j] = combList[i][j].get(0);
						microbes.add(map[i][j]);
					}
					//
					else if (combList[i][j].size() > 1) {
						// 미생물들의 합을 저장할 변수
						int sumCnt = 0;
						// 최댓값을 저장할 변수
						int maxCnt = 0;

						for (int k = 0; k < combList[i][j].size(); k++) {
							// 미생물들의 합
							sumCnt += combList[i][j].get(k).microbeCnt;

							if (maxCnt < combList[i][j].get(k).microbeCnt) {
								map[i][j] = combList[i][j].get(k);
								maxCnt = combList[i][j].get(k).microbeCnt;
							}
						}
						map[i][j].microbeCnt = sumCnt;
						microbes.add(map[i][j]);
					}
				}
			}
		}
	}

	private static Microbe killMicrobe(int nr, int nc, Microbe microbe) {
		microbe.microbeCnt /= 2;

		// 방향 반대로 바꾸기
		int direct = reverseDirect(microbe.direct);

		if (microbe.microbeCnt != 0) {
			microbes.add(new Microbe(nr, nc, microbe.microbeCnt, direct));
			return microbe;
		}

		return null;
	}

	private static int reverseDirect(int direct) {
		switch (direct) {
		case 1:
			return 2;
		case 2:
			return 1;
		case 3:
			return 4;
		case 4:
			return 3;
		}
		return 0;
	}

	private static boolean isChecked(int nr, int nc) {
		if (nr >= 0 && nr < N && nc >= 0 && nc < N) {
			return true;
		}
		return false;
	}
}