import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * @packageName : 집합과_맵
 * @description : 대칭 차집합 개수를 구하는 공식 (A의 개수 + B의 개수 - 2 * 교집합의 개수)
 * @author : Younghun Yu
 * @date : 2022.06.08
 * ===========================================================
 *     DATE      AUTHOR      NOTE
 * -----------------------------------------------------------
 * 2022.06.08  Younghun Yu  최초 생성
 */
public class Main {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());

		Map<Integer, Integer> map = new HashMap<>();
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < a; i++) {
			map.put(Integer.parseInt(st.nextToken()), 0);
		}

		int count = 0;

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < b; i++) {
			if (map.containsKey(Integer.parseInt(st.nextToken()))) {
				count++;
			}
		}

		br.close();
		System.out.println(a + b - 2 * count);
	}
}