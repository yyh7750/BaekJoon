import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {

	public static void main(String[] args) {

		BufferedReader br = null;
		int n = 0;
		Stack<Integer> stack = null;

		try {

			br = new BufferedReader(new InputStreamReader(System.in));
			n = Integer.parseInt(br.readLine());
			stack = new Stack<Integer>();

			for (int i = 0; i < n; i++) {
				int num = Integer.parseInt(br.readLine());
				if (num > 0) {
					stack.push(num);
				} //
				else {
					stack.pop();
				}
			}

			int sum = 0;
			for (int i = 0; i < stack.size(); i++) {
				sum += stack.get(i);
			}

			System.out.println(sum);

		} //
		catch (IOException ioException) {
			System.out.println("입력 오류. 다시 실행해주세요.");
		} //
		catch (NumberFormatException numberException) {
			System.out.println("숫자를 입력해주세요. 다시 실행해주세요.");
		}
	}
}