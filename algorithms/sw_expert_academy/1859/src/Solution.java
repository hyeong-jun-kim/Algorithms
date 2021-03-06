import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	public static void main(String args[]) throws NumberFormatException, IOException {
		int price[];
		// Input
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int c = Integer.parseInt(bf.readLine());
		int count = 1;
		while(c>0) {
			int day = Integer.parseInt(bf.readLine());
			// 전처리
			if(day<2 || day > 1000000)
				return;
			StringTokenizer st = new StringTokenizer(bf.readLine());
			price = new int[day];
			for(int i = 0; i < day; i++) {
				price[i] = Integer.parseInt(st.nextToken());
				// 전처리
				if(price[i] < 0 || price[i] > 10000)
					return;
			}
			// Process
			int idx = 0;
			long answer = 0;
			int start = 0;
			loop:
			while(idx <= day-1) {
				long sum = 0;
				int max = price[idx];
				// max 값 찾기
				for(int i = idx; i < day; i++) {
					if(max <= price[i]) {
						max = price[i];
						idx = i;
					}
				}
				// 예외처리 (max 값이 자신일 때)
				if(max == price[start]) {
					idx = idx + 1;
					start = idx;
					continue loop;
				}
				// 계산
				for(int i = start; i < idx ; i++) {
					sum += price[i];
				}
				sum = max * (idx - start) - sum;
				answer += sum;
				idx++;
				start = idx;
			}
			System.out.println("#"+count+ " " + answer);
			count++;
			c--;
		}
	}
}
