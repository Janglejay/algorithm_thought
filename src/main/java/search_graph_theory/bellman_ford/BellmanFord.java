package search_graph_theory.bellman_ford;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class BellmanFord {
    static final MyScanner in = new MyScanner();
    static final MyWriter myOut = new MyWriter();
    static final PrintWriter out = myOut.out;
    static int n;
    static int k;
    static int m;
    static int[] dist;
    static List<Edge> graph = new ArrayList<>();
    private static class Edge{
        private int start;
        private int end;
        private int weight;

        public Edge(int start, int end, int weight) {
            this.start = start;
            this.end = end;
            this.weight = weight;
        }
    }
    private static void add(int a, int b, int v) {
        graph.add(new Edge(a, b, v));
    }
    public static void main(String[] args) {
        n = in.nextInt();
        m = in.nextInt();
        k = in.nextInt();
        dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE / 2);
        for (int i = 0; i < m; i++) {
            add(in.nextInt(), in.nextInt(), in.nextInt());
        }
        int res = bellmanFord();
        if (res == -1) {
            out.println("impossible");
        }else {
            out.println(res);
        }
        out.flush();
        out.close();
    }
    private static int bellmanFord() {
        dist[1] = 0;
        for (int i = 0; i < k; i++) {
            int[] bak = new int[n + 1];
            System.arraycopy(dist, 0, bak, 0, n + 1);
            for (Edge e : graph) {
                dist[e.end] = Math.min(dist[e.end], bak[e.start] + e.weight);
            }
        }
        if (dist[n] >= Integer.MAX_VALUE / 4) {
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
