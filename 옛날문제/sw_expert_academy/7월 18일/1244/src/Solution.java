import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static int max;
	static int digit;
	static int count;
	public static void main(String args[]) throws NumberFormatException, IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int c = Integer.parseInt(bf.readLine());
		int cnt = 0;
		while (cnt < c) {
			cnt++;
			max = 0;
			StringTokenizer st = new StringTokenizer(bf.readLine());
			String prize = st.nextToken();
			count = Integer.parseInt(st.nextToken());
			char[] num = prize.toCharArray();
			digit = num.length;
			getMaxPrize(num,0);
			System.out.println("#" + cnt + " " + max);
		}
	}
	public static void getMaxPrize(char arr[], int idx) {
		if(idx == count) {
			String s_num = String.valueOf(arr);
			int num = Integer.parseInt(s_num);
			if(max < num)
				max = num;
			return;
		}
		if(idx > 5) {
			char tmp = arr[digit-1];
			arr[digit-1] = arr[digit-2];
			arr[digit-2] = tmp;
			getMaxPrize(arr, idx+1);
		}else {
			for(int i = 0; i < digit; i++) {
				for(int j = i+1; j < digit; j++) {
					char tmp = arr[i];
					arr[i] = arr[j];
					arr[j] = tmp;
					getMaxPrize(arr, idx+1);
					tmp = arr[i];
					arr[i] = arr[j];
					arr[j] = tmp;
				}
			}
		}
	}
}