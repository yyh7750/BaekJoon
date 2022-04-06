import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) {

		BufferedReader br = null;
		int n = 0;
		int num = 666;
		int count = 0;
		
		try {
			
			br = new BufferedReader(new InputStreamReader(System.in));
			n = Integer.parseInt(br.readLine());
			
			br.close();
			
			while(count < n) {
				if(String.valueOf(num).contains("666")) {
					count++;
				}
				num++;
			}
			
			System.out.println(num - 1);
			
		} //
		catch (IOException ioException) {
			System.out.println("입력 오류. 다시 실행해주세요.");
		} //
		catch (NumberFormatException numberException) {
			System.out.println("숫자를 입력해주세요. 다시 실행해주세요.");
		}
	}
}