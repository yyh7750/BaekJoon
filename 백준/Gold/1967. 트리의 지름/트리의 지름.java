import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

	static class Node {
		int vertex;
		int weight;

		public Node(int vertex, int weight) {
			super();
			this.vertex = vertex;
			this.weight = weight;
		}
	}

	private static int N, max, farNode;
	private static ArrayList<ArrayList<Node>> adjList;
	private static boolean[] visited;

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		adjList = new ArrayList<>();
		for (int i = 0; i <= N; i++) {
			adjList.add(new ArrayList<>());
		}

		for (int i = 0; i < N - 1; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			adjList.get(from).add(new Node(to, weight));
			adjList.get(to).add(new Node(from, weight));
		}

		visited = new boolean[N + 1];
		dfs(1, 0);

		visited = new boolean[N + 1];
		dfs(farNode, 0);

		System.out.println(max);
	}

	private static void dfs(int cur, int weight) {
		if (max < weight) {
			max = weight;
			farNode = cur;
		}

		visited[cur] = true;

		for (Node next : adjList.get(cur)) {
			if (!visited[next.vertex]) {
				visited[next.vertex] = true;
				dfs(next.vertex, next.weight + weight);
			}
		}
	}
}