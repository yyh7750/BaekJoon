import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @packageName : 단계별_문제풀이.문자열
 * @description : 주어진 문자열을 크로아티아 알파벳으로 바꾸고 그 길이를 출력하는 프로그램
 * @author : Younghun Yu
 * @date : 2022.05.03
 * ===========================================================
 *     DATE      AUTHOR      NOTE
 * -----------------------------------------------------------
 * 2022.05.03  Younghun Yu  최초 생성
 */
public class Main {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		
		for(int i = 0; i < str.length(); i++) {
			if(str.contains("c=")) {
				str = str.replaceAll("c=", "a");
			}
			if(str.contains("c-")) {
				str = str.replaceAll("c-", "a");
			}
			if(str.contains("dz=")) {
				str = str.replaceAll("dz=", "a");
			}
			if(str.contains("d-")) {
				str = str.replaceAll("d-", "a");
			}
			if(str.contains("lj")) {
				str = str.replaceAll("lj", "a");
			}
			if(str.contains("nj")) {
				str = str.replaceAll("nj", "a");
			}
			if(str.contains("s=")) {
				str = str.replaceAll("s=", "a");
			}
			if(str.contains("z=")) {
				str = str.replaceAll("z=", "a");
			}
		}
		
		System.out.println(str.length());
	}
}