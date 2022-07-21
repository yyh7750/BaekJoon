import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		int L = Integer.parseInt(br.readLine());
		int pL = Integer.parseInt(br.readLine());

		int[] arr = new int[L];
		int[] aArr = new int[pL];
		int[] bArr = new int[pL];
		int result1 = 0;
		int result2 = 0;
		
		int max = 0; // 예상값
		int real = 0; // 실제값
		for (int i = 0; i < pL; i++) {
			st = new StringTokenizer(br.readLine());
			aArr[i] = Integer.parseInt(st.nextToken());
			bArr[i] = Integer.parseInt(st.nextToken());
		
			// 예상값 구하기
			if(bArr[i] - aArr[i] > max) {
				max = bArr[i] - aArr[i];
				result1 = i + 1;
			}
			
			// 실제 값 구하기
			int cnt = 0;
			for(int j = aArr[i] - 1; j < bArr[i]; j++) {
				if(arr[j] == 0) {
					arr[j] = i + 1;
					cnt++;
				}
			}
			
			// 예상값과 차이가 있다면 갱신
			if(real < cnt) {
				result2 = i + 1;
				real = cnt;
			}
		}
		
		System.out.println(result1);
		System.out.println(result2);
	}
}