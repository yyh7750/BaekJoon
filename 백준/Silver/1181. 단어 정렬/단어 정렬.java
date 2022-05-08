import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @packageName : 단계별_문제풀이.정렬
 * @description : 문자열의 길이 순서대로 출력하고, 길이가 같을 경우 사전 순서대로 출력하는 프로그램
 * @author : Younghun Yu
 * @date : 2022.05.08
 * ===========================================================
 *     DATE      AUTHOR      NOTE
 * -----------------------------------------------------------
 * 2022.05.08  Younghun Yu  최초 생성
 */
public class Main {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int n = Integer.parseInt(br.readLine());

		String[] arr = new String[n];

		for (int i = 0; i < n; i++) {
			arr[i] = br.readLine();
		}

		Arrays.sort(arr, new Comparator<String>() {
			public int compare(String s1, String s2) {
				if (s1.length() == s2.length()) {
					// compareTo()를 이용하여 사전 순으로 정렬
					return s1.compareTo(s2);
				} else {
					// 음수 또는 0일 경우 그대로, 양수일 경우 변경
					return s1.length() - s2.length();
				}
			}
		});

		Set<String> set = new LinkedHashSet<>(Arrays.asList(arr));
		Iterator<String> iter = set.iterator();

		while (iter.hasNext()) {
			sb.append(iter.next()).append("\n");
		}

		System.out.println(sb);
	}
}