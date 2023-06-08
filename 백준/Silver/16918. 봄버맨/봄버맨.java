import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * packageName    : baekjoon.gold5
 * fileName       : 봄버맨_48741614
 * author         : yyh77
 * date           : 2023-06-07
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-06-07        yyh77       최초 생성
 */
public class Main {

    private static class Loc {
        int r, c;

        public Loc(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    private static int R, C, N, time = 1;
    private static int[][] map;
    private static int[] dr = {-1, 1, 0, 0};
    private static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        map = new int[R][C];
        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            String str = st.nextToken();
            for (int j = 0; j < C; j++) {
                if (str.charAt(j) == '.') {
                    map[i][j] = 0;
                } //
                else {
                    map[i][j] = 3;
                }
            }
        }

        // time 증가시키면서 폭탄 깔기, 터트리기 메소드 수행
        while (time++ < N) {
            if (time % 2 == 0) {
                setBomb();
            } //
            else {
                bomb();
            }
        }

        // 출력
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] > 0) {
                    sb.append('O');
                } //
                else {
                    sb.append('.');
                }
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    /**
     * 폭탄 터트리기
     * time 값과 map 원소값 비교하여 터트린 후 0으로 바꾼다.
     * 연쇄 반응이 있기 때문에 리스트에 담은 후 한번에 처리해준다.
     */
    private static void bomb() {

        List<Loc> bombList = new ArrayList<>();

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {

                if (map[i][j] == time) {

                    // 터졌으니 값 바꿔주고
                    map[i][j] = 0;

                    for (int d = 0; d < 4; d++) {
                        int nr = i + dr[d];
                        int nc = j + dc[d];

                        if (isChecked(nr, nc)) {
                            // 연쇄 폭탄 터질것들 넣어주기
                            bombList.add(new Loc(nr, nc));
                        }
                    }
                }
            }
        }

        for (Loc loc : bombList) {
            map[loc.r][loc.c] = 0;
        }
    }

    /**
     * time을 기준으로 +3초 적용된 폭탄 깔기
     */
    private static void setBomb() {

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] == 0) {
                    map[i][j] = time + 3;
                }
            }
        }
    }

    private static boolean isChecked(int nr, int nc) {
        if (nr >= 0 && nr < R && nc >= 0 && nc < C) {
            return true;
        }
        return false;
    }
}
