import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) {

		BufferedReader br = null;
		StringTokenizer st = null;
		int n = 0; // 테스트 데이터 개수

		try {

			br = new BufferedReader(new InputStreamReader(System.in));
			n = Integer.parseInt(br.readLine());
			
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				System.out.println(bracket(st.nextToken()));
			}
			br.close();
		} //
		catch (IOException ioException) {
			System.out.println("입력 오류. 다시 실행해주세요.");
		} //
		catch (NumberFormatException numberException) {
			System.out.println("숫자를 입력해주세요. 다시 실행해주세요.");
		}
	}

	/**
	 * @methodName : bracket
	 * @description : '('일 경우 스택에 저장, ')'일 경우 가장 상위 데이터 삭제.
	 * 				  // 삭제할 자료가 없는데 삭제를 할 경우 NO 리턴 
	 * @param token
	 * @return
	 *
	 * @author : Younghun Yu
	 * @date : 2022.03.28
	 */
	private static String bracket(String token) {
		Stack<Character> stack = new Stack<>();

		for (int i = 0; i < token.length(); i++) {
			
			if(token.charAt(i) == '(') {
				stack.add(token.charAt(i));
			} //
			else {
				if(stack.isEmpty()) {
					return "NO";
				}
				else {
					stack.pop();
				}
			}
		}
		
		return stack.isEmpty() ? "YES" : "NO";
	}
}