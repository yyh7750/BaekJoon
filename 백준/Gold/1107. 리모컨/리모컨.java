import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * packageName    : baekjoon.gold5
 * fileName       : 리모컨_1107
 * author         : yyh77
 * date           : 2023-06-02
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-06-02        yyh77       최초 생성
 */
public class Main {

    private static int N;
    private static boolean[] wrongBtn;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        wrongBtn = new boolean[10];

        if (M > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < M; i++) {
                // 고장난 버튼 true 처리
                int wrongNum = Integer.parseInt(st.nextToken());
                wrongBtn[wrongNum] = true;
            }
        }

        System.out.println(solution());

        br.close();
    }

    /**
     * 숫자를 누르지 않고 +, -만 눌러서 이동한 횟수와 숫자를 누르는걸 포함한 횟수를 비교하여 반환하는 메소드
     *
     * @return : 총 누른 횟수 중 가장 적은 값
     */
    private static int solution() {

        // 현재 보고 있는 채널 - N => 숫자를 클릭하지 않고 +, -로만 움직인 경우
        int ans = Math.abs(100 - N);

        // N 최대값이 500,000 이므로, 같은 자릿수 중 최대값 만큼 반복
        for (int i = 0; i <= 999999; i++) {
            // 숫자 누른 횟수
            int pressNum = getNumCnt(i);
            // 숫자를 눌렀을 때
            if (pressNum > 0) {
                // +, - 누르는 경우 연산해주고 비교해준다.
                int pressUpDown = Math.abs(i - N);
                ans = Math.min(ans, pressNum + pressUpDown);
            }
        }

        return ans;
    }

    /**
     * num에 해당하는 숫자를 눌렀을때 얼만큼 눌렀는지 반환하는 메소드
     * 고장난 버튼에 해당하는 숫자가 포함될 경우 0 반환
     *
     * @param num : 현재 누르고자 하는 숫자
     * @return : 누른 숫자 개수
     */
    private static int getNumCnt(int num) {

        int cnt = 0;

        if (num == 0) {
            if (wrongBtn[num]) {
                return 0;
            }
            return 1;
        }

        while (num > 0) {
            // 숫자 중 고장난 버튼에 해당하는 숫자가 있으면 안된다.
            if (wrongBtn[num % 10]) {
                return 0;
            }
            num /= 10;
            cnt++;
        }

        return cnt;
    }
}
