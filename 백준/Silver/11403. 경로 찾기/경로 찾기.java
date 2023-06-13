import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * packageName    : baekjoon.silver1
 * fileName       : 경로찾기_11403
 * author         : yyh77
 * date           : 2023-06-13
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-06-13        yyh77       최초 생성
 */
public class Main {

    private static int N;
    private static int[][] arr;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        N = Integer.parseInt(br.readLine());

        arr = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        floydWarshall();
        printArr();

        br.close();
    }

    /**
     * 플로이드 워셜 : 모든 정점에서 다른 모든 정점까지의 최단 거리 구하기
     * dist 배열을 구하여 구현하지만 문제에서는 인접 리스트가 dist 배열로 구현되어 있음
     * 문제 자체가 최단 거리가 아닌, 연결되어 있느냐에 대한 문제이기 때문에
     * i -> k로 가면서 k -> j로 갈 수 있다면 i -> j로도 갈 수 있다.
     * arr[i][k] == 1 && arr[k][j] == 1인 경우 arr[i][j]를 1로 하면된다.
     */
    private static void floydWarshall() {

        // 거쳐가는 노드 k
        for (int k = 0; k < N; k++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (arr[i][k] == 1 && arr[k][j] == 1) {
                        arr[i][j] = 1;
                    }
                }
            }
        }
    }

    private static void printArr() {

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                sb.append(arr[i][j]).append(" ");
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }
}
