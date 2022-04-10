import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {

	public static void main(String[] args) {

		BufferedReader br = null;
		BufferedWriter bw = null;
		int n = 0;
		int[] counting = null;

		try {

			br = new BufferedReader(new InputStreamReader(System.in));
			bw = new BufferedWriter(new OutputStreamWriter(System.out));
			n = Integer.parseInt(br.readLine());

			counting = new int[10001];

			// 원소의 개수 저장
			for (int i = 0; i < n; i++) {
				counting[Integer.parseInt(br.readLine())]++;
			}
			
			br.close();

			// 원소의 수만큼 i 출력, 수의 범위 1 ~ 10000
			for (int i = 1; i < 10001; i++) {
				while (counting[i] > 0) {
					bw.write(i + "\n");
					counting[i]--;
				}
			}
			
			bw.flush();
			bw.close();

		} //
		catch (IOException ioException) {
			System.out.println("입력 오류. 다시 실행해주세요.");
		} //
		catch (NumberFormatException numberException) {
			System.out.println("숫자를 입력해주세요. 다시 실행해주세요.");
		}
	}
}