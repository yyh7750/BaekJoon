import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		Set<String> set = new HashSet<String>();
		for (int i = 0; i < n; i++) {
			set.add(br.readLine());
		}

		List<String> list = new ArrayList<String>();
		int count = 0;
		for (int i = 0; i < m; i++) {
			String str = br.readLine();

			if (set.contains(str)) {
				count++;
				list.add(str);
			}
		}

		Collections.sort(list);

		sb.append(count).append("\n");
		for (String s : list) {
			sb.append(s).append("\n");
		}

		br.close();
		System.out.println(sb);
	}
}