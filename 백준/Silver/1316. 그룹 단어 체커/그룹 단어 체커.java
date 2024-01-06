import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int ans = 0;

        for (int i = 0; i < N; i++) {
            if (isGroupWordCheck(br.readLine())) {
                ans++;
            }
        }

        System.out.println(ans);
        br.close();
    }

    /**
     * 연속된 문자열이 있는지 검사하는 메소드
     * 같은 문자열이 연속되게 나오는 경우만 true 리턴.
     * aba의 경우 a가 중복으로 나왔지만 연속되지 않았기 때문에 fasle
     * boolean 배열을 아스키코드 인덱스 값으로 두고, 해당 문자열이 나왔다면 해당 인덱스를 true로 체크
     * 현재값과 이전값을 비교하면서 현재값과 이전값이 같을 경우엔 boolean 배열이 true여도 괜찮지만,
     * 값이 다른데, 현재값의 인덱스에 위치하는 boolean 배열 값이 true면 안된다.
     * <p>
     * happy : true
     * abcabc : false
     *
     * @param str
     * @return
     */
    private static boolean isGroupWordCheck(String str) {

        // 알파벳의 개수만큼 배열 선언. a~z 26개
        boolean[] checkArr = new boolean[26];

        // 문자열이 하나일 경우 예외 처리
        if (str.length() == 1) {
            return true;
        }

        // 첫번째 값 true 처리
        checkArr[str.charAt(0) - 'a'] = true;
        for (int i = 1; i < str.length(); i++) {
            char now = str.charAt(i);
            char prev = str.charAt(i - 1);

            // 현재값과 이전값이 같지 않을 경우
            if (now != prev) {
                // 현재값이 true로 되어 있다면 중복해서 나온 경우이므로 false
                if (checkArr[now - 'a']) {
                    return false;
                } //
                else {
                    checkArr[now - 'a'] = true;
                }
            } //
            else {
                checkArr[now - 'a'] = true;
            }
        }

        return true;
    }
}
