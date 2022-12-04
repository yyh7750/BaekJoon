import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

class Solution {
    
	public int solution(String str1, String str2) {

		str1 = str1.toLowerCase();
		str2 = str2.toLowerCase();

		List<String> list1 = new ArrayList<>();
		List<String> list2 = new ArrayList<>();

		// 문자열에 대한 집합 만들기
		isPatternChecked(str1, list1);
		isPatternChecked(str2, list2);

		Collections.sort(list1);
		Collections.sort(list2);

		List<String> intersectionList = new ArrayList<>(); // 교집합
		List<String> unionList = new ArrayList<>(); // 합집합

		getIntersection(unionList, intersectionList, list1, list2);
		getUnion(unionList, list2);

		return getJaccardSimilarity((double) intersectionList.size(), (double) unionList.size());
	}

	/**
	 * @methodName : getJaccardSimilarity
	 * @Description : 자카드 유사도 구하기
	 * 
	 * @param intersectionSize : 교집합 크기
	 * @param unionSize        : 합집합 크기
	 * @return int : 자카드 유사도 값
	 *
	 * @date 2022. 12. 5.
	 * @author 유영훈
	 */
	private int getJaccardSimilarity(double intersectionSize, double unionSize) {
		double jakard = 0.0;

		if (unionSize == 0.0) {
			jakard = 1;
		} //
		else {
			jakard = intersectionSize / unionSize;
		}

		return (int) (jakard * 65536);
	}

	/**
	 * @methodName : getUnion
	 * @Description : list1을 기준으로 교집합을 만들었으니 list2를 기준으로 합집합 완성
	 * 
	 * @param unionList
	 * @param list2
	 * @return void
	 *
	 * @date 2022. 12. 4.
	 * @author 유영훈
	 */
	private void getUnion(List<String> unionList, List<String> list2) {
		for (String str : list2) {
			unionList.add(str);
		}
	}

	/**
	 * @methodName : getIntersection
	 * @Description : 교집합을 구하기 위한 메소드
	 * 
	 * @param unionList        : 합집합 리스트
	 * @param intersectionList : 교집합 리스트
	 * @param list1
	 * @param list2
	 * @return void
	 *
	 * @date 2022. 12. 4.
	 * @author 유영훈
	 */
	private void getIntersection(List<String> unionList, List<String> intersectionList, List<String> list1,
			List<String> list2) {

		// List.remove -> boolean 반환 타입 : 대상이 존재하여 삭제되면 true, 아니면 false
		for (String str : list1) {
			if (list2.remove(str)) {
				intersectionList.add(str);
			}
			unionList.add(str);
		}
	}

	/**
	 * @methodName : isPatternChecked
	 * @Description : 문자열 검사를 통해 알파벳으로만 이루어져 있는지 확인 후 집합에 포함하는 메소드
	 * 
	 * @param str
	 * @param list
	 * @return void
	 *
	 * @date 2022. 12. 4.
	 * @author 유영훈
	 */
	private void isPatternChecked(String str, List<String> list) {
		for (int i = 0; i < str.length() - 1; i++) {
			String subStr = str.substring(i, i + 2);

			// 정규식을 통해 알파벳으로만 이루어져 있는지 확인
			if (Pattern.matches("^[a-z]*$", subStr)) {
				list.add(subStr);
			}
		}
	}
//
//	public static void main(String[] args) {
//		뉴스_클러스터링 sol = new 뉴스_클러스터링();
//		
//		int ans = sol.solution("FRANCE", "french");
//		System.out.println(ans);
//	}
}