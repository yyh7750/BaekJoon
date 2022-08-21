import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	private static int[][] adjArr;
	private static boolean[] visited;
	private static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int V = Integer.parseInt(st.nextToken());

		adjArr = new int[N + 1][N + 1];

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			adjArr[from][to] = 1;
			adjArr[to][from] = 1;
		}

		visited = new boolean[N + 1];
		dfs(N, V);
		sb.append("\n");
		visited = new boolean[N + 1];
		bfs(N, V);

		System.out.println(sb);
	}

	private static void dfs(int N, int idx) {
		visited[idx] = true;
		sb.append(idx).append(" ");

		for (int i = 0; i < N + 1; i++) {
			if (!visited[i] && adjArr[idx][i] != 0) {
				dfs(N, i);
			}
		}
	}

	private static void bfs(int N, int idx) {
		Queue<Integer> Q = new LinkedList<Integer>();
		Q.offer(idx);
		visited[idx] = true;

		while (!Q.isEmpty()) {
			idx = Q.poll();
			sb.append(idx).append(" ");

			for (int i = 0; i < N + 1; i++) {
				if (!visited[i] && adjArr[idx][i] != 0) {
					visited[i] = true;
					Q.offer(i);
				}
			}
		}
	}
}