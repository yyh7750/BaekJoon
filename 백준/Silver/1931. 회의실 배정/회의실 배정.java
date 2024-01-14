import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * packageName    : baekjoon.silver1
 * fileName       : 회의실배정_1931
 * author         : yyh77
 * date           : 2024-01-13
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-01-13        yyh77       최초 생성
 */
public class Main {

    private static class Meeting implements Comparable<Meeting> {

        int startTime, endTime;

        public Meeting(int startTime, int endTime) {
            this.startTime = startTime;
            this.endTime = endTime;
        }

        @Override
        public int compareTo(Meeting o) {
            if (this.endTime != o.endTime) {
                return this.endTime - o.endTime;
            }
            return this.startTime - o.startTime;
        }
    }

    private static Meeting[] meetings;
    private static int ans = 1;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        meetings = new Meeting[N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int startTime = Integer.parseInt(st.nextToken());
            int endTime = Integer.parseInt(st.nextToken());
            meetings[i] = new Meeting(startTime, endTime);
        }

        Arrays.sort(meetings);

        getMeetingCount();

        System.out.println(ans);
        br.close();
    }

    private static void getMeetingCount() {

        int endTime = meetings[0].endTime;

        for (int i = 1; i < meetings.length; i++) {

            Meeting meeting = meetings[i];

            if (endTime <= meeting.startTime) {
                endTime = meeting.endTime;
                ans++;
            }
        }
    }
}
