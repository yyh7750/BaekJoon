import java.util.Arrays;
import java.util.PriorityQueue;

public class Solution {

	class Job implements Comparable<Job> {
		int start;
		int cost;

		public Job(int start, int cost) {
			super();
			this.start = start;
			this.cost = cost;
		}

		@Override
		public int compareTo(Job o) {
			return this.cost - o.cost;
		}
	}

	public int solution(int[][] jobs) {

		Arrays.sort(jobs, (o1, o2) -> o1[0] - o2[0]); // 시작시간 순서대로
		PriorityQueue<Job> pq = new PriorityQueue<>(); // 처리시간 순서대로

		int i = 0;
		int cur = 0; // 현재 시간
		int sum = 0;
		
		while (i < jobs.length || !pq.isEmpty()) {
			
			// 현재 처리 가능한 모든 작업 추가
			while (i < jobs.length && jobs[i][0] <= cur) {
				pq.add(new Job(jobs[i][0], jobs[i][1]));
				i++;
			}

			// 작업이 없을 경우
			if (pq.isEmpty()) {
				// 다음 작업의 시작시간으로 이동한다.
				cur = jobs[i][0];
			} //
			else {
				Job temp = pq.poll();
				// 요청부터 종료시간 누적
				sum += cur + (temp.cost - temp.start);
				cur += temp.cost;
			}
		}

		return sum / jobs.length;
	}
}