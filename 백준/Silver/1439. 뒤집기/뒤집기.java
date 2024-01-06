import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();

        StringTokenizer zeroToken = new StringTokenizer(str, "0");
        StringTokenizer oneToken = new StringTokenizer(str, "1");

        int ans = Math.min(zeroToken.countTokens(), oneToken.countTokens());

        System.out.println(ans);
        br.close();
    }
}
