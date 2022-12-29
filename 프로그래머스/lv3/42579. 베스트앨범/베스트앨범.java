import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {

	class Music implements Comparable<Music> {
		String genre;
		int play;
		int num;

		public Music(String genre, int play, int num) {
			super();
			this.genre = genre;
			this.play = play;
			this.num = num;
		}

		@Override
		public int compareTo(Music o) {
			if (this.play == o.play) {
				return this.num - o.num;
			}
			return o.play - this.play;
		}
	}

	public int[] solution(String[] genres, int[] plays) {

		Map<String, Integer> genreMap = new HashMap<>();
		for (int i = 0; i < genres.length; i++) {
			// getOrDefault를 이용해 누적합 구하기
			genreMap.put(genres[i], genreMap.getOrDefault(genres[i], 0) + plays[i]);
		}

		// 장르 재생 수 별로 먼저 정렬하기
		List<Music> genreList = new ArrayList<>();
		int idx = 0;
		for (String key : genreMap.keySet()) {
			genreList.add(new Music(key, genreMap.get(key), idx));
			idx++;
		}
		Collections.sort(genreList);

		List<Music> result = new ArrayList<>();
		for (Music music : genreList) {

			// 장르 내 재생 수 별로 정렬
			List<Music> playList = new ArrayList<>();
			for (int i = 0; i < genres.length; i++) {
				if (genres[i].equals(music.genre)) {
					playList.add(new Music(music.genre, plays[i], i));
				}
			}
			Collections.sort(playList);

			// 결과 담기
			if (playList.size() > 1) {
				for (int i = 0; i < 2; i++) {
					result.add(playList.get(i));
				}
			} //
			else {
				result.add(playList.get(0));
			}
		}

		int[] answer = new int[result.size()];
		for (int i = 0; i < result.size(); i++) {
			answer[i] = result.get(i).num;
		}

		return answer;
	}

// 	public static void main(String[] args) {
// 		베스트앨범 sol = new 베스트앨범();
// 		int[] ans = sol.solution(new String[] { "classic", "pop", "classic", "classic", "classic" },
// 				new int[] { 500, 1000, 400, 300, 200, 100 });

// 		System.out.println(Arrays.toString(ans));
// 	}
}