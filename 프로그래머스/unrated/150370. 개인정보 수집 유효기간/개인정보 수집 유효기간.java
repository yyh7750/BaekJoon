import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

class Solution {
    public int[] solution(String today, String[] terms, String[] privacies) {
        StringTokenizer st = null;
            DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy.MM.dd");

            // today LocalDate로 변환
            LocalDate todayDate = LocalDate.parse(today, format);

            // terms 배열을 Map으로 변환하여 약관 종류를 key, 유효기간을 value로 설정
            Map<String, Integer> termsMap = new HashMap<>();
            for (String term : terms) {
                st = new StringTokenizer(term);
                termsMap.put(st.nextToken(), Integer.parseInt(st.nextToken()));
            }

            List<Integer> expirationPeriods = new ArrayList<>();
            for (int i = 0; i < privacies.length; i++) {
                st = new StringTokenizer(privacies[i]);
                LocalDate privacyDate = LocalDate.parse(st.nextToken(), format);
                String termsType = st.nextToken();

                // 약관 기간 (약정)
                int agreement = termsMap.get(termsType);

                privacyDate = privacyDate.plusMonths(agreement);
                if (privacyDate.isBefore(todayDate) || privacyDate.isEqual(todayDate)) {
                    expirationPeriods.add(i + 1);
                }
            }

            int[] answer = new int[expirationPeriods.size()];
            for (int i = 0; i < answer.length; i++) {
                answer[i] = expirationPeriods.get(i);
            }

            return answer;
    }
}