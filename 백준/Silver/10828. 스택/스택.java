import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) {

		Stack<Integer> stack = null;
		BufferedReader br = null;
		StringTokenizer st = null;
		int n = 0;

		try {

			br = new BufferedReader(new InputStreamReader(System.in));
			n = Integer.parseInt(br.readLine()); // 명령어 줄 수 입력
			stack = new Stack<Integer>();

			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				String command = st.nextToken();

				switch (command) {
				//
				case "push":
					int num = 0;
					if (st.hasMoreTokens()) {
						num = Integer.parseInt(st.nextToken());
					}
					stack.push(num);
					break;
				//
				case "pop":
					if(stack.isEmpty()) {
						System.out.println(-1);
					} //
					else {
						int pop = stack.pop();
						System.out.println(pop);
					}
					break;

				case "size":
					System.out.println(stack.size());
					break;
				//
				case "empty":
					if (stack.isEmpty()) {
						System.out.println(1);
					} //
					else {
						System.out.println(0);
					}
					break;
				//
				case "top":
					if(stack.isEmpty()) {
						System.out.println(-1);
					} //
					else {
						System.out.println(stack.peek());
					}
					break;
				}
			}
		} //
		catch (IOException ioException) {
			System.out.println("입력 오류. 다시 실행해주세요.");
		} //
		catch (NumberFormatException numberException) {
			System.out.println("숫자를 입력해주세요. 다시 실행해주세요.");
		}
	}
}