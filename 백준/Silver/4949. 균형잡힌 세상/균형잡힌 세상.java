import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {

	public static void main(String[] args) {

		BufferedReader br = null;
		String str = null;
		StringBuilder sb = null;

		try {

			br = new BufferedReader(new InputStreamReader(System.in));
			sb = new StringBuilder();

			while (true) {
				str = br.readLine();

				if (str.equals(".")) {
					break;
				} //
				else {
					sb.append(bracket(str)).append("\n");
				}
			}

			br.close();
			
			System.out.println(sb);
		} //
		catch (IOException ioException) {
			System.out.println("입력 오류. 다시 실행해주세요.");
		}
	}

	/**
	 * @methodName : bracket
	 * @description : 백준 '괄호'의 응용 문제.
	 * @param s
	 * @return
	 *
	 * @author : Younghun Yu
	 * @date : 2022.03.29
	 */
	private static String bracket(String s) {
		Stack<Character> stack = new Stack<>();

		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);

			// 시작 괄호 스택에 삽입
			if (c == '(' || c == '[') {
				stack.push(c);
			} // ')'일 경우
			else if (c == ')') {
				// 스택이 비어있거나 마지막 원소가 '('이 아닐 경우 no 리턴
				if(stack.isEmpty() || !stack.peek().equals('(')) {
					return "no";
				} // 스택에서 제거
				else {
					stack.pop();
				}
			} // ']'일 경우
			else if (c == ']') {
				// 스택이 비어있거나 마지막 원소가 ']'이 아닐 경우 no 리턴
				if(stack.isEmpty() || !stack.peek().equals('[')) {
					return "no";
				} // 스택에서 제거
				else {
					stack.pop();
				}
			}
		}

		if (stack.isEmpty()) {
			return "yes";
		} //
		else {
			return "no";
		}
	}
}