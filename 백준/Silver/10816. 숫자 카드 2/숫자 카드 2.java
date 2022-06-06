import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @packageName : 집합과_맵
 * @description : M개의 수에 대해서, 각 수가 적힌 숫자 카드를 몇 개 가지고 있는지를 출력하는 프로그램
 * 이분탐색을 이용해야 하는 문제.(Counting Sort로 해결) 이분탐색 공부 필요.
 * @author : Younghun Yu
 * @date : 2022.06.06
 * ===========================================================
 *     DATE      AUTHOR      NOTE
 * -----------------------------------------------------------
 * 2022.06.06  Younghun Yu  최초 생성
 */
public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		int n = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());

		int[] arr = new int[n];
		int[] counting = new int[20000001];

		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());

			if (arr[i] < 0) {
				int abs = Math.abs(arr[i]) + 10000000;
				counting[abs]++;
			} //
			else {
				counting[arr[i]]++;
			}
		}

		int m = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());

		for (int i = 0; i < m; i++) {
			int num = Integer.parseInt(st.nextToken());

			if (num < 0) {
				num = Math.abs(num) + 10000000;
			}
			sb.append(counting[num]);
			sb.append(" ");
		}

		br.close();
		System.out.println(sb);
	}
}