public class Solution {

	private static int answer;

	public int solution(int[] numbers, int target) {
		answer = 0;

		dfs(0, 0, numbers, target);

		return answer;
	}

	private void dfs(int idx, int sum, int[] numbers, int target) {
		if (idx == numbers.length) {
			if (sum == target) {
				answer++;
			}
			return;
		}

		dfs(idx + 1, sum + numbers[idx], numbers, target);
		dfs(idx + 1, sum - numbers[idx], numbers, target);
	}
}