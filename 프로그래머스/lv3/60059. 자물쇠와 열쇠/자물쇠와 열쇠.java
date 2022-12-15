class Solution {

	int n, m, len;
	int[][] bigLock;

	/**
	 * @methodName : solution
	 * @Description
	 */
	// 1. 키는 자물쇠 기준으로 영역을 벗어나도 자물쇠의 구멍만 메우면 된다.
	// 2. 자물쇠의 처음과 끝 인덱스를 기준으로 키의 크기만큼에 해당하는 배열을 생성한다.
	// 3. 해당 배열 가운데에 자물쇠를 놓는다.
	// 4. 자물쇠와 키가 맞물리는지 확인한다.
	/**
	 * @param key
	 * @param lock
	 * @return boolean
	 *
	 * @date 2022. 12. 15.
	 * @author 유영훈
	 */
	public boolean solution(int[][] key, int[][] lock) {

		n = lock.length; // n*n 자물쇠 크기
		m = key.length; // m*m 키 크기
		len = n + (m * 2) - 2; // 크기를 늘릴 배열의 크기

		// 1, 2. n + (m * 2) - 2
		// 자물쇠에 맞춰볼 수 있도록 키의 크기에 맞춰 크기를 늘린 배열
		bigLock = new int[len][len];

		// 3. 크기를 늘린 배열에 자물쇠 모양 초기화
		for (int i = m - 1; i < m + n - 1; i++) {
			for (int j = m - 1; j < m + n - 1; j++) {
				bigLock[i][j] = lock[i - m + 1][j - m + 1];
			}
		}

		// 4. 확인 작업
		if (isCorrectKey(key)) {
			return true;
		}

		return false;
	}

	private boolean isCorrectKey(int[][] key) {
		// 키 크기만큼 검사 범위 설정
		// 3*3 크기일 경우 0,0 ~ 4,4
		for (int d = 0; d < 4; d++) {
			for (int i = 0; i < len - m + 1; i++) {
				for (int j = 0; j < len - m + 1; j++) {
					// key값을 lock 자리에 더해서 lock이 전부 1이면 통과 아니면 실패
					for (int r = 0; r < m; r++) {
						for (int c = 0; c < m; c++) {
							bigLock[i + r][j + c] += key[r][c];
						}
					}

					// lock에 해당하는 자리 값 확인
					boolean flag = true;
					loop: for (int r = m - 1; r < m + n - 1; r++) {
						for (int c = m - 1; c < m + n - 1; c++) {
							if (bigLock[r][c] != 1) {
								flag = false;
								break loop;
							}
						}
					}

					if (flag) {
						return true;
					}
					
					// 더했던 값 다시 빼주기
					for (int r = 0; r < m; r++) {
						for (int c = 0; c < m; c++) {
							bigLock[i + r][j + c] -= key[r][c];
						}
					}
				}
			}

			key = rotate(key);
		}

		return false;
	}

	private int[][] rotate(int[][] key) {
		int[][] rotateKey = new int[m][m];

		for (int i = 0; i < m; i++) {
			for (int j = 0; j < m; j++) {
				rotateKey[i][j] = key[m - 1 - j][i];
			}
		}

		return rotateKey;
	}
}