import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	private static int N, M, result;
	private static ArrayList<ArrayList<Integer>> adjList;
	private static List<Integer> truthList;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		result = M;

		adjList = new ArrayList<>();

		for (int i = 0; i < M; i++) {
			adjList.add(new ArrayList<>());
		}

		truthList = new ArrayList<>();
		st = new StringTokenizer(br.readLine());
		int friend = Integer.parseInt(st.nextToken());
		for (int i = 0; i < friend; i++) {
			truthList.add(Integer.parseInt(st.nextToken()));
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int people = Integer.parseInt(st.nextToken());
			for (int j = 0; j < people; j++) {
				adjList.get(i).add(Integer.parseInt(st.nextToken()));
			}
		}

		bfs();

		System.out.println(result);
	}

	private static void bfs() {
		Queue<Integer> Q = new LinkedList<>();
		boolean[] truthCheck = new boolean[N + 1];
		boolean[] partyCheck = new boolean[M];

		for (int i = 0; i < truthList.size(); i++) {
			Q.offer(truthList.get(i));
			truthCheck[truthList.get(i)] = true;
		}

		while (!Q.isEmpty()) {
			int cur = Q.poll();

			for (int i = 0; i < M; i++) {
				if (!adjList.get(i).contains(cur)) {
					continue;
				} //
				if (partyCheck[i]) {
					continue;
				}

				for (int next : adjList.get(i)) {
					if (!truthCheck[next]) {
						truthCheck[next] = true;
						Q.add(next);
					}
				}

				partyCheck[i] = true;
				result--;
			}
		}
	}
}