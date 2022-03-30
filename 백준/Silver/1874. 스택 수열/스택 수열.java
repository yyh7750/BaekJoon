import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {

	public static void main(String[] args) {

		BufferedReader br = null;
		StringBuilder sb = null;

		Stack<Integer> stack = null;
		int n = 0;
		// 중간에 스택 진행상황에 대한 변수
		int temp = 0;

		try {

			br = new BufferedReader(new InputStreamReader(System.in));
			n = Integer.parseInt(br.readLine());

			stack = new Stack<Integer>();
			sb = new StringBuilder();

			for (int i = 0; i < n; i++) {

				int num = Integer.parseInt(br.readLine());

				if (num > temp) {
					// 오름차순으로 스택에 push
					for (int j = temp + 1; j <= num; j++) {
						stack.push(j);
						sb.append("+\n");
					}
					// 다음 push 해야할 값 입력
					temp = num;
				} //
				else if (stack.peek() != num) {
					System.out.println("NO");
					return;
				} //

				stack.pop();
				sb.append("-\n");
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