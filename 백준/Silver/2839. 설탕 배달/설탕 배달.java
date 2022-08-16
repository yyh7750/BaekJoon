import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int sugar = Integer.parseInt(br.readLine());
		int result = 0;

		while (sugar > 0) {
			if (sugar % 5 == 0) {
				result += sugar / 5;
				break;
			}

			sugar -= 3;
			result++;
		}

		if (sugar < 0) {
			result = -1;
		}

		br.close();
		System.out.println(result);
	}
}