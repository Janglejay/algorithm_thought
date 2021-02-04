package search_graph_theory.dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SimpleDijkstra {
    static final MyScanner in = new MyScanner();
    static final MyWriter myOut = new MyWriter();
    static final PrintWriter out = myOut.out;
    static int n;
    static int m;
    static int[][] graph;
    static int[] dist;
    static boolean[] st;
    public static void main(String[] args) {
        n = in.nextInt();
        m = in.nextInt();
        graph = new int[n + 1][n + 1];
        dist = new int[n + 1];
        st = new boolean[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE / 2);
        for (int i = 0; i < n + 1; i++) Arrays.fill(graph[i], Integer.MAX_VALUE / 2);
        for (int i = 0; i < m; i++) {
            int a = in.nextInt();
            int b = in.nextInt();
            int v = in.nextInt();
            graph[a][b] = Math.min(graph[a][b], v);
        }
        int res = dijkstra();
        out.println(res);
        out.flush();
        out.close();
    }

    private static int dijkstra() {
        dist[1] = 0;
        for (int i = 1; i < n + 1; i++) {
            int idx = -1;
            for (int j = 1; j < n + 1; j++) {
                if (!st[j] && (idx == -1 || dist[idx] > dist[j])) {
                    idx = j;
                }
            }
            st[idx] = true;
            for (int j = 1; j < n + 1; j++) {
                dist[j] = Math.min(dist[j], dist[idx] + graph[idx][j]);
            }
        }
        if (dist[n] == Integer.MAX_VALUE / 2) {
            return -1;
        }
        return dist[n];
    }

    private static class MyWriter {

        private PrintWriter out;

        private MyWriter() {
            out = new PrintWriter(System.out);
        }

        private void printlnArray(int[] arr, int start, int end) {
            for (int i = start; i < end; i++) {
                out.println(arr[i]);
            }
        }

        private void printArrayJoinSpace(int[] arr, int start, int end) {
            for (int i = start; i < end; i++) {
                out.println(arr[i] + " ");
            }
        }

        private void printlnArray(int[] arr) {
            for (int x : arr) {
                out.println(x);
            }
        }

        private void printArrayJoinSpace(int[] arr) {
            for (int i : arr) {
                out.print(i + " ");
            }
        }

    }

    private static class MyScanner {
        private BufferedReader br;
        private StringTokenizer st;

        private MyScanner() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        private String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        private int nextInt() {
            return Integer.parseInt(next());
        }

        private long nextLong() {
            return Long.parseLong(next());
        }

        private double nextDouble() {
            return Double.parseDouble(next());
        }

        private String nextLine() {
            String str = "";
            try {
                str = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return str;
        }

        private void nextIntegerArray(int[] arr, int start, int end) {
            for (int i = start; i < end; i++) {
                arr[i] = nextInt();
            }
        }

        private void nextIntegerArray(int[] arr) {
            for (int i = 0; i < arr.length; i++) {
                arr[i] = nextInt();
            }
        }
    }
}
