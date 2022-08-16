import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	private static int cnt = 1;

	private static class Time implements Comparable<Time> {
		int start;
		int end;

		public Time(int start, int end) {
			super();
			this.start = start;
			this.end = end;
		}

		@Override
		public int compareTo(Time time) {
			if (this.end != time.end) {
				return this.end - time.end;
			} //
			return this.start - time.start;
		}

		@Override
		public String toString() {
			return "Time [start=" + start + ", end=" + end + "]";
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = null;

		Time[] time = new Time[N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			time[i] = new Time(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}

		Arrays.sort(time);
//		System.out.println(Arrays.toString(time));

		solution(time);

		br.close();
		System.out.println(cnt);
	}

	private static void solution(Time[] time) {
		int end = time[0].end;

		for (int i = 1; i < time.length; i++) {
			if (end <= time[i].start) {
				end = time[i].end;
				cnt++;
			}
		}
	}
}