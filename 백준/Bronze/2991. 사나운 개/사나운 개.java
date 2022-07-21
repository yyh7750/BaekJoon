import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		st = new StringTokenizer(br.readLine());
		int a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		int d = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		int[] person = new int[3];
		for (int i = 0; i < person.length; i++) {
			person[i] = Integer.parseInt(st.nextToken());
		}
		
		br.close();

		int[] result = new int[3];
		
		for(int i = 0; i < person.length; i++) {
			if(person[i] % (a + b) > 0 && person[i] % (a + b) <= a) {
				result[i]++;
			}
			if(person[i] % (c + d) > 0 && person[i] % (c + d) <= c) {
				result[i]++;
			}
			System.out.println(result[i]);
		}
	}
}