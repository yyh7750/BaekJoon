import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * @packageName : 단계별_문제풀이.정렬
 * @description : 좌표가 n개만큼 주어질때 낮은 크기순으로 순위를 매기는 프로그램
 * @author : Younghun Yu
 * @date : 2022.06.02
 * ===========================================================
 *     DATE      AUTHOR      NOTE
 * -----------------------------------------------------------
 * 2022.06.02  Younghun Yu  최초 생성
 */
public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		int n = Integer.parseInt(br.readLine());
		int[] arr = new int[n];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		int[] copy = arr.clone();
		Arrays.sort(copy);

		Map<Integer, Integer> map = new HashMap<>();

		int result = 0;
		for (int key : copy) {
			if (!map.containsKey(key)) {
				map.put(key, result);
				result++;
			}
		}

		for (int key : arr) {
			sb.append(map.get(key)).append(" ");
		}

		br.close();
		System.out.println(sb);
	}
}