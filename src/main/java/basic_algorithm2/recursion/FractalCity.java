package basic_algorithm2.recursion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class FractalCity {
    static final MyScanner in = new MyScanner();
    static final MyWriter myOut = new MyWriter();
    static final PrintWriter out = myOut.out;

    private static class Point{
        long x;
        long y;

        public Point(long x, long y) {
            this.x = x;
            this.y = y;
        }
    }
    private static Point get(long n, long a) {
        if (n == 0) return new Point(0, 0);
        long block = 1L << (n * 2 - 2);
        long len = 1L << (n - 1);
        Point p = get(n - 1, a % block);
        long x = p.x;
        long y = p.y;
        int number = (int) (a / block);
        if (number == 0) return new Point(y, x);
        else if (number == 1) return new Point(x, y + len);
        else if (number == 2) return new Point(x + len, y + len);
        else return new Point(len * 2 - 1 - y, len - 1 - x);
    }
    public static void main(String[] args) {
        int m = in.nextInt();
        while (m-- > 0) {
            long n = in.nextLong();
            long a = in.nextLong();
            long b = in.nextLong();
            Point pa = get(n, a - 1);
            Point pb = get(n, b - 1);
            double dx = pa.x - pb.x;
            double dy = pa.y - pb.y;
            out.printf("%.0f\n", Math.sqrt((dx * dx + dy * dy)) * 10);
        }
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
