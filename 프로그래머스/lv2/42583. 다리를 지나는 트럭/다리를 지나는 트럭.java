import java.util.*;

class Solution {
    
    public int solution(int bridgeLength, int weight, int[] truckWeights) {
        
        int answer = sol(bridgeLength, weight, truckWeights);
        
        return answer;
    }
    
    // 트럭의 순서는 고정이다.
    private int sol(int bridgeLength, int weight, int[] trucks) {
        
        Queue<Integer> Q = new LinkedList<>();
        int answer = 0, sum = 0;
        
        for (int i = 0; i < trucks.length; i++) {
         
            // 0이 추가되어야 하니까 루프를 돌아준다.
            while (true) {

                // 큐가 비어있을 경우
                if (Q.isEmpty()) {
                    Q.offer(trucks[i]);
                    answer++;
                    sum += trucks[i];
                    break;
                }
                else {
                    // 다 건넜다
                    if (Q.size() == bridgeLength) {
                        sum -= Q.poll();
                    }
                    // 다리가 견딜 수 있는 무게를 초과하는 경우
                    if (sum + trucks[i] > weight) {
                        Q.offer(0);
                        answer++;
                    }
                    // 초과하지 않는 경우
                    else {
                        Q.offer(trucks[i]);
                        answer++;
                        sum += trucks[i];
                        break;
                    }
                }
            }
        }
        
        return answer + bridgeLength;
    }
    
//     class Truck {
        
//         int weight, time;
        
//         public Truck(int weigth, int time) {
//             this.weigth = weigth;
//             this.time = time;
//         }
//     }
}