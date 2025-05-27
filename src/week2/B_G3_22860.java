package week2;

import java.io.*;
import java.util.*;

/*
https://www.acmicpc.net/problem/22860(폴더정리)

파일을 트리구조로 가져간다.
dfs를 통하여 Set에 넣어주며 바텀업 방식으로 값을 저장한다.

시간 복잡도 O(N+M+Q)
 */
public class B_G3_22860 {
    public static final int IS_FILE = 0;
    final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    Map<String, Set<String>> nodeMap = new TreeMap<>();
    Set<String> file = new HashSet<>();
    List<String> qList = new ArrayList<>();
    Map<String, List<String>> ans = new HashMap<>();
    int n;
    int m;

    public static void main(String[] args) throws IOException {
        B_G3_22860 solve = new B_G3_22860();
        solve.init();
    }

    public void init() throws IOException {
        input();
        solve();
        output();
    }

    public void solve() throws IOException {
        dfs("main");

        for (String q : qList) {
            List<String> fileList = ans.get(q);
            int typeCount = fileList.size();
            int distinctCount = (int) fileList.stream().distinct().count();

            bw.write(String.format("%d %d%n", distinctCount, typeCount));
        }
    }

    private void dfs(String node) {
        // 이미 방문한 노드면 리턴
        if (ans.containsKey(node)) {
            return;
        }
        // 파일 이라면 정보를 갱신 한다.
        if (isFile(node)) {
            ans.computeIfAbsent(node, k -> new ArrayList<>()).add(node);
            return;
        }
        // 자식 노드에 있는 폴더에 도착 하면 리턴 한다.
        if (!nodeMap.containsKey(node)) {
            return;
        }

        for (String child : nodeMap.get(node)) {
            dfs(child);
            ans.computeIfAbsent(node, k -> new ArrayList<>()).addAll(ans.get(child));
        }
    }

    private boolean isFile(String child) {
        return file.contains(child);
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

        // 부모 노드, 자식 노드, 파일 여부 을 입력 받는다.
        for (int i = 0; i < n + m; i++) {
            st = readLine();
            String parent = st.nextToken();
            String child = st.nextToken();

            int checkFile = Integer.parseInt(st.nextToken());

            if (checkFile == IS_FILE) {
                file.add(child);
            }

            nodeMap.computeIfAbsent(parent, k -> new HashSet<>()).add(child);
        }


        int q = Integer.parseInt(readLine().nextToken());

        // 출력해야 하는 디렉토리를 입력 받는다.
        for (int i = 0; i < q; i++) {
            st = readLine();

            String path = st.nextToken();
            String node = getLastIndex(path.split("/"));
            qList.add(node);
        }

    }

    private String getLastIndex(String[] str) {
        return str[str.length - 1];
    }

    private void output() throws IOException {
        bw.flush();
        bw.close();
    }
}