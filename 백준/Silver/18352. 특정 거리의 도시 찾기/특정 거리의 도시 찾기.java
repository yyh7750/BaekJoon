import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	private static int N, M, K, X;
	private static ArrayList<ArrayList<Integer>> adjList;
	private static boolean[] visited;
	private static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());

		visited = new boolean[N + 1];

		adjList = new ArrayList<>();
		for (int i = 0; i < N + 1; i++) {
			adjList.add(new ArrayList<>());
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			adjList.get(from).add(to);
		}

		bfs(X);

		br.close();
		System.out.println(sb.length() == 0 ? -1 : sb);
	}

	private static void bfs(int cur) {
		Queue<int[]> Q = new LinkedList<>();
		visited[cur] = true;
		Q.offer(new int[] { cur, 0 });
		
		List<Integer> list = new ArrayList<>();

		while (!Q.isEmpty()) {
			int[] curArr = Q.poll();
			for (int i : adjList.get(curArr[0])) {
				if (!visited[i]) {
					visited[i] = true;
					Q.offer(new int[] { i, curArr[1] + 1 });
				}
			}
			if (curArr[1] == K) {
				list.add(curArr[0]);
			}
		}
		
		Collections.sort(list);
		for(int i : list) {
			sb.append(i).append("\n");
		}
	}
}