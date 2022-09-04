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

	private static ArrayList<ArrayList<Node>> adjList;
	private static int N, M;
	private static int[] dist;
	private static boolean[] visited;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		adjList = new ArrayList<>();
		for (int i = 0; i <= N; i++) {
			adjList.add(new ArrayList<>());
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			adjList.get(from).add(new Node(to, weight));
			adjList.get(to).add(new Node(from, weight));
		}

		dijkstra(1);

		System.out.println(dist[N]);
	}

	private static void dijkstra(int start) {
		dist = new int[N + 1];
		visited = new boolean[N + 1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[1] = 0;

		PriorityQueue<Node> Q = new PriorityQueue<>();
		Q.offer(new Node(start, 0));

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
	}
}