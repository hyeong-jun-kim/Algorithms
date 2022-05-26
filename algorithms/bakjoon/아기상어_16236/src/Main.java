import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Node{
    int a, b, size, distance = 0;
    Node(int a, int b){
        this.a = a;
        this.b = b;
    }
    Node(int a, int b, int size){
        this.a = a;
        this.b = b;
        this.size = size;
    }
    int getA(){
        return a;
    }
    int getB(){
        return b;
    }
    void setA(int a){
        this.a = a;
    }
    void setB(int b){
        this.b = b;
    }
    void setDistance(int distance){
        this.distance = distance;
    }
    int getDistance(){
        return distance;
    }
}
public class Main {
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int[][] distance;
    static int n;
    static int[][] arr;
    public static void main(String args[]) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(bf.readLine());
        arr = new int[n][n];
        distance = new int[n][n];
        int a = 0, b = 0;
        for(int i = 0; i < n; i++){
            StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
            for(int j = 0; j < n; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
                if(arr[i][j] == 9){
                    a = i;
                    b = j;
                }
            }
        }
        bfs(a, b);
    }
    static void bfs(int aa, int bb){
        Node shark = new Node(aa, bb, 2);
        Queue<Node> q = new LinkedList<>();
        ArrayList<Node> node_list = new ArrayList<>();
        while(true) {
            node_list.clear();
            distance = new int[n][n];
            int size = shark.size;
            for (int i = 0; i < n; i++) { // 상어보다 작은 사이즈 넣기
                for (int j = 0; j < n; j++) {
                    if(arr[i][j] < size && (i!=aa || j!=bb)){
                        node_list.add(new Node(i, j));
                    }
                }
            }
            if(node_list.isEmpty()) // 상어보다 작은 사이즈가 없으면 탈출
                break;
            q.offer(shark);
            // 각 노드별 거리 구하기
            while(!q.isEmpty()){
                Node node = q.poll();
                int a = node.getA();
                int b = node.getB();
                for(int i = 0; i < 4; i++){
                    int x = a + dx[i];
                    int y = b + dy[i];
                    if(x >= 0 && x < n && y >= 0 && y < n){
                        if(size < arr[x][y]) // 물고기의 크기가 클 경우 무시
                            continue;
                        if(size == arr[x][y] && distance[x][y] == 0){ // 물고기의 크기가 같을 경우 통과, 처음 방문할 경우 통과
                            distance[x][y] = distance[a][b] + 1;
                        }
                    }
                }
            }
            int max = Integer.MAX_VALUE;
            int ea = 999;
            int eb = 999;
            for(int i = 0; i < node_list.size(); i++) {
                int a = node_list.get(i).getA();
                int b = node_list.get(i).getB();
                if (distance[a][b] == 0)
                    continue;
                int length = distance[a][b];
                if (max > length) {
                    ea = a;
                    eb = b;
                    max = length;
                } else if (max == length) {
                    if (eb > b || ea < a) { // 위쪽에 있거나 왼쪽에 있을 때
                        ea = a;
                        eb = b;
                    }
                }
            }
            if(max != 999){
                shark.setA(ea);
                shark.setB(eb);
            }

        }
    }
}
