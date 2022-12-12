class Solution
{
	public int solution(String s) {

		for (int i = s.length(); i > 0; i--) {
			// 처음 기준으로 검사 시작
			loop: for (int j = 0; j + i <= s.length(); j++) {
				// 단어가 팰린드롬인지 검사하기 위한 반복문
				for (int k = 0; k < i / 2; k++) {
					// 양쪽 끝으로 하나씩 검사
					if (s.charAt(j + k) != s.charAt(j + i - k - 1)) {
						continue loop;
					}
				}
				return i;
			}
		}
		return 1;
	}
}