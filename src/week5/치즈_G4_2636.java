package week5;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*

 */

public class 치즈_G4_2636 {
    private static final int CHEESE = 1;
    private static final int EMPTY = 0;
    private static final int EDGE_CHEESE = 2;
    final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    int[][] board;
    int[] dx = {-1, 1, 0, 0};
    int[] dy = {0, 0, -1, 1};
    int t, n, m;


    long ans;

    public static void main(String[] args) throws IOException {
        치즈_G4_2636 solve = new 치즈_G4_2636();
        solve.init();
    }

    public void init() throws IOException {
        input();
        solve();
        output();
    }

    public StringTokenizer readLine() {
        try {
            return new StringTokenizer(br.readLine());
        } catch (IOException e) {
            throw new IllegalArgumentException("읽어오지 못했습니다.");
        }
    }

    /*

13 12
0 0 0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 1 1 0 0 0
0 1 1 1 0 0 0 1 1 0 0 0
0 1 1 1 1 1 1 0 0 0 0 0
0 1 1 1 1 1 0 1 1 0 0 0
0 1 1 1 1 0 0 1 1 0 0 0
0 0 1 1 0 0 0 1 1 0 0 0
0 0 1 1 1 1 1 1 1 0 0 0
0 0 1 1 1 1 1 1 1 0 0 0
0 0 1 1 1 1 1 1 1 0 0 0
0 0 1 1 1 1 1 1 1 0 0 0
0 0 0 0 0 0 0 0 0 0 0 0
     */

    public void input() {
        StringTokenizer st = readLine();
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        board = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = readLine();
            for (int j = 0; j < m; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    private void output() throws IOException {
        bw.flush();
        bw.close();
    }

    public void solve() throws IOException {
        boolean[][] visited = new boolean[n][m];

        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(0, 0));
        visited[0][0] = true;

        Queue<Point> nextQueue;
        int count = 0;
        int prevCheeseCount = 0;
        while(!(nextQueue = getNextQueue(queue, visited)).isEmpty()){
            count++;
            queue = nextQueue;
            prevCheeseCount = queue.size();
        }

        bw.write(count + "\n");
        bw.write(prevCheeseCount + "\n");


    }

    private Queue<Point> getNextQueue(Queue<Point> queue, boolean[][] visited) {
        Queue<Point> nextQueue = new LinkedList<>();

        while (!queue.isEmpty()) {
            Point p = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nx = p.x + dx[i];
                int ny = p.y + dy[i];

                if (!isValid(nx, ny) || visited[nx][ny]) continue;
                visited[nx][ny] = true;

                if (board[nx][ny] == EMPTY) queue.add(new Point(nx, ny));
                if (board[nx][ny] == CHEESE) nextQueue.add(new Point(nx, ny));
            }
        }
        return nextQueue;
    }

    private boolean isValid(int nx, int ny) {
        return nx >= 0 && nx < n && ny >= 0 && ny < m;
    }

//    private boolean isEdgeCheese(int x, int y) {
//        for (int i = 0; i < 4; i++) {
//            int nx = x + dx[i];
//            int ny = y + dy[i];
//
//            if (board[nx][ny] == EMPTY) return true;
//        }
//        return false;
//    }

    private class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}