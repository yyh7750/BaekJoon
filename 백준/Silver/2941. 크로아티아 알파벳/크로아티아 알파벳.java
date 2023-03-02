import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();

		if (str.contains("c=")) {
			str = str.replace("c=", "1");
		}
		if (str.contains("c-")) {
			str = str.replace("c-", "1");
		}
		if (str.contains("dz=")) {
			str = str.replace("dz=", "1");
		}
		if (str.contains("d-")) {
			str = str.replace("d-", "1");
		}
		if (str.contains("lj")) {
			str = str.replace("lj", "1");
		}
		if (str.contains("nj")) {
			str = str.replace("nj", "1");
		}
		if (str.contains("s=")) {
			str = str.replace("s=", "1");
		}
		if (str.contains("z=")) {
			str = str.replace("z=", "1");
		}

		System.out.println(str.length());
	}
}