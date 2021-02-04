package search_graph_theory.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

public class PointLevel {
    static final MyScanner in = new MyScanner();
    static final MyWriter myOut = new MyWriter();
    static final PrintWriter out = myOut.out;
    static int n;
    static int m;
    static List<List<Integer>> graph = new ArrayList<>();
    static int[] dist;
    private static void add(int a, int b) {
        if (a == b) {
            return;
        }
        graph.get(a).add(b);
    }
    public static void main(String[] args) {
        n = in.nextInt();
        m = in.nextInt();
        dist = new int[n + 1];
        Arrays.fill(dist, -1);
        for (int i = 0; i < n + 1; i++) graph.add(new ArrayList<>());
        for (int i = 0; i < m; i++) add(in.nextInt(), in.nextInt());
        int res = bfs();
        out.println(res);
        out.flush();
        out.close();
    }
    private static int bfs() {
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(1);
        dist[1] = 0;
        while (!queue.isEmpty()) {
            Integer idx = queue.poll();
            List<Integer> list = graph.get(idx);
            for (int x : list) {
                if (dist[x] == -1) {
                    dist[x] = dist[idx] + 1;
                    queue.offer(x);
                }
            }
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
