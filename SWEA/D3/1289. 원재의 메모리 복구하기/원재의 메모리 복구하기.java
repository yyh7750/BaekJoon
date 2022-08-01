import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {

	private static char[] arr;
	private static int cnt;
	
	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		for(int tc = 1; tc <= T; tc++) {
			sb.append("#").append(tc).append(" ");
			
			arr = br.readLine().toCharArray();
			cnt = 0;
			recursion(0, '0');
			sb.append(cnt).append("\n");
		}
		
		br.close();
		System.out.println(sb);
	}
	
	private static void recursion(int n, char temp) {
		if(n >= arr.length) {
			return;
		}
		
		if(arr[n] != temp) {
			temp = arr[n];
			cnt++;
		}
		
		recursion(++n, temp);
	}
}