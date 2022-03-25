public class Main {

	public static void main(String[] args) {
		
		boolean[] self = new boolean[10001];
		
		for(int i = 1; i <= 10000; i++) {
			int n = selfNum(i);
			
			if(n <= 10000) {
				self[n] = true;
			}
		}
		
		for(int i = 1; i <= 10000; i++) {
			if(!self[i]) {
				System.out.println(i);
			}
		}
	}
	
	private static int selfNum(int num) {
		
		int result = num;
		
		while(num != 0) {
			result += num % 10; // 첫째자리수 구하기
			num /= 10; // 첫째자리수를 없애고 둘째자리수 구하기
		}
		
		return result;
	}
}