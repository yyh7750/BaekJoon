import java.util.*;

class Solution {
    
    int answer = 0;
    boolean flag;
    boolean[][] visited;
    // 우, 우하, 하 검사
    int[] dr = {0, 0, 1, 1};
    int[] dc = {0, 1, 1, 0};
    
    public int solution(int m, int n, String[] board) {
    
        char[][] boards = new char[m][n];
        
        for (int i = 0; i < m; i++) {
            boards[i] = board[i].toCharArray();
        }
        
        while (!flag) {
            visited = new boolean[m][n];
            find4Block(boards);
            delete4Block(boards);
            downBlock(boards);
        }

        return answer;
    }
    
    // 2*2 크기의 블록을 찾는 메소드
    // (n - 2) * (n - 2) 만큼 완전탐색
    // n - 1만큼 돌면 배열 범위에서 벗어나기 때문
    private void find4Block(char[][] board) {
        
        for (int i = 0; i < board.length - 1; i++) {
            for (int j = 0; j < board[0].length - 1; j++) {
                
                // 현재값 (검사할 값)
                char cur = board[i][j];
                
                if (cur == '0') continue;
                
                // 2*2 블록인지 판단할 변수
                int cnt = 1;
                
                for (int d = 1; d <= 3; d++) {
                    int nr = i + dr[d];
                    int nc = j + dc[d];
                    
                    if (cur == board[nr][nc]) {
                        cnt++;
                    }
                }
                
                // 2*2 블록일 경우 방문처리
                if (cnt == 4) {
                    for (int d = 0; d < 4; d++) {
                        int nr = i + dr[d];
                        int nc = j + dc[d];
                        visited[nr][nc] = true;
                    }
                }
            }
        }
        
        // 전부 돌았는데 방문처리된게 없다면 flag true로 바꿔준다.
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (visited[i][j]) {
                    return;
                }
            }
        }
        
        flag = true;
    }
    
    // 블록을 제거하는 메소드
    // 제거된 블록 개수의 누적합 = answer
    private void delete4Block(char[][] board) {
        
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (visited[i][j]) {
                    board[i][j] = '0';
                    answer++;
                }
            }
        }
    }
    
    // 블록을 내리는 메소드
    // 스택 이용해 블록을 내려준다.
    private void downBlock(char[][] board) {
        
        Stack<Character> stack = new Stack<>();
        
        for (int i = 0; i < board[0].length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (board[j][i] == '0') continue;
                stack.push(board[j][i]);
                board[j][i] = '0';
            }
            
            for (int j = board.length - 1; j >= 0; j--) {
                if (stack.isEmpty()) break;
                board[j][i] = stack.pop();
            }
        }
    }
}