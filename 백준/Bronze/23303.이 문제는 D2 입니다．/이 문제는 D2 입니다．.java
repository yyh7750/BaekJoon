import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		System.out.println(containsD2(str));
	}

	public static String containsD2(String str) {
		String result = null;

		result = str.toUpperCase();

		if (result.contains("D2")) {
			result = "D2";
		} //
		else {
			result = "unrated";
		}

		return result;
	}

}