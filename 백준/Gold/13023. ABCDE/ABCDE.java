import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * Description : 친구 관계가 5명 이상인 것을 확인하는 문제. depth가 4일 경우 성립된다.
 * 
 * @date 2022. 8. 23.
 * @author 유영훈
 */
public class Main {

	private static int N, M;
	private static boolean flag;
	private static ArrayList<ArrayList<Integer>> adjList;
	private static boolean[] visited;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		// 친구 수
		N = Integer.parseInt(st.nextToken());
		// 친구 관계 수
		M = Integer.parseInt(st.nextToken());

		// 인접 리스트 객체 생성
		adjList = new ArrayList<ArrayList<Integer>>();
		for (int i = 0; i < N; i++) {
			adjList.add(new ArrayList<>());
		}

		// 인접 리스트 초기화
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			adjList.get(from).add(to);
			adjList.get(to).add(from);
		}
		br.close();

		for (int i = 0; i < N; i++) {
			visited = new boolean[N];
			dfs(i, 0);
		}

		System.out.println(flag ? 1 : 0);
	}

	private static void dfs(int idx, int depth) {
        if (flag) {
			return;
		}
		// 친구관계 5명 이상 조건 만족으로 인한 true 리턴.
		if (depth >= 4) {
			flag = true;
			return;
		}

		// 방문배열 체크
		visited[idx] = true;
		// 자식 노드들 탐색 -> depth 늘려가기
		for (int nextNode : adjList.get(idx)) {
			if (!visited[nextNode]) {
				dfs(nextNode, depth + 1);
			}
		}
		visited[idx] = false;
	}
}