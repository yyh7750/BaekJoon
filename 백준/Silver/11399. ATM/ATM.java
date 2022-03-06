import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) {

		// 입력을 받기 위한 BufferedReader
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// N명의 사람 수
		int personNum = 0;
		
		// 대기열 배열
		Integer[] waitArr = null;
		
		// 총 대기 시간
		int totalTime = 0;
		// 각자 대기시간
		int waitTime = 0;
		
		try {
			
			personNum = Integer.parseInt(br.readLine());
			waitArr = new Integer[personNum];
			
			// 대기열에 들어갈 배열을 입력받기 위한 StringTokenizer
			StringTokenizer st = new StringTokenizer(br.readLine());

			// 배열 생성
			int i = 0;
			while(personNum-- > 0) {
				waitArr[i] = Integer.parseInt(st.nextToken());
				i++;
			}
			
			Arrays.sort(waitArr);
			
			for(int j : waitArr) {
				waitTime += j;
				totalTime += waitTime;
			}
			
			System.out.println(totalTime);
			
		} catch (IOException e) {
			System.out.println("입력 오류 발생 다시 시도해 주세요.");
		} catch (NumberFormatException e) {
			System.out.println("숫자를 입력해주세요.");
		}
	}
}