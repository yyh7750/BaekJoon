import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	static class Sort implements Comparable<Sort> {
		int num;
		String strNum;

		public Sort(int num, String strNum) {
			this.num = num;
			this.strNum = strNum;
		}

		@Override
		public int compareTo(Sort o) {
			return this.strNum.compareTo(o.strNum);
		}
	}

	private static String[] strNum = { "zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine" };

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder resultBuilder = new StringBuilder();

		int M = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());

		List<Sort> list = new ArrayList<>();

		for (int i = M; i <= N; i++) {
			String str = Integer.toString(i);
			char[] numArr = str.toCharArray();

			StringBuilder sb = new StringBuilder();
			for (int j = 0; j < numArr.length; j++) {
				sb.append(strNum[numArr[j] - '0']).append(" ");
			}

			list.add(new Sort(i, sb.toString().trim()));
		}

		Collections.sort(list);
		for (int i = 0; i < list.size(); i++) {
			resultBuilder.append(list.get(i).num).append(" ");
			if ((i + 1) % 10 == 0) {
				resultBuilder.append("\n");
			}
		}

		br.close();
		System.out.println(resultBuilder);
	}
}