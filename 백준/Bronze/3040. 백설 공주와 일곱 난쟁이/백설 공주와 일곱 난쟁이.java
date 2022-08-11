import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	private static int[] arr;
	private static int[] sel;

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		arr = new int[9];
		sel = new int[7];

		for (int i = 0; i < 9; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		
		comb(0,0);
	}

	private static void comb(int idx, int k) {
		int sum = 0;
		
		if (k == sel.length) {
			for (int i = 0; i < sel.length; i++) {
				sum += sel[i];
			}
			if(sum == 100) {
				for(int i : sel) {
					System.out.println(i);
				}
			}
			
			return;
		}

		for (int i = idx; i < arr.length; i++) {
			sel[k] = arr[i];
			comb(i + 1, k + 1);
		}
	}
}