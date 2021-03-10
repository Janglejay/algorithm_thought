package basic_algorithm2.sort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class ChineseValentine {
    static final MyScanner in = new MyScanner();
    static final MyWriter myOut = new MyWriter();
    static final PrintWriter out = myOut.out;
    static int[] row;
    static int[] col;
    private static long work(int n, int a[]) {
        int[] sum = new int[n + 1];
        int[] constant = new int[n + 1];
        for (int i = 1; i <= n; i++) sum[i] = sum[i - 1] + a[i];
        if (sum[n] % n != 0) return -1;
        int avg = sum[n] / n;
        // c_i
        constant[1] = 0;
        for (int i = 2; i <= n; i++) constant[i] = sum[i - 1] - (i - 1) * avg;
        Arrays.sort(constant, 1, n + 1);
        long res = 0L;
        int median = constant[(n + 1) / 2];
        for (int i = 1; i < n + 1; i++) res += Math.abs(constant[i] - median);
        return res;
    }
    public static void main(String[] args) {
        int n = in.nextInt();
        int m = in.nextInt();
        int t = in.nextInt();
        row = new int[n + 1];
        col = new int[m + 1];
        while (t-- > 0) {
            row[in.nextInt()]++;
            col[in.nextInt()]++;
        }
        long r = work(n, row);
        long c = work(m, col);
        if (r != -1 && c != -1) out.println("both " + (r + c));
        else if (r != -1) out.println("row " + r);
        else if (c != -1) out.println("column " + c);
        else out.println("impossible");
        out.flush();
        out.close();
    }
    private static class MyWriter {

        private final PrintWriter out;

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
        private final BufferedReader br;
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
