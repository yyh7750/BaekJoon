import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	
	private static StringBuilder sb = new StringBuilder();
	private static int cnt = 0;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		hanoi(n, 1, 2, 3);
		
		System.out.println(cnt);
		System.out.println(sb);
	}
	
	private static void hanoi(int n, int start, int temp, int end) {
		if(n <= 0) {
			return;
		}
		cnt++;
		hanoi(n-1, start, end, temp);
		sb.append(start).append(" ").append(end).append("\n");
		hanoi(n-1, temp, start, end);
	}
}