import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) {

		BufferedReader br = null;
		StringTokenizer st = null;
		int n = 0;
		int m = 0;
		int v = 0;
		int graph[][] = null;
		boolean[] visit = null;

		try {
			br = new BufferedReader(new InputStreamReader(System.in));
			st = new StringTokenizer(br.readLine());

			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			v = Integer.parseInt(st.nextToken());

			graph = new int[n + 1][n + 1];

			for (int i = 0; i < m; i++) {
				st = new StringTokenizer(br.readLine());
				
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				graph[a][b] = 1;
				graph[b][a] = 1;
			}

			visit = new boolean[n + 1];
			dfs(graph, visit, v);
			
			System.out.println();
			
			visit = new boolean[n + 1];
			bfs(graph, visit, v);
		} //
		catch (IOException ioException) {
			System.out.println("입력 오류. 다시 실행해주세요.");
		} //
		catch (NumberFormatException numberException) {
			System.out.println("숫자를 입력해주세요. 다시 실행해주세요.");
		}
	}

	/**
	 * @methodName : dfs
	 * @description : 깊이 우선 탐색 알고리즘 - Stack 이용
	 * @param graph
	 * @param visit
	 * @param v
	 *
	 * @author : Younghun Yu
	 * @date : 2022.03.17
	 */
	public static void dfs(int[][] graph, boolean[] visit, int v) {
		Stack<Integer> stack = new Stack<>();
		stack.push(v);
		visit[v] = true;
		System.out.printf("%d ", v);
		
		while(!stack.isEmpty()) {
			int now = stack.peek();
			boolean p = false;
			
			for(int i =0; i < graph.length; ++i) {
				if(graph[now][i] == 1 && !visit[i]) {
					visit[i] = true;
					p = true;
					stack.push(i);
					System.out.printf("%d ", i);
					break;
				}
			}
			
			if(!p) {
				stack.pop();
			}
		}
	}
	
	/**
	 * @methodName : bfs
	 * @description : 너비우선탐색 알고리즘 - Queue 이용
	 * @param graph
	 * @param visit
	 * @param v
	 *
	 * @author : Younghun Yu
	 * @date : 2022.03.17
	 */
	public static void bfs(int[][] graph, boolean[] visit, int v) {
		Queue<Integer> queue = new LinkedList<>();
		queue.offer(v);
		visit[v] = true;
		
		while(!queue.isEmpty()) {
			int now = queue.poll();
			System.out.printf("%d ", now);
			
			for(int i = 0; i < graph.length; i++) {
				if(graph[now][i] == 1 && !visit[i]) {
					visit[i] = true;
					queue.offer(i);
				}
			}
		}
	}
}