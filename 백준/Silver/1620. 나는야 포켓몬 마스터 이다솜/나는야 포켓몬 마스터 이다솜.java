import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.regex.Pattern;

/**
 * @packageName : 집합과_맵
 * @description : 포켓몬 이름이 들어가면 해당 번호를, 번호가 주어지면 포켓몬 이름을 출력하는 프로그램
 * @author : Younghun Yu
 * @date : 2022.06.10
 * ===========================================================
 *     DATE      AUTHOR      NOTE
 * -----------------------------------------------------------
 * 2022.06.10  Younghun Yu  최초 생성
 */
public class Main {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		Map<String, Integer> map = new HashMap<>();
		String[] nameArr = new String[n];
		for (int i = 0; i < n; i++) {
			String name = br.readLine();
			map.put(name, i + 1);
			nameArr[i] = name;
		}

		String pattern = "^[0-9]*$";
		for (int i = 0; i < m; i++) {
			String key = br.readLine();
			boolean regex = Pattern.matches(pattern, key);

			if (regex) {
				int index = Integer.parseInt(key) - 1;
				sb.append(nameArr[index]).append("\n");
			} //
			else {
				sb.append(map.get(key)).append("\n");
			}
		}

		br.close();
		System.out.println(sb);
	}
}