import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {

	private static char[] arr;
	private static int S, P;
	private static Map<Character, Integer> map = new HashMap<>();
	private static int[] require = new int[4];

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		st = new StringTokenizer(br.readLine());
		S = Integer.parseInt(st.nextToken());
		P = Integer.parseInt(st.nextToken());
		arr = br.readLine().toCharArray();

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 4; i++) {
			require[i] = Integer.parseInt(st.nextToken());
		}

		br.close();

		map.put('A', 0);
		map.put('C', 0);
		map.put('G', 0);
		map.put('T', 0);

		for (int i = 0; i < P; i++) {
			map.put(arr[i], map.get(arr[i]) + 1);
		}

		System.out.println(solution());
	}

	private static int solution() {
		int cnt = 0;

		if (isChecked())
			cnt++;

		for (int i = 0; i < S - P; i++) {

			char prevc = arr[i];
			char nextc = arr[i + P];
			map.put(prevc, map.get(prevc) - 1);
			map.put(nextc, map.get(nextc) + 1);

			if (isChecked())
				cnt++;

		}

		return cnt;
	}

	private static boolean isChecked() {
		for (char key : map.keySet()) {
			if (key == 'A') {
				if (map.get(key) < require[0]) {
					return false;
				}
			} //
			else if (key == 'C') {
				if (map.get(key) < require[1]) {
					return false;
				}
			} //
			else if (key == 'G') {
				if (map.get(key) < require[2]) {
					return false;
				}
			} //
			else if (key == 'T') {
				if (map.get(key) < require[3]) {
					return false;
				}
			}
		}

		return true;
	}
}