import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static boolean check;

	public static void main(String args[]) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int c = Integer.parseInt(bf.readLine());
		int a = 0;
		while (a < c) {
			a++;
			check = true;
			int arr[][] = new int[9][9];
			for (int i = 0; i < 9; i++) {
				StringTokenizer st = new StringTokenizer(bf.readLine());
				for (int j = 0; j < 9; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			// check
			// 가로 세로 체크
			check_width_height(arr, 0, 0, 1);
			//System.out.println("#" + a + " " + check);
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					int[] c_arr = new int[10];
					for (int k = i * 3; k < i * 3 + 3; k++) {
						for (int l = j * 3; l < j * 3 + 3; l++) {
								c_arr[arr[k][l]]++;
						}
					}
					// check
					for(int k = 1; k < 10; k++) {
						if(c_arr[k] != 1)
							check = false;
					}
				}
			}
			int answer = 0;
			if(check == true) answer = 1;
			System.out.println("#" + a + " " + answer);
		}
	}

	public static void check_width_height(int arr[][], int idx, int idx1, int idx2) {
		if (idx == 9)
			return;
		if (idx1 == 8 && idx2 == 9) {
			check_width_height(arr, idx + 1, 0, 1);
			return;
		}
		if (idx2 == 9) {
			check_width_height(arr, idx, idx1 + 1, idx1 + 2);
			return;
		}
		if (arr[idx][idx1] == arr[idx][idx2] || arr[idx1][idx] == arr[idx2][idx]) {
			check = false;
		}
		check_width_height(arr, idx, idx1, idx2 + 1);
	}
}
