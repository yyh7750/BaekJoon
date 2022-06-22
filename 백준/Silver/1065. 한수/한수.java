import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int num = Integer.parseInt(br.readLine());

		System.out.print(solution(num));
		br.close();
	}

	public static int solution(int num) {
		int count = 0;

		if (num < 100) {
			return num;
		} //
		else {
			count = 99;
			for (int i = 100; i <= num; i++) {
				int hun = i / 100;
				int ten = (i / 10) % 10;
				int one = i % 10;

				if ((hun - ten) == (ten - one)) {
					count++;
				}
			}
		}

		return count;
	}
}