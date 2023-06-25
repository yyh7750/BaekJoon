import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * packageName    : baekjoon.gold4
 * fileName       : 문자열폭발_9935
 * author         : yyh77
 * date           : 2023-06-24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-06-24        yyh77       최초 생성
 */
public class Main {

    private static StringBuilder sb;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str = br.readLine();
        String bombStr = br.readLine();

        sb = new StringBuilder();

        bomb(str, bombStr);

        System.out.println(sb.length() == 0 ? "FRULA" : sb);

        br.close();
    }

    /**
     * StringBuilder을 이용하여 폭발 문자열 길이 이상만큼 길이가 채워지면 검사하여 폭발 진행
     *
     * @param str
     * @param bombStr
     */
    private static void bomb(String str, String bombStr) {

        for (int i = 0; i < str.length(); i++) {

            sb.append(str.charAt(i));

            // bombStr 길이 이상만큼 StringBuilder에 저장된다면 비교한다.
            if (sb.length() >= bombStr.length()) {

                boolean flag = false;

                for (int j = 0; j < bombStr.length(); j++) {

                    // 문자열 디버그를 위한 변수.
                    // 앞에 검사했던 문자열들은 필요 없으므로 StringBuilder의 길이 - 폭발문자열의 길이를 기준으로 검사 시작
                    char c1 = sb.charAt(sb.length() - bombStr.length() + j);
                    char c2 = bombStr.charAt(j);

                    // 폭발 문자열 비교
                    if (c1 != c2) {
                        flag = true;
                        break;
                    }
                }

                // 문자열 폭발 진행
                if (!flag) {
                    sb.delete(sb.length() - bombStr.length(), sb.length());
                }
            }
        }
    }
}
