import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @packageName : 대학교_경진대회.연세대.아카라카
 * @description :
 * @author : Younghun Yu
 * @date : 2022.03.04
 *       =========================================================== DATE AUTHOR
 *       NOTE -----------------------------------------------------------
 *       2022.03.04 Younghun Yu 최초 생성
 */
public class Main {

	/**
	 * @methodName : main
	 * @description :
	 * @param args
	 *
	 * @author : Younghun Yu
	 * @throws IOException
	 * @date : 2022.03.04
	 */
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String palindrome = br.readLine();

		String[] arr = palindrome.split("");

		if (getPalindrome(arr)) {
			System.out.println("AKARAKA");
		} else {
			System.out.println("IPSELENTI");
		}
	}

	public static boolean getPalindrome(String[] arr) {

		int size = arr.length;

		if (size == 1) {
			return true;
		} //
		else if (size == 2) {
			if (arr[0].equals(arr[1])) {
				return true;
			} else {
				return false;
			}
		} //
		else {
			if (arr[0].equals(arr[size / 2 - 1])) {

				String[] arr2 = new String[size / 2];
				for (int j = 0; j < size / 2; j++) {
					arr2[j] = arr[j];
				}
				return getPalindrome(arr2);
			} //
			else {
				return false;
			}
		} //
	}
}