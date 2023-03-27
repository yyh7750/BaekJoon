import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.regex.Pattern;

/**
 * packageName    : 백준
 * fileName       : 달력_20207
 * date           : 2023-03-27
 * description    :
 */
public class Main {

  private static int N;
  private static int[] arr;

  public static void main(String[] args) throws IOException {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = null;
    N = Integer.parseInt(br.readLine());

    arr = new int[366];

    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      int start = Integer.parseInt(st.nextToken());
      int end = Integer.parseInt(st.nextToken());

      /**
       * 인덱스를 기준으로 같은 수가 입력되면 카운트 증가 -> 높이 값
       * 연속된 일수 -> 너비 값
       */
      for (int j = start; j <= end; j++) {
        arr[j]++;
      }
    }

    System.out.println(solution());
  }

  private static int solution() {

    int width = 0;
    int height = 0;
    int result = 0;

    for (int i = 1; i < arr.length; i++) {
      if (arr[i] != 0) {
        width++;
        height = Math.max(height, arr[i]);
      } //
      else {
        result += width * height;
        width = 0;
        height = 0;
      }
    }

    result += width * height;
    return result;
  }
}
