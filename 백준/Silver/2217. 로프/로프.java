import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	/**
	 * @methodName : main
	 * @description :
	 * @param args
	 *
	 * @author : Younghun Yu
	 * @date : 2022.03.12
	 */
	public static void main(String[] args) {
		
		BufferedReader br = null;
		StringTokenizer st = null;
		
		try {
			
			br = new BufferedReader(new InputStreamReader(System.in));
			st = new StringTokenizer(br.readLine());
			
			int n = Integer.parseInt(st.nextToken());
			int rope[] = new int[n];
			
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				rope[i] = Integer.parseInt(st.nextToken());
			}
			
			Arrays.sort(rope);

			int temp = 0;
			int max = 0;
			
			// 최대 중량 = 연결된 로프 수 * 연결된 로프 중 최저 중량
			for (int i = n - 1; i >= 0; i--) {
				temp = rope[i] * (n - i);
			
				if (max < temp)
					max = temp;
			}
		
			System.out.println(max);
		} //
		catch (IOException ioException) {
			System.out.println("입력 오류. 다시 실행해주세요.");
		} //
		catch (NumberFormatException numberException) {
			System.out.println("숫자를 입력해주세요. 다시 실행해주세요.");
		}
	}
}