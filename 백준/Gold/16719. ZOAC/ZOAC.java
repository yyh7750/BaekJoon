import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    private static String str;
    private static boolean[] visited;
    private static StringBuilder sb;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        str = br.readLine();

        visited = new boolean[str.length()];
        sb = new StringBuilder();

        dfs(0, str.length() - 1);

        System.out.println(sb);
        br.close();
    }

    private static void dfs(int start, int end) {

        if (start > end) return;

        int minStrIdx = start;

        for (int i = start; i <= end; i++) {
            if (str.charAt(minStrIdx) > str.charAt(i)) {
                minStrIdx = i;
            }
        }

        visited[minStrIdx] = true;
        for (int i = 0; i < str.length(); i++) {
            if (visited[i]) {
                sb.append(str.charAt(i));
            }
        }
        sb.append("\n");

//        System.out.println(minStrIdx + 1 + " " + end);
//        System.out.print(start + " ");
//        System.out.println(minStrIdx - 1 + "\n");
        dfs(minStrIdx + 1, end);
        dfs(start, minStrIdx - 1);
    }
}
