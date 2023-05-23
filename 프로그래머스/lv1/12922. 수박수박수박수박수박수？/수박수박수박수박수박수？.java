class Solution {
    public String solution(int n) {
        
        char[] arr = {'수', '박'};
        StringBuilder sb = new StringBuilder();
        
        for (int i = 0; i < n; i++) {
            sb.append(arr[i % 2]);
        }
        
        return sb.toString();
    }
}