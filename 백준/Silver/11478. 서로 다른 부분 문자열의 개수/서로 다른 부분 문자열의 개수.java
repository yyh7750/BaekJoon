import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class Main {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] arr = br.readLine().split("");

		Set<String> set = new HashSet<>();
		for (int i = 0; i < arr.length - 1; i++) {
			if (i == arr.length - 2) {
				set.add(arr[i + 1]);
			}
			set.add(arr[i]);

			String str = arr[i];
			for (int j = i + 1; j < arr.length; j++) {
				str += arr[j];
				set.add(str);
			}
		}

		br.close();
		System.out.println(set.size());
	}
}