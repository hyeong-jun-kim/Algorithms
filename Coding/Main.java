import java.io.*;
import java.util.*;

public class Main {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int i = 0; i < n; i++){
            arr[i] = Integer.parseInt(br.readLine());
            pq.offer(arr[i]);
        }


        int sum = 0;
        while(pq.size() != 1){
            int a = pq.poll();
            int b = pq.poll();
            sum += a + b;
            pq.offer(a+b);
        }

        System.out.println(sum);
    }
}