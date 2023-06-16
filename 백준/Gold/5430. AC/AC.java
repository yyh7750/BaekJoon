import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

/**
 * packageName    : baekjoon.gold5
 * fileName       : AC_5430
 * author         : yyh77
 * date           : 2023-06-15
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-06-15        yyh77       최초 생성
 */
public class Main {

    private static char[] commands;
    private static ArrayDeque<Integer> deque;
    private static StringBuilder sb;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        sb = new StringBuilder();
        int tc = Integer.parseInt(br.readLine());

        for (int t = 0; t < tc; t++) {

            commands = br.readLine().toCharArray();
            int length = Integer.parseInt(br.readLine());

            st = new StringTokenizer(br.readLine(), "[],");
            initQueue(length, st);

            solution();
        }

        System.out.println(sb.toString());

        br.close();
    }

    /**
     * 입력받은 문자열을 StringTokenizer로 자른 후 ArrayDeque로 바꿔주는 메소드
     *
     * @param length
     * @param st
     */
    private static void initQueue(int length, StringTokenizer st) {
        deque = new ArrayDeque<>();

        for (int i = 0; i < length; i++) {
            deque.add(Integer.parseInt(st.nextToken()));
        }
    }

    /**
     * 메인 로직이 담긴 메소드
     */
    private static void solution() {

        int rCnt = 0;

        for (char command : commands) {

            // 뒤집기 함수일 경우
            if (command == 'R') {
                rCnt++;
                continue;
            }
            // 삭제 함수일 경우
            else {
                // 비어 있으면 error 출력
                if (deque.isEmpty()) {
                    sb.append("error\n");
                    return;
                } //
                else {
                    // 정방향인 경우
                    if (rCnt % 2 == 0) {
                        // 데크의 첫번째 원소를 지운다.
                        deque.pollFirst();
                    }
                    // 배열이 뒤집어진 경우
                    else {
                        // 데크의 마지막 원소를 지운다.
                        deque.pollLast();
                    }
                }
            }
        }

        int length = deque.size() - 1;

        sb.append("[");

        if (!deque.isEmpty()) {
            // 정방향일 경우
            if (rCnt % 2 == 0) {
                // 그대로 출력해준다.
                for (int i = 0; i < length; i++) {
                    sb.append(deque.pollFirst()).append(",");
                }
            }
            // 역순일 경우
            else {
                // 거꾸로 출력해준다.
                for (int i = 0; i < length; i++) {
                    sb.append(deque.pollLast()).append(",");
                }
            }
            sb.append(deque.poll());
        }
        sb.append("]").append("\n");
    }
}
