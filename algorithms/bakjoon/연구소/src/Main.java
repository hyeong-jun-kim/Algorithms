import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Optional;
import java.util.Queue;
import java.util.StringTokenizer;

class Node {
    int a, b;

    Node(int a, int b) {
        this.a = a;
        this.b = b;
    }

    public int getA() {
        return a;
    }

    public int getB() {
        return b;
    }
}

public class Main {
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    static int[][] dxdy = {{-1,1}, {1,1}, {-1,-1}, {-1,1}};
    static boolean visit[][];
    static int n;
    static int m;
    static int max = Integer.MIN_VALUE;

    public static void main(String args[]) throws IOException {
        String[] inputs;
        int map[][];
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        inputs = bf.readLine().split(" ");
        n = Integer.parseInt(inputs[0]);
        m = Integer.parseInt(inputs[1]);
        map = new int[n][m];
        visit = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        dfs(0, map);
        System.out.println(max);
    }

    static void dfs(int cnt, int map[][]) {
        if (cnt == 3) {
            bfs(map);
            return;
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (visit[i][j] != true && map[i][j] != 2 && promising(i, j, map)) {
                    visit[i][j] = true;
                    map[i][j] = 1;
                    dfs(cnt + 1, map);
                    visit[i][j] = false;
                    map[i][j] = 0;
                }
            }
        }
    }

    // 주변에 벽이 1개이상 있거나, 바이러스가 있을 시 유망하다고 판단
    static boolean promising(int a, int b, int[][] map) {
        int x, y;
        boolean check = false;
        // 상하좌우
        for (int i = 0; i < 4; i++) {
            x = a + dx[i];
            y = b + dy[i];
            if (x >= 0 && x < n && y >= 0 && y < m) {
                if (map[x][y] == 1 || map[x][y] == 2)
                    check = true;
                    continue;
            }
            // 대각선
            for(int j = 0; j < 4; j++){
                x = a + dxdy[j][0];
                y = b + dxdy[j][1];
                if (x >= 0 && x < n && y >= 0 && y < m) {
                    if (map[x][y] == 1 || map[x][y] == 2)
                        check = true;
                    continue;
                }
            }

        }
        return check;
    }

    static void bfs(int[][] map) {
        int cnt = 0;
        int[][] tmp_map = new int[n][m];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++) {
                tmp_map[i][j] = map[i][j];
            }
        }
        Node node;
        Queue<Node> q = new LinkedList<>();
        // 바이러스 감염 처리
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if(tmp_map[i][j] == 2){
                    node = new Node(i, j);
                    q.offer(node);
                    while(!q.isEmpty()){
                        node = q.poll();
                        for(int k = 0; k < 4; k++){
                            int x = dx[k] + node.getA();
                            int y = dy[k] + node.getB();
                            if (x >= 0 && x < n && y >= 0 && y < m) {
                                if(tmp_map[x][y] != 1 && tmp_map[x][y] != 2){
                                    tmp_map[x][y] = 2;
                                    q.offer(new Node(x, y));
                                }
                            }
                        }
                    }
                }
            }
        }
        // 안전지역 카운트
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(tmp_map[i][j] == 0)
                    cnt++;
            }
        }
        max = Math.max(cnt, max);
    }
}
