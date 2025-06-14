package week5;

import java.io.*;
import java.util.StringTokenizer;

/*

 */

public class 오목_S1_2615 {
    public static final int SIZE = 19;
    public static final int WHITE = 1;
    public static final int BLACK = 2;
    private static final int WINNER_COUNT = 5;
    private static final int BLANK = 0;
    final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    int[][] board = new int[SIZE][SIZE];
    boolean[][] visited = new boolean[SIZE][SIZE];
    int t, n, m;


    long ans;
    int[] days = new int[366];

    public static void main(String[] args) throws IOException {
        오목_S1_2615 solve = new 오목_S1_2615();
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
        for (int i = 0; i < SIZE; i++) {
            StringTokenizer st = readLine();
            for (int j = 0; j < SIZE; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    private void output() throws IOException {
        bw.flush();
        bw.close();
    }

    public void solve() throws IOException {

        for (int j = 0; j < SIZE; j++) {
            for (int i = 0; i < SIZE; i++) {
                if (board[i][j] != BLANK && isWinner(i, j)) {
                    bw.write("%d \n%d %d\n".formatted(board[i][j], i + 1, j + 1));
                    return;
                }
            }
        }
        bw.write(String.valueOf(0));
    }

    private boolean isWinner(int i, int j) {
        int columnCount = getDirectionCount(i, j, 1, 0) + getDirectionCount(i, j, -1, 0) - 1;   //아래 위
        int rowCount = getDirectionCount(i, j, 0, 1) + getDirectionCount(i, j, 0, -1) - 1;      // 오른쪽 왼쪽
        int positiveDiagonal = getDirectionCount(i, j, 1, 1) + getDirectionCount(i, j, -1, -1) - 1; // 우하 좌상
        int negativeDiagonal = getDirectionCount(i, j, -1, 1) + getDirectionCount(i, j, 1, -1) - 1; // 우상 좌하

        return columnCount == WINNER_COUNT
                || rowCount == WINNER_COUNT
                || positiveDiagonal == WINNER_COUNT
                || negativeDiagonal == WINNER_COUNT;
    }

    private int getMaxCount(int i, int j) {
        return max(
                getDirectionCount(i, j, 1, 0), // 아래 방향
                getDirectionCount(i, j, 0, 1), // 오른쪽 방향
                getDirectionCount(i, j, 1, 1), // 우하
                getDirectionCount(i, j, -1, 1) // 우상
        );
    }

    private int max(int... values) {
        int ret = 0;
        for (int value : values) {
            ret = Math.max(ret, value);
        }
        return ret;
    }

    private int getDirectionCount(int x, int y, int dx, int dy) {
        int value = board[x][y];
        int ret = 0;

        int nx = x;
        int ny = y;
        while (isValid(nx, ny) && board[nx][ny] == value) {
            ret++;

            nx += dx;
            ny += dy;
        }
        return ret;
    }

    private boolean isValid(int i, int j) {
        return i >= 0 && i < SIZE && j >= 0 && j < SIZE;
    }


}