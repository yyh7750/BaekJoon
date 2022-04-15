import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @packageName : 단계별_문제풀이.정렬
 * @description : n개의 수가 주어졌을 때, 네 가지 통계값을 구하는 프로그램
 * @author : Younghun Yu
 * @date : 2022.04.15
 * ===========================================================
 *     DATE      AUTHOR      NOTE
 * -----------------------------------------------------------
 * 2022.04.15  Younghun Yu  최초 생성
 */
public class Main {

	public static void main(String[] args) {

		/**
		 * 네 가지 통계값
		 * 1. 산술평균	: n개의 수들의 합을 n으로 나눈 값. 소수점 이하 첫째 자리에서 반올림한 값 출력
		 * 2. 중앙값	: n개의 수들을 증가하는 순서로 나열했을 경우 그 중앙에 위치하는 값.
		 * 3. 최빈값	: n개의 수들 중 가장 많이 나타나는 값. 여러 개의 최빈값이 존재한다면 두 번째로 작은 값 출력.
		 * 4. 범위	: n개의 수들 중 최댓값과 최솟값의 차이
		 * -------------------------------------------------------------------------------
		 * n의 범위 	: 1 ≤ N ≤ 500,000
		 * n개의 숫자들은 절대로 4000을 넘지 않는다.
		 */

		BufferedReader br = null;
		StringBuilder sb = null;
		double avg = 0.0; // 산술평균
		int middle = 0; // 중앙값
		int mode = 0; // 최빈값
		int range = 0; // 범위
		int n = 0;
		int[] arr = null; // n개의 수들을 담을 배열(양수 배

		try {

			br = new BufferedReader(new InputStreamReader(System.in));
			sb = new StringBuilder();
			n = Integer.parseInt(br.readLine());
			arr = new int[n];

			double sum = 0.0;
			int max = Integer.MIN_VALUE;
			int min = Integer.MAX_VALUE;

			// n개의 수 배열 값 입력
			for (int i = 0; i < n; i++) {
				arr[i] = Integer.parseInt(br.readLine());

				sum += arr[i];
				max = Math.max(max, arr[i]);
				min = Math.min(min, arr[i]);
			}

			Arrays.sort(arr);

			br.close();

			avg = Math.round(sum / n);
			middle = arr[n / 2];
			mode = mode(arr);
			range = max - min;

			sb.append((int) avg).append("\n");
			sb.append(middle).append("\n");
			sb.append(mode).append("\n");
			sb.append(range);

			System.out.println(sb);

		} //
		catch (IOException ioException) {
			System.out.println("입력 오류. 다시 실행해주세요.");
			System.out.println(ioException.getMessage());
		} //
		catch (NumberFormatException numberException) {
			System.out.println("올바른 숫자를 입력해주세요. 다시 실행해주세요.");
			System.out.println(numberException.getMessage());
		}
	}

	/**
	 * @methodName : mode
	 * @description : 최빈값을 구하기 위한 메소드
	 * @param arr
	 * @return
	 *
	 * @author : Younghun Yu
	 * @date : 2022.04.15
	 */
	private static int mode(int[] arr) {

		int[] counting = new int[8001]; // 자연수의 범위 -4000 ~ 4000
		int max = Integer.MIN_VALUE;

		for (int i : arr) {
			// Counting sort
			if (i >= 0) {
				counting[i]++;
			} // 음수의 경우 자연수 4000 범위 밖의 index에 저장
			else {
				counting[Math.abs(i) + 4000]++;
			}
		}

		for (int i = 0; i < counting.length; i++) {
			if (counting[i] > max) {
				max = counting[i]; // 최빈값 저장
			}
		}

		// 최빈값을 담을 리스트
		List<Integer> list = new ArrayList<>();
		for (int i = 0; i < counting.length; i++) {
			int num = i;

			// 최빈값이 여러개일 경우
			if (counting[i] == max) {
				if (i > 4000) {
					num -= 4000;
					num *= -1;
					list.add(num);
				} //
				else {
					list.add(i);
				}
			}
		}
		Collections.sort(list);

		// 최빈값이 여러개일 경우 두번째로 작은 수 리턴
		return list.size() > 1 ? list.get(1) : list.get(0);
	}
}