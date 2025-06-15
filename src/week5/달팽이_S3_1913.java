package week5;

import java.io.*;
import java.util.StringTokenizer;

/*

 */

public class 달팽이_S3_1913 {
    final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    int[][] board;
    int[] dx = {-1, 1, 0, 0};
    int[] dy = {0, 0, -1, 1};
    int t, n, m;


    long ans;

    public static void main(String[] args) throws IOException {
        달팽이_S3_1913 solve = new 달팽이_S3_1913();
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
        n = Integer.parseInt(readLine().nextToken());
        m = Integer.parseInt(readLine().nextToken());

        board = new int[n][n];
    }

    private void output() throws IOException {
        bw.flush();
        bw.close();
    }

    public void solve() throws IOException {
        int count = 1;

        int x = n / 2;
        int y = n / 2;

        board[x][y] = count;

        for (int i = 1; i < n; i += 2) {
            int right = i;
            int up = i;
            int down = i + 1;
            int left = i + 1;

            while (up-- != 0) {
                board[--x][y] = ++count;
            }
            while (right-- != 0) {
                board[x][++y] = ++count;
            }
            while (down-- != 0) {
                board[++x][y] = ++count;
            }
            while (left-- != 0) {
                board[x][--y] = ++count;
            }
        }

        int up = n - 1;
        while (up-- != 0) {
            board[--x][y] = ++count;
        }

        Point p = findPointFromNumber(m);

        for (int[] line : board) {
            for (int value : line) {
                bw.write(value + " ");
            }
            bw.newLine();
        }
        bw.write("%d %d".formatted(p.x + 1, p.y + 1));
    }

    private Point findPointFromNumber(int m) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == m) {
                    return new Point(i, j);
                }
            }
        }
        return null;
    }

    private boolean isValid(int x, int y) {
        return x >= 0 && x < n && y >= 0 && y < n;
    }

    class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}