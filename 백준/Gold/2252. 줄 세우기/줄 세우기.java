import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int N, M;
	static ArrayList<ArrayList<Integer>> adjList;
	static int[] inDegree;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		adjList = new ArrayList<>();
		for (int i = 0; i <= N; i++) {
			adjList.add(new ArrayList<>());
		}
		inDegree = new int[N + 1];

		for (int i = 0; i < M; ++i) {
			st = new StringTokenizer(br.readLine(), " ");
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			adjList.get(from).add(to);
			++inDegree[to];
		}
		
		br.close();

		ArrayList<Integer> resultList = topologySort();

		if (resultList.size() == N) {
			StringBuilder sb = new StringBuilder();
			for (int i : resultList) {
				sb.append(i).append(" ");
			}
			System.out.println(sb);
		}
	}

	private static ArrayList<Integer> topologySort() {
		ArrayList<Integer> list = new ArrayList<>();
		Queue<Integer> queue = new LinkedList<Integer>();

		for (int i = 1; i <= N; ++i) {
			if (inDegree[i] == 0) {
				queue.offer(i);
			}
		}

		while (!queue.isEmpty()) {
			int cur = queue.poll();
			list.add(cur);

			for (int i : adjList.get(cur)) {
				if (--inDegree[i] == 0) {
					queue.offer(i);
				}
			}
		}
		return list;
	}
}