import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

	private static ArrayList<ArrayList<Integer>> adjList;
	private static int N, M;
	private static boolean[] visited;
	private static int[] child;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		child = new int[N + 1];

		adjList = new ArrayList<>();
		for (int i = 0; i <= N; i++) {
			adjList.add(new ArrayList<>());
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			adjList.get(from).add(to);
		}

		for (int i = 1; i <= N; i++) {
			visited = new boolean[N + 1];
			dfs(i);
		}

		int max = Integer.MIN_VALUE;
		for (int i = 1; i <= N; i++) {
			max = Math.max(max, child[i]);
		}

		for (int i = 1; i <= N; i++) {
			if (max == child[i]) {
				sb.append(i).append(" ");
			}
		}
		
		br.close();
		System.out.println(sb);
	}

	private static void dfs(int cur) {
		visited[cur] = true;
		for (int next : adjList.get(cur)) {
			if (!visited[next]) {
				// 가장 하위 노드부터 감염되기 시작하므로
				// 연결되어 있는 하위 노드 카운트.
				child[next]++;
				dfs(next);
			}
		}
	}
}