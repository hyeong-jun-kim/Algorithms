import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Node{
    int a, b, size, distance = 0;
    int cnt = 0;
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
    void eatShark(){
        cnt++;
        if(cnt == size){
            size++;
            cnt = 0;
        }
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
        int size = bfs(a, b);
        System.out.println(size);
    }
    static int bfs(int aa, int bb){
        Node shark = new Node(aa, bb, 2);
        Queue<Node> q = new LinkedList<>();
        ArrayList<Node> node_list = new ArrayList<>();
        while(true) {
            node_list.clear();
            distance = new int[n][n];
            int size = shark.size;
            for (int i = 0; i < n; i++) { // ???????????? ?????? ????????? ??????
                for (int j = 0; j < n; j++) {
                    if(arr[i][j] != 0 && arr[i][j] < size && (i!=shark.getA() || j!=shark.getB())){
                        node_list.add(new Node(i, j));
                    }
                }
            }
            if(node_list.isEmpty()) // ???????????? ?????? ???????????? ????????? ??????
                break;
            q.offer(shark);
            // ??? ????????? ?????? ?????????
            while(!q.isEmpty()){
                Node node = q.poll();
                int a = node.getA();
                int b = node.getB();
                for(int i = 0; i < 4; i++){
                    int x = a + dx[i];
                    int y = b + dy[i];
                    if(x >= 0 && x < n && y >= 0 && y < n){
                        if(size < arr[x][y]) // ???????????? ????????? ??? ?????? ??????
                            continue;
                        if(size >= arr[x][y] && distance[x][y] == 0){ // ???????????? ????????? ?????? ?????? ??????, ?????? ????????? ?????? ??????
                            distance[x][y] = distance[a][b] + 1;
                            q.offer(new Node(x, y));
                        }
                    }
                }
            }
            int min = Integer.MAX_VALUE;
            int ea = 999;
            int eb = 999;
            for(int i = 0; i < node_list.size(); i++) {
                int a = node_list.get(i).getA();
                int b = node_list.get(i).getB();
                if (distance[a][b] == 0)
                    continue;
                int length = distance[a][b];
                if (min > length) {
                    ea = a;
                    eb = b;
                    min = length;
                } else if (min == length) {
                    if (ea > a) { // ????????? ?????? ???
                        ea = a;
                        eb = b;
                    }else if(ea == a && eb > b){ // ????????? ?????? ???
                        eb = b;
                    }
                }
            }
            if(ea != 999 && eb != 999){
                arr[shark.getA()][shark.getB()] = 0; // ?????? ?????? ??????
                arr[ea][eb] = 9; // ?????? ????????????
                shark.setA(ea);
                shark.setB(eb);
                shark.eatShark();
                int d  = shark.getDistance();
                shark.setDistance(d + min);
            }else{
                break;
            }
        }
        return shark.getDistance();
    }
}
