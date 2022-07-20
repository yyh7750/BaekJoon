import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		String[] str = br.readLine().split("");
		int N = str.length;

		br.close();
		
		// C보다 작거나 같으면서 최대한 큰 R 값을 찾기 위한 제곱근
		int root = (int) Math.sqrt(N);
		int r = 0;
		int c = 0;
		for (int i = 1; i <= root; i++) {
			if (N % i == 0) {
				r = i;
				c = N / i;
			}
		}
		
		// 세로로 입력
		int cnt = 0;
		String result[][] = new String[r][c];
		for (int i = 0; i < c; i++) {
			for (int j = 0; j < r; j++) {
				result[j][i] = str[cnt++];
			}
		}
		
		// 차례대로 출력
		for(int i = 0; i < r; i++) {
			for(int j = 0; j < c; j++) {
				sb.append(result[i][j]);
			}
		}
		
		System.out.println(sb);
	}
}