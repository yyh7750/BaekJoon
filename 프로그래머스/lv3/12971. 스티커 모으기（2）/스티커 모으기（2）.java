class Solution {
   
   /**
    * @methodName : solution
    * @description : 첫번째 스티커를 선택하느냐, 안하느냐에 따른 방법
    * @param sticker
    * @return
    *
    * @author : Younghun Yu
    * @date : 2022.12.28
    */
   public static int solution(int sticker[]) {
      int answer = 0;

      // 길이가 1일 경우 예외처리
      if (sticker.length == 1) {
         return sticker[0];
      }

      int[] dp = new int[sticker.length];

      // 첫 번째 스티커를 고를 경우
      dp[0] = sticker[0];
      dp[1] = dp[0];
      for (int i = 2; i < sticker.length - 1; i++) {
         dp[i] = Math.max(dp[i - 2] + sticker[i], dp[i - 1]);
      }
      answer = dp[sticker.length - 2];

      // 첫 번째 스티커를 고르지 않을 경우
      dp[0] = 0;
      dp[1] = sticker[1];
      for (int i = 2; i < sticker.length; i++) {
         dp[i] = Math.max(dp[i - 2] + sticker[i], dp[i - 1]);
      }
      answer = Math.max(answer, dp[sticker.length - 1]);
      
      return answer;
   }
   
   // public static void main(String[] args) {
   //    int ans = solution(new int[] {14, 6, 5, 11, 3, 9, 2, 10});
   //    System.out.println(ans);
   // }
}