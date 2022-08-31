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

	private static int N, D;
	private static ArrayList<ArrayList<Node>> adjList;
	private static int[] dist;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());

		adjList = new ArrayList<>();
		dist = new int[10001];
		for (int i = 0; i < 10001; i++) {
			adjList.add(new ArrayList<>());
			dist[i] = i;
		}

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			adjList.get(from).add(new Node(to, weight));
		}

		dijkstra(0);

		br.close();
		System.out.println(dist[D]);
	}

	private static void dijkstra(int cur) {
		while (cur <= D) {
			if (dist[cur + 1] > dist[cur] + 1) {
				dist[cur + 1] = dist[cur] + 1;
			}

			for (Node next : adjList.get(cur)) {
				if (dist[next.vertex] > dist[cur] + next.weight) {
					dist[next.vertex] = dist[cur] + next.weight;
				}
			}
			cur++;
		}
	}
}