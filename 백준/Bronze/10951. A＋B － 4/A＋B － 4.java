import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		String str = null;
		
		while ((str = br.readLine()) != null && !str.isEmpty()) {
			st = new StringTokenizer(str, " ");

			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			sb.append(a + b).append("\n");
		}

		br.close();
		System.out.println(sb);
	}
}