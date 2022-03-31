import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * @packageName : 단계별_문제풀이.스택
 * @description : 골드4 수열이 주어질 경우 큰 수를 출력하는 프로그램
 * @author : Younghun Yu
 * @date : 2022.03.31
 *       =========================================================== DATE AUTHOR
 *       NOTE -----------------------------------------------------------
 *       2022.03.31 Younghun Yu 최초 생성
 */
public class Main {

	private static Stack<Integer> stack = null;

	public static void main(String[] args) {

		BufferedReader br = null;
		StringTokenizer st = null;
		int n = 0;
		int[] arr = null;

		try {

			br = new BufferedReader(new InputStreamReader(System.in));
			n = Integer.parseInt(br.readLine());
			arr = new int[n];

			st = new StringTokenizer(br.readLine());
			br.close();

			stack = new Stack<Integer>();
			for (int i = 0; i < n; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			
			System.out.println(solution(arr));

		} //
		catch (IOException ioException) {
			System.out.println("입력 오류. 다시 실행해주세요.");
		} //
		catch (NumberFormatException numberException) {
			System.out.println("숫자를 입력해주세요. 다시 실행해주세요.");
		}
	}

	/**
	 * @methodName : solution
	 * @description :
	 *
	 * @author : Younghun Yu
	 * @date : 2022.03.31
	 */
	private static StringBuilder solution(int[] arr) {

		int[] result = new int[arr.length];
		stack.push(0);
		StringBuilder sb = new StringBuilder();

		for (int i = 1; i < arr.length; i++) {
			while(!stack.isEmpty() && arr[stack.peek()] < arr[i]) {
				result[stack.peek()] = arr[i];
				stack.pop();
			}
			
			stack.push(i);
		}
		
		while(!stack.isEmpty()) {
			result[stack.peek()] = -1;
			stack.pop();
		}
		
		for(int i : result) {
			sb.append(i);
			sb.append(" ");
		}
		
		return sb;
	}
}