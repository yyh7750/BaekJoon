import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		String str = br.readLine();

		int[][] sum = new int[str.length()][26];
		sum[0][str.charAt(0) - 'a']++;

		for (int i = 1; i < str.length(); i++) {
			int temp = str.charAt(i) - 'a';

			for (int j = 0; j < 26; j++) {
				sum[i][j] = sum[i - 1][j];
			}

			sum[i][temp]++;
		}

		int tc = Integer.parseInt(br.readLine());

		for (int t = 0; t < tc; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			char alphabet = st.nextToken().charAt(0);
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());

			if (start == 0) {
				sb.append(sum[end][alphabet - 'a']).append("\n");
			} //
			else {
				sb.append(sum[end][alphabet - 'a'] - sum[start - 1][alphabet - 'a']).append("\n");
			}
		}

		br.close();
		System.out.println(sb);
	}
}