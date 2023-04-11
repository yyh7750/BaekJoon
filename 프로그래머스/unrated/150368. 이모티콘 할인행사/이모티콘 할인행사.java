import java.util.*;

class Solution {
    
    double[] discount = {0.1, 0.2, 0.3, 0.4};
    int maxPlusMember = Integer.MIN_VALUE;
    int maxPrice = Integer.MIN_VALUE;
    List<User> priceList;
    
    public int[] solution(int[][] users, int[] emoticons) {
    
        priceList = new ArrayList<>();
        dfs(0, users, emoticons);
        
        int[] answer = new int[2];
        answer[0] = maxPlusMember;
        answer[1] = maxPrice;
        
        return answer;
    }
    
    
    // 중복 순열
    // 할인 정보를 한번씩 다 비교해보고 결과 도출
    private void dfs(int idx, int[][] users, int[] emoticons) {
        
        // 할인 다 해봤을때 리턴
        if (idx == emoticons.length) {
            int plusMember = 0;
            int sumAllUserPrice = 0;
            
            for (int i = 0; i < users.length; i++) {
                
                // 기준 할인 비율, 가격
                int standardDiscount = users[i][0];
                int standardPrice = users[i][1];

                int sumPrice = 0;
                
                for (int j = 0; j < priceList.size(); j++) {
                    double discountPrice = priceList.get(j).price;
                    double userDiscount = priceList.get(j).discount;
                    
                    // 할인율이 기준 비율보다 높거나 같으면 더한다.
                    if (userDiscount >= standardDiscount) {
                        sumPrice += discountPrice;
                    }
                }
                
                // 기준 금액보다 산 금액이 높거나 같으면 금액 다 버리고 플러스 가입
                if (sumPrice >= standardPrice) {
                    plusMember += 1;
                }
                // 플러스 가입 안하면 금액 계산
                else {
                    sumAllUserPrice += sumPrice;
                }   
            }
            
            if (maxPlusMember < plusMember) {
                maxPlusMember = plusMember;
                maxPrice = sumAllUserPrice;
            }
            else if (maxPlusMember == plusMember && maxPrice < sumAllUserPrice) {
                maxPrice = sumAllUserPrice;
            }
            
            return;
        }
        
        for (int i = 0; i < discount.length; i++) {
            
            // 이모티콘 가격 담기
            priceList.add(new User(emoticons[idx] * (1 - discount[i]), discount[i] * 100));
            
            // 재귀
            dfs(idx + 1, users, emoticons);
            
            // 담았던거 삭제해준다.
            priceList.remove(priceList.size() - 1);
        }
    }
    
    class User {
        double price, discount;
        
        public User(double price, double discount) {
            this.price = price;
            this.discount = discount;
        }
    }
}