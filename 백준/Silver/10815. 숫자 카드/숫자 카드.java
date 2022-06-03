import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @packageName : 집합과_맵
 * @description : https://st-lab.tistory.com/267 - 이분탐색에 대한 공부 필요
 * @author : Younghun Yu
 * @date : 2022.06.03
 * ===========================================================
 *     DATE      AUTHOR      NOTE
 * -----------------------------------------------------------
 * 2022.06.03  Younghun Yu  최초 생성
 */
public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int n = Integer.parseInt(br.readLine());
		int[] arr = new int[n];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(arr);

		int m = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());

		for (int i = 0; i < m; i++) {
			int key = Integer.parseInt(st.nextToken());

			// upperBound와 lowerBound의 차이 값을 구한다.
			sb.append(upperBound(arr, key) - lowerBound(arr, key)).append(' ');
		}
		System.out.println(sb);
	}

	private static int lowerBound(int[] arr, int key) {
		int low = 0;
		int high = arr.length;

		while (low < high) {

			int mid = (low + high) / 2;

			if (key <= arr[mid]) {
				high = mid;
			} //
			else {
				low = mid + 1;
			}
		}

		return low;
	}

	private static int upperBound(int[] arr, int key) {
		int low = 0;
		int high = arr.length;

		while (low < high) {
			int mid = (low + high) / 2;

			if (key < arr[mid]) {
				high = mid;
			} //
			else {
				low = mid + 1;
			}
		}

		return low;
	}
}