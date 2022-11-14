import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	private static int L, C;
	private static String[] arr;
	private static String[] sel;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		L = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		arr = new String[C];
		sel = new String[L];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < C; i++) {
			arr[i] = st.nextToken();
		}

		Arrays.sort(arr);

		combintion(0, 0);
	}

	private static void combintion(int idx, int k) {
		if (k == L) {
			if (isChecked()) {
				for (int i = 0; i < L; i++) {
					System.out.print(sel[i]);
				}
				System.out.println();
			}
			return;
		}

		for (int i = idx; i < C; i++) {
			sel[k] = arr[i];
			combintion(i + 1, k + 1);
		}
	}

	public static boolean isChecked() {
		int moum = 0;
		int jaum = 0;

		for (String alphabet : sel) {
			if (alphabet.equals("a") || alphabet.equals("e") || alphabet.equals("i") || alphabet.equals("o")
					|| alphabet.equals("u")) {
				moum++;
			} //
			else {
				jaum++;
			}
		}

		if (moum >= 1 && jaum >= 2) {
			return true;
		}
		return false;
	}
}