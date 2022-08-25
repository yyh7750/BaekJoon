import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	static class Node {
		int to;
		int weight;

		public Node(int to, int weight) {
			this.to = to;
			this.weight = weight;
		}
	}

	private static int N, M;
	private static ArrayList<ArrayList<Node>> adjList;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		adjList = new ArrayList<>();
		for (int i = 0; i <= N; i++) {
			adjList.add(new ArrayList<>());
		}

		for (int i = 0; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			adjList.get(from).add(new Node(to, weight));
			adjList.get(to).add(new Node(from, weight));
		}

		PriorityQueue<Node> bestQ = new PriorityQueue<>(new Comparator<Node>() {
			@Override
			public int compare(Node o1, Node o2) {
				return o2.weight - o1.weight;
			}
		});

		int best = (int) Math.pow(solution(bestQ, 0), 2);

		PriorityQueue<Node> worstQ = new PriorityQueue<>(new Comparator<Node>() {
			@Override
			public int compare(Node o1, Node o2) {
				return o1.weight - o2.weight;
			}
		});

		int worst = (int) Math.pow(solution(worstQ, 0), 2);

		br.close();
		System.out.println(worst - best);
	}

	public static int solution(PriorityQueue<Node> pq, int startNode) {
		boolean[] visited = new boolean[N + 1];

		pq.offer(new Node(startNode, -1));

		int fatigue = 0;

		while (!pq.isEmpty()) {
			Node cur = pq.poll();

			if (!visited[cur.to]) {
				visited[cur.to] = true;
				if (cur.weight == 0) {
					fatigue++;
				}
				pq.addAll(adjList.get(cur.to));
			}
		}
		
		return fatigue;
	}
}