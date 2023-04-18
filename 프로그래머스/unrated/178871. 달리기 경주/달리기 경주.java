import java.util.*;

class Solution {
    public String[] solution(String[] players, String[] callings) {
        
        return swap(players, callings);
    }
    
    private String[] swap(String[] players, String[] callings) {
        
        HashMap<String, Integer> map = new HashMap<>();
        
        // key 값으로 이름을 넣고, value 값으로 순위를 넣는다.
        for (int i = 0; i < players.length; i++) {
            map.put(players[i], i);
        }
        
        for (int i = 0; i < callings.length; i++) {
            // 현재 등수
            int rank = map.get(callings[i]);
            
            if (rank <= 0) {
                continue;
            }
            
            // 현재 등수보다 앞으로
            map.put(callings[i], rank - 1);
            // 앞에 있는 선수 뒤로
            map.put(players[rank - 1], rank);
            
            String player = players[rank];
            players[rank] = players[rank - 1];
            players[rank - 1] = player;
        }
        
        return players;
    }
}