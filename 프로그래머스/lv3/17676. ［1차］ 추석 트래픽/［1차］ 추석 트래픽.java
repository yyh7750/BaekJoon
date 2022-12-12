import java.text.SimpleDateFormat;
import java.util.Date;

class Solution {
    public static int solution(String[] lines) throws Exception {
		
		SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss.SSS");

		// 각 최대 처리량을 담을 배열
		int[] counts = new int[lines.length];
		int answer = 0;

		for (int i = 0; i < lines.length; i++) {
			// 이전 로그의 완료 시점
			String[] curLog = lines[i].split(" ");
			// 날짜 형식 파싱
			Date curLogEndDate = format.parse(curLog[1]);
			// 날짜 형식에서 시간 얻어오기
			long curLogEnd = curLogEndDate.getTime();

			// 해당 로그 보다 늦게 종료되는 로그 체크
			for (int j = i; j < lines.length; j++) {
				// 다음 로그의 시작시점
				String[] nextLog = lines[j].split(" ");
				Date nextLogEndDate = format.parse(nextLog[1]);

				// 처리 시간
				double sec = Double.parseDouble(nextLog[2].substring(0, nextLog[2].length() - 1));
				// 다음 로그의 종료시점 - 처리시간 + 1;
				long nextStart = (long) (nextLogEndDate.getTime() - sec * 1000 + 1);

				// 이전 로그의 종료 시점부터 + 1초 범위안에 시작되면 + 1
				if (curLogEnd + 1000 > nextStart) {
					counts[i] += 1;
					answer = Math.max(answer, counts[i]);
				}
			}
		}
		return answer;
	}
}