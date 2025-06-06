package week4;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

/*
예외 케이스
6 4
0000
1110
0000
0111
1111
0000

 */

public class 벽부수고이동하기_G3_2206 {
    public static final int BREAK_COUNT = 1;
    public static final int WALL = 1;
    public static final int PATH = 0;
    final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    int n;
    int m;
    int[] dx = {0, 0, -1, 1};
    int[] dy = {1, -1, 0, 0};
    int[][] board = new int[1000][1000];

    int ans;


    public static void main(String[] args) throws IOException {
        벽부수고이동하기_G3_2206 solve = new 벽부수고이동하기_G3_2206();
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

    public void input() {
        StringTokenizer st = readLine();
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        IntStream.range(0, n).forEach(i -> {
            String value = readLine().nextToken();
            IntStream.range(0, m).forEach(j -> {
                board[i][j] = value.charAt(j) - '0';
            });
        });
    }

    private void output() throws IOException {
        bw.flush();
        bw.close();
    }

    public void solve() throws IOException {
        bw.write(String.valueOf(bfs(board)));
    }

    public int bfs(int[][] board) {
        Queue<Point> queue = new LinkedList<>();
        int[][][] visited = new int[BREAK_COUNT + 1][n][m];
        visited[BREAK_COUNT][0][0] = 1;

        queue.add(new Point(0, 0, BREAK_COUNT));

        while (!queue.isEmpty()) {
            Point point = queue.poll();

            if (point.x == n - 1 && point.y == m - 1) {
                return visited[point.possibleCount][point.x][point.y];
            }

            for (int i = 0; i < dx.length; i++) {
                int nx = point.x + dx[i];
                int ny = point.y + dy[i];
                int possibleCount = point.possibleCount;

                if (nx < 0 || ny < 0 || nx >= n || ny >= m) continue; //범위 초과 x
                if (board[nx][ny] == WALL && possibleCount == 0) continue; // 벽인데 가능 횟수가 없음.
                if (board[nx][ny] == PATH && visited[possibleCount][nx][ny] == 0) {
                    visited[possibleCount][nx][ny] = visited[possibleCount][point.x][point.y] + 1;
                    queue.add(new Point(nx, ny, possibleCount));
                }
                if (board[nx][ny] == WALL && visited[possibleCount - 1][nx][ny] == 0) {
                    visited[possibleCount - 1][nx][ny] = visited[possibleCount][point.x][point.y] + 1;
                    queue.add(new Point(nx, ny, possibleCount - 1));
                }
            }
        }

        return -1;
    }


    class Point {
        int x;
        int y;
        int possibleCount;

        public Point(int x, int y, int possibleCount) {
            this.x = x;
            this.y = y;
            this.possibleCount = possibleCount;
        }
    }
}