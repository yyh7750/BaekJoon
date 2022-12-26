import java.util.HashMap;
import java.util.Map;

public class Solution {

	class Person {
		String name;
		Person referral;
		int profit;

		public Person(String name) {
			this.name = name;
			this.referral = null;
			this.profit = 0;
		}
	}

	/**
	 * 추천인에게 10%의 수익 제공
	 * Tree 구조 생성 후 재귀 호출로 계산
	 *
	 * @param enroll   : 판매원의 이름이 담긴 배열
	 * @param referral : 추천인 이름이 담긴 배열
	 * @param seller   : 판매한 사람이 담긴 배열
	 * @param amount   : 판매 수량이 담긴 배열
	 *
	 * @return answer
	 *
	 * @author 유영훈
	 * @since 2021. 10. 9
	 */
	public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
		int[] answer = new int[enroll.length];

		// tree 구조로 나타낼 Map
		Map<String, Person> treeMap = new HashMap<>();

		// 최상위 트리 - 사장님
		treeMap.put("-", new Person("-"));

		// 판매원 이름 입력
		for (int i = 0; i < enroll.length; i++) {
			treeMap.put(enroll[i], new Person(enroll[i]));
		}

		// 판매원의 부모 입력
		for (int i = 0; i < referral.length; i++) {
			treeMap.get(enroll[i]).referral = treeMap.get(referral[i]);
		}

		// 수익금 계산
		for (int i = 0; i < seller.length; i++) {
			addProfit(treeMap.get(seller[i]), amount[i] * 100);
		}

		// 결과 입력
		for (int i = 0; i < enroll.length; i++) {
			answer[i] = treeMap.get(enroll[i]).profit;
		}

		return answer;
	}

	/**
	 * 수익금을 계산하기 위한 메소드
	 * 
	 * @param person : 판매원
	 * @param profit : 판매 갯수 * 100 -> 10% 떼기 전 금액
	 *
	 * @author 유영훈
	 * @since 2022. 12. 20
	 */
	public void addProfit(Person person, int profit) {
		// 추천인에게 줄 금액
		int tax = profit / 10;

		// 추천인이 있다면
		if (tax != 0 && person.referral != null) {
			// 수익금의 10% 추천인에게 지불
			person.profit += profit - tax;
			// 추천인의 추천인 -> 재귀
			addProfit(person.referral, tax);
		}
		// 없다면 수익금 분배하지 않고 본인 소유
		else {
			person.profit += profit;
		}
	}
}