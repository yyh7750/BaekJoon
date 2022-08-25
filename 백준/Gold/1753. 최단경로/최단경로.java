import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
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

	private static int V, E, K;
	private static ArrayList<ArrayList<Node>> adjList;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(br.readLine());

		adjList = new ArrayList<>();

		for (int i = 0; i <= V; i++) {
			adjList.add(new ArrayList<>());
		}

		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			adjList.get(from).add(new Node(to, weight));
		}

		PriorityQueue<Node> Q = new PriorityQueue<>();
		Q.offer(new Node(K, 0));

		int[] dist = new int[V + 1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[K] = 0;
		boolean[] visited = new boolean[V + 1];

		while (!Q.isEmpty()) {
			Node cur = Q.poll();

			if (!visited[cur.vertex]) {
				visited[cur.vertex] = true;

				for (Node next : adjList.get(cur.vertex)) {
					if (dist[next.vertex] > dist[cur.vertex] + next.weight) {
						dist[next.vertex] = dist[cur.vertex] + next.weight;
						Q.offer(new Node(next.vertex, dist[next.vertex]));
					}
				}
			}
		}

		br.close();

		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= V; i++) {
			if (dist[i] == Integer.MAX_VALUE) {
				sb.append("INF").append("\n");
			} //
			else {
				sb.append(dist[i]).append("\n");
			}
		}

		System.out.println(sb);
	}
}