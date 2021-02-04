package search_graph_theory.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class QueenProblem {
    static final MyScanner in = new MyScanner();
    static final MyWriter myOut = new MyWriter();
    static final PrintWriter out = myOut.out;
    static int n;
    static boolean[] col;
    static boolean[] diagonal;
    static boolean[] backDiagonal;
    static char[][] map;

    public static void main(String[] args) {
        n = in.nextInt();
        col = new boolean[n];
        diagonal = new boolean[n << 1];
        backDiagonal = new boolean[n << 1];
        map = new char[n][n];
        for (int i = 0; i < n; i++) Arrays.fill(map[i], '.');
        dfs(0);
        out.flush();
        out.close();
    }

    private static void dfs(int num) {
        if (num == n) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++)
                    out.print(map[i][j]);
                out.println();
            }
            out.println();
            return;
        }
        for (int j = 0; j < n; j++) {
            if (!col[j] && !diagonal[num - j + n] && !backDiagonal[num + j]) {
                col[j] = diagonal[num - j + n] = backDiagonal[num + j] = true;
                map[num][j] = 'Q';
                dfs(num + 1);
                map[num][j] = '.';
                col[j] = diagonal[num - j + n] = backDiagonal[num + j] = false;
            }
        }
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
