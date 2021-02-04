package search_graph_theory.staining_method;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class StainingMethod {
    static final MyScanner in = new MyScanner();
    static final MyWriter myOut = new MyWriter();
    static final PrintWriter out = myOut.out;
    static int n;
    static int m;
    static int[] colors;
    static List<List<Integer>> graph = new ArrayList<>();
    private static void add(int a, int b) {
        graph.get(a).add(b);
        graph.get(b).add(a);
    }
    public static void main(String[] args) {
        n = in.nextInt();
        m = in.nextInt();
        colors = new int[n + 1];
        for (int i = 0; i < n + 1; i++) graph.add(new ArrayList<>());
        for (int i = 0; i < m; i++) add(in.nextInt(), in.nextInt());
        boolean res = true;
        for (int i = 1; i < n + 1; i++) {
            if (colors[i] == 0) {
               res = staining(i, 1);
               if (!res) {
                   break;
               }
            }
        }
        if (res) {
            out.println("Yes");
        }else {
            out.println("No");
        }
        out.flush();
        out.close();
    }
    private static boolean staining(int idx, int color) {
        List<Integer> list = graph.get(idx);
        colors[idx] = color;
        for (int x : list) {
            if (colors[x] == 0) {
                if (!staining(x, 3 - color)) {
                    return false;
                }
            }else if (colors[x] == color) {
                return false;
            }
        }
        return true;
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
