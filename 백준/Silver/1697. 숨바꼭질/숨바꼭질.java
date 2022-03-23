import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	private static int[] visited = null;

	public static void main(String[] args) {

		BufferedReader br = null;
		StringTokenizer st = null;
		// 현재 위치
		int n = 0;
		// 가야할 위치
		int m = 0;

		try {

			br = new BufferedReader(new InputStreamReader(System.in));
			st = new StringTokenizer(br.readLine());

			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());

			br.close();
			
			// 숨바꼭질 가능 범위 0 ~ 100000
			visited = new int[100001];

			if (n == m) {
				System.out.println(0);
			} else {
				bfs(n, m);
				System.out.println(visited[m]);
			}

		} //
		catch (IOException ioException) {
			System.out.println("입력 오류. 다시 실행해주세요.");
		} //
		catch (NumberFormatException numberException) {
			System.out.println("숫자를 입력해주세요. 다시 실행해주세요.");
		}
	}

	private static void bfs(int n, int m) {
		Queue<Integer> queue = new LinkedList<>();

		queue.add(n);
		// 방문할때마다 +1 해주기 위한 배열
		visited[n] = 0;

		while (!queue.isEmpty()) {
			int x = queue.poll();

			// 범위에 따라 값을 매겨준다. 0이 아닌 경우 continue
			if (x - 1 >= 0 && visited[x - 1] == 0) {
				visited[x - 1] = visited[x] + 1;
				queue.add(x - 1);
			}
			if (x + 1 < visited.length && visited[x + 1] == 0) {
				visited[x + 1] = visited[x] + 1;
				queue.add(x + 1);
			}
			if (x * 2 < visited.length && visited[x * 2] == 0) {
				visited[x * 2] = visited[x] + 1;
				queue.add(x * 2);
			}
		}
	}
}