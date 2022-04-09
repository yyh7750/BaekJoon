import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		
		BufferedReader br = null;
		int n = 0;
		List<Integer> list = null;
		StringBuilder sb = null;

		try {

			br = new BufferedReader(new InputStreamReader(System.in));
			n = Integer.parseInt(br.readLine());
			list = new ArrayList<>();

			for (int i = 0; i < n; i++) {
				list.add(Integer.parseInt(br.readLine()));
			}

			br.close();

			/**
			 *  Collections.sort()는 합병정렬 및 삽입정렬 알고리즘을 사용한다.
			 *  합병정렬의 최선, 최악의 경우 O(nlogn)를 보장하고
			 *  삽입정렬의 최선의 경우 O(n), 최악의 경우 O(n^2)를 보장한다.
			 *  즉, 합병정렬의 최악의 경우와 삽입정렬의 최선의 경우를 포함한 알고리즘이다.
			 */
			Collections.sort(list);
			
			sb = new StringBuilder();

			for (int i : list) {
				sb.append(i).append("\n");
			}
			
			System.out.println(sb);

		} //
		catch (IOException ioException) {
			System.out.println("입력 오류. 다시 실행해주세요.");
		} //
		catch (NumberFormatException numberException) {
			System.out.println("숫자를 입력해주세요. 다시 실행해주세요.");
		}
	}
}