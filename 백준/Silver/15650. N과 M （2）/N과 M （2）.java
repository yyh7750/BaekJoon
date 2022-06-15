import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.io.IOException;

public class Main {

	public static int[] arr;
	public static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		arr = new int[m];

		dfs(n, m, 1, 0);
		
		br.close();
		System.out.println(sb);
	}

	public static void dfs(int n, int m, int cLocation, int depth) {
		if (depth == m) {
			for (int val : arr) {
				sb.append(val).append(' ');
			}
			sb.append('\n');
			return;
		}

		for (int i = cLocation; i <= n; i++) {
			arr[depth] = i;
			dfs(n, m, i + 1, depth + 1);
		}
	}
}