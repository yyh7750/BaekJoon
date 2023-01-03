import java.util.Collections;
import java.util.PriorityQueue;

public class Solution {

	public int[] solution(String[] operations) {
		PriorityQueue<Integer> lowestQ = new PriorityQueue<>();
		PriorityQueue<Integer> highestQ = new PriorityQueue<>(Collections.reverseOrder());

		for (int i = 0; i < operations.length; i++) {
			String operation = operations[i];

			// 연산 명령어
			String cal = operation.substring(0, 1);
			// 숫자, 최댓값 삭제, 최솟값 삭제
			String val = operation.substring(2);

			if (cal.equals("I")) {
				lowestQ.offer(Integer.parseInt(val));
				highestQ.offer(Integer.parseInt(val));
			} //
			else {
				if (val.equals("-1")) {
					if (!lowestQ.isEmpty()) {
						int element = lowestQ.poll();
						highestQ.remove(element);
					}
				} //
				else {
					if (!highestQ.isEmpty()) {
						int element = highestQ.poll();
						lowestQ.remove(element);
					}
				}
			}
		}

		int[] answer = new int[2];
		if (!lowestQ.isEmpty() && !highestQ.isEmpty()) {
			answer[0] = highestQ.peek();
			answer[1] = lowestQ.peek();
		} //
		else {
			
		}

		return answer;
	}
//
//	public static void main(String[] args) {
//		이중우선순위큐 sol = new 이중우선순위큐();
//		int[] ans = sol.solution(new String[] { "I 16", "I -5643", "D -1", "D 1", "D 1", "I 123", "D -1" });
//		System.out.println(Arrays.toString(ans));
//	}
}