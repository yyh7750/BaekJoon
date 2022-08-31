import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	static class Node implements Comparable<Node> {
		int vertex;
		int weight;

		public Node(int vertex, int weight) {
			super();
			this.vertex = vertex;
			this.weight = weight;
		}

		@Override
		public int compareTo(Node o) {
			return this.weight - o.weight;
		}
	}

	private static int N, M, result;
	private static ArrayList<ArrayList<Node>> adjList;
	private static boolean[] visited;

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		adjList = new ArrayList<>();

		for (int i = 0; i <= N; i++) {
			adjList.add(new ArrayList<>());
		}

		for (int i = 0; i < M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			adjList.get(from).add(new Node(to, weight));
			adjList.get(to).add(new Node(from, weight));
		}

		visited = new boolean[N + 1];

		prim(1);

		br.close();
		System.out.println(result);
	}

	private static void prim(int start) {
		PriorityQueue<Node> Q = new PriorityQueue<>();
		Q.addAll(adjList.get(start));
		visited[start] = true;

		while (!Q.isEmpty()) {
			Node next = Q.poll();

			if (!visited[next.vertex]) {
				visited[next.vertex] = true;
				result += next.weight;
				Q.addAll(adjList.get(next.vertex));
			}
		}
	}
}