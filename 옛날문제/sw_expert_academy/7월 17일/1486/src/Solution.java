import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Solution {
	static ArrayList<Integer> data = new ArrayList<Integer>();
	public static void main(String args[]) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int Case = Integer.parseInt(bf.readLine());
		int cnt = 0;
		while(cnt < Case) {
			cnt++;
			data.clear();
			StringTokenizer st = new StringTokenizer(bf.readLine());
			int num = Integer.parseInt(st.nextToken());
			int top = Integer.parseInt(st.nextToken());
			
			st = new StringTokenizer(bf.readLine());
			int p[] = new int[num];
			boolean visited[] = new boolean[num];
			for(int i = 0; i < num; i++) {
				p[i] = Integer.parseInt(st.nextToken());
			}
			powerSet(p, visited, num, 0, top);
			int min = 9999999;
			for(int i = 0; i < data.size(); i++) {
				if(data.get(i) <= min) {
					min = data.get(i);
				}
			}
			System.out.println("#"+cnt+ " "+min);
		}
	}
	public static void powerSet(int[] arr, boolean[] visited, int n , int idx, int top) {
		if(idx == n) {
			int sum = 0;
			for(int i = 0; i < arr.length; i++) {
				if(visited[i] == true) {
					sum += arr[i];
				}
			}
			if(sum - top >= 0) {
				data.add(sum - top);
				return;
			}else {
				return;
			}
		}
		visited[idx] = false;
		powerSet(arr, visited, n, idx+1, top);
		
		visited[idx] = true;
		powerSet(arr, visited, n, idx+1, top);
	}
}