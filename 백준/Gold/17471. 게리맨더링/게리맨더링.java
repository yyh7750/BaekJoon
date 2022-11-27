import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	private static int N, ans;
	private static int[] people;
	private static boolean[] isSelected;
	private static List<Integer>[] adjList;

	/**
	 * @methodName : main
	 * @description :
	 * @param args
	 *
	 * @author : Younghun Yu
	 * @throws IOException
	 * @throws NumberFormatException
	 * @date : 2022.11.27
	 */
	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		N = Integer.parseInt(br.readLine());
		people = new int[N];
		isSelected = new boolean[N];
		adjList = new ArrayList[N];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			people[i] = Integer.parseInt(st.nextToken());
			adjList[i] = new ArrayList<>();
		}

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int adjCnt = Integer.parseInt(st.nextToken());
			for (int j = 0; j < adjCnt; j++) {
				adjList[i].add(Integer.parseInt(st.nextToken()) - 1);
			}
		}

		ans = Integer.MAX_VALUE;
		powerSet(0);

		br.close();
		ans = ans == Integer.MAX_VALUE ? -1 : ans;
		System.out.println(ans);
	}

	/**
	 * @methodName : powerSet
	 * @description : 구역을 나누기 위한 부분집합 메소드
	 * @param idx
	 *
	 * @author : Younghun Yu
	 * @date : 2022.11.27
	 */
	private static void powerSet(int idx) {
		if (idx == N) {
			// 구역이 잘 구분되었는지 확인
			if (connectCheck()) {
				int a = 0;
				int b = 0;

				for (int i = 0; i < N; i++) {
					if (isSelected[i]) {
						a += people[i];
					} //
					else {
						b += people[i];
					}
				}
				ans = Math.min(ans, Math.abs(a - b));
			}

			return;
		}

		isSelected[idx] = true;
		powerSet(idx + 1);
		isSelected[idx] = false;
		powerSet(idx + 1);
	}

	/**
	 * @methodName : connectCheck
	 * @description : 각 구역이 연결되어 있는지 확인하는 메소드
	 * @return : true, false
	 *
	 * @author : Younghun Yu
	 * @date : 2022.11.27
	 */
	private static boolean connectCheck() {
		boolean[] visited = new boolean[N];

		// 각 구역의 첫번째 인덱스 뽑아오기 -> 연결되어있는지 확인하기 위함
		int a = -1;
		for (int i = 0; i < N; i++) {
			if (isSelected[i]) {
				a = i;
				break;
			}
		}

		int b = -1;
		for (int i = 0; i < N; i++) {
			if (!isSelected[i]) {
				b = i;
				break;
			}
		}

		// 나눠진 구역이 없음
		if (a == -1 || b == -1) {
			return false;
		}

		bfs(a, visited, 'a');
		bfs(b, visited, 'b');

		for (int i = 0; i < N; i++) {
			if (!visited[i]) {
				return false;
			}
		}
		return true;
	}

	/**
	 * @methodName : bfs
	 * @description : 구역 연결 확인을 위한 bfs 메소드
	 * @param areaIdx : 구역 시작 번호
	 * @param visited : 방문체크 배열
	 * @param type    : a구역인지, b구역인지
	 *
	 * @author : Younghun Yu
	 * @date : 2022.11.27
	 */
	private static void bfs(int areaIdx, boolean[] visited, char type) {
		Queue<Integer> Q = new LinkedList<>();
		Q.offer(areaIdx);
		visited[areaIdx] = true;

		while (!Q.isEmpty()) {
			int cur = Q.poll();

			for (int i = 0; i < adjList[cur].size(); i++) {
				int next = adjList[cur].get(i);

				if (type == 'a') {
					if (!visited[next] && isSelected[next]) {
						visited[next] = true;
						Q.offer(next);
					}
				} //
				else {
					if (!visited[next] && !isSelected[next]) {
						visited[next] = true;
						Q.offer(next);
					}
				}
			}
		}
	}
}