import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static int max;
	static int n, m;
	public static void main(String args[]) throws NumberFormatException, IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int c = Integer.parseInt(bf.readLine());
		int count = 0;
		while(count < c) {
			count++;
			max = 0;
			StringTokenizer st = new StringTokenizer(bf.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			int arr[][] = new int[n][n];
			for(int i = 0; i < n; i++) {
				st = new StringTokenizer(bf.readLine());
				for(int j = 0; j < n; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			getMaxfly(arr,0,0);
			System.out.println("#"+count+ " " + max);
		}
	}
	public static void getMaxfly(int arr[][], int i, int j) {
		if(i == n-m && j == n-m+1)
			return;
		if(j == n-m+1) {
			getMaxfly(arr,i+1,0);
			return;
		}
		int sum = 0;
		for(int k = 0+i; k < m+i; k++) {
			for(int l = 0+j; l < m+j; l++) {
				sum += arr[k][l];
			}
		}
		if(sum > max)
			max = sum;
		getMaxfly(arr,i,j+1);
	}
}