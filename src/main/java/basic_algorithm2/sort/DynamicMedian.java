package basic_algorithm2.sort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class DynamicMedian {
    static final MyScanner in = new MyScanner();
    static final MyWriter myOut = new MyWriter();
    static final PrintWriter out = myOut.out;

    private static class OxymoronHeap {
        private PriorityQueue<Integer> less;
        private PriorityQueue<Integer> large;

        public OxymoronHeap() {
            less = new PriorityQueue<>();
            large = new PriorityQueue<>((a, b) -> Integer.compare(b, a));
        }

        public void add(int x) {
            if (less.isEmpty() || less.peek() <= x) {
                less.add(x);
            } else {
                large.add(x);
            }
            if (less.size() > large.size() + 1) {
                large.add(less.poll());
            }
            if (large.size() > less.size()) {
                less.add(large.poll());
            }
        }

        public int getMedian() {
            return less.peek();
        }
    }

    public static void main(String[] args) {
        int t = in.nextInt();
        while (t-- > 0) {
            int number = in.nextInt();
            int m = in.nextInt();
            OxymoronHeap heap = new OxymoronHeap();
            out.println(number + " " + ((m >> 1) + 1));
            int count = 0;
            for (int i = 1; i <= m; i++) {
                heap.add(in.nextInt());
                if ((i & 1) == 1) {
                    int median = heap.getMedian();
                    if (count >= 10) {
                        count = 0;
                        out.println();
                    }
                    out.print(median + " ");
                    count++;
                }
            }
            out.println();
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
