import java.util.Stack;

public class Solution {
	public static void main(String args[]) {
		int board[][] = {{0,0,0,0,0},{0,0,1,0,3},{0,2,5,0,1},{4,2,4,4,2},{3,5,1,3,1}};
		int moves[] = {1,5,3,5,1,2,1,4};
		System.out.println(solution(board,moves));
	}
	public static int solution(int[][] board, int[] moves) {
		Stack<Integer> stack = new Stack<Integer>();
		int l = board.length;
		int count = 0;
		int c = 0;
		for(int i = 0; i < moves.length; i++) {
			int m = moves[i]-1;
			for(int j = 0; j < l ; j++) {
				// first
				if(board[j][m]!=0 && stack.empty()) {
					stack.add(board[j][m]);
					board[j][m] = 0;
					count++;
					break;
				}
				// push
				else if(board[j][m]!=0 && stack.lastElement()!=board[j][m]) {
					stack.add(board[j][m]);
					board[j][m] = 0;
					count++;
					break;
				}
				// pop
				else if(board[j][m]!=0 && stack.lastElement() == board[j][m]){
					stack.remove(count-1);
					board[j][m] = 0;
					count--;
					c += 2;
					break;
				}
			}
		}
		int answer = c;
		return answer;
	}
}
