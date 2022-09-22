import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {

	static class House {
		int pos;
		long dist;

		public House(int pos, long dist) {
			super();
			this.pos = pos;
			this.dist = dist;
		}
	}

	private static int N, K;
	private static long result;
	private static Set<Integer> visited;
	private static int[] dr = { -1, 1 };
	private static Queue<House> Q;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		// 샘터가 100_000_000의 범위 값을 갖기 때문에 Set 활용.
		visited = new HashSet<>();
		Q = new LinkedList<>();
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			int oasis = Integer.parseInt(st.nextToken());
			visited.add(oasis);
			Q.offer(new House(oasis, 0));
		}

		bfs();

		br.close();
		System.out.println(result);
	}

	/**
	 * Description : 샘터와 가까운 곳을 기준으로 집을 짓기 위한 BFS 문제풀이 메소드
	 * 
	 * @return void
	 *
	 * @date 2022. 9. 22.
	 * @author 유영훈
	 */
	private static void bfs() {
		while (!Q.isEmpty()) {
			House cur = Q.poll();

			// 좌, 우 탐색
			for (int d = 0; d < 2; d++) {
				int nr = cur.pos + dr[d];

				// 방문배열을 대신한 ArrayList. 포함되어 있다면 패스
				if (!visited.contains(nr)) {
					// 집을 다 지었다면 종료.
					if (K == 0) {
						return;
					}

					// 결과값 도출. 방문하지 않았던 곳이라면 현재 거리값 + 1
					result += cur.dist + 1;
					K--;

					// 집을 다 짓지 않았다면 방금 지은 곳을 기준으로 반복.
					visited.add(nr);
					Q.offer(new House(nr, cur.dist + 1));
				}
			}
		}
	}
}