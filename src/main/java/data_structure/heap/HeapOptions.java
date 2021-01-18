package data_structure.heap;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.StringTokenizer;

public class HeapOptions {
    static final MyScanner in = new MyScanner();
    static final MyWriter myOut = new MyWriter();
    static final PrintWriter out = myOut.out;
    static int[] heap;
    static int size;
    static int[] hp;
    static int[] ph;
    static int idx;

    private static void heapSwap(int i, int j) {
        MyOptions.swap(ph, hp[i], hp[j]);
        MyOptions.swap(hp, i, j);
        MyOptions.swap(heap, i, j);
    }

    private static void down(int idx) {
        int t = idx;
        int left = idx * 2;
        int right = idx * 2 + 1;
        if (left <= size && heap[left] < heap[t]) {
            t = left;
        }
        if (right <= size && heap[right] < heap[t]) {
            t = right;
        }
        if (t == idx) {
            return;
        }
        heapSwap(t, idx);
        down(t);
    }

    private static void up(int idx) {
        while (idx / 2 >= 1 && heap[idx / 2] > heap[idx]) {
            heapSwap(idx / 2, idx);
            idx /= 2;
        }
    }

    private static void insert(int val) {
        heap[++size] = val;
        ph[++idx] = size;
        hp[size] = idx;
        up(size);
    }

    private static void deleteMin() {
        heapSwap(1, size);
        size--;
        down(1);
    }

    private static void deleteK(int k) {
        int t = ph[k];
        heapSwap(t, size);
        size--;
        down(t);
        up(t);
    }

    private static void changeK(int k, int val) {
        int t = ph[k];
        heap[t] = val;
        down(t);
        up(t);
    }

    public static void main(String[] args) {
        int n = in.nextInt();
        heap = new int[n + 1];
        hp = new int[n + 1];
        ph = new int[n + 1];
        while (n-- > 0) {
            String str = in.nextLine();
            String[] ss = str.split(" ");
            if ("I".equals(ss[0])) {
                insert(Integer.parseInt(ss[1]));
            } else if ("PM".equals(ss[0])) {
                out.println(heap[1]);
            } else if ("DM".equals(ss[0])) {
                deleteMin();
            } else if ("D".equals(ss[0])) {
                deleteK(Integer.parseInt(ss[1]));
            } else if ("C".equals(ss[0])) {
                changeK(Integer.parseInt(ss[1]), Integer.parseInt(ss[2]));
            }
        }
        out.flush();
        out.close();
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

    private static class MyOptions {
        private static double doubleAdd(double x, double y) {
            BigDecimal bigDecimal = new BigDecimal(x);
            BigDecimal bigDecimal2 = new BigDecimal(y);
            return bigDecimal.add(bigDecimal2).doubleValue();
        }

        private static double subtractDouble(double x, double y) {
            BigDecimal bigDecimal = new BigDecimal(x);
            BigDecimal bigDecimal2 = new BigDecimal(y);
            return bigDecimal.subtract(bigDecimal2).doubleValue();
        }

        private static double divideDouble(double x, double y) {
            BigDecimal bigDecimal = new BigDecimal(x);
            BigDecimal bigDecimal2 = new BigDecimal(y);
            return bigDecimal.divide(bigDecimal2).doubleValue();
        }

        private static double multiplyDouble(double x, double y) {
            BigDecimal bigDecimal = new BigDecimal(x);
            BigDecimal bigDecimal2 = new BigDecimal(y);
            return bigDecimal.multiply(bigDecimal2).doubleValue();
        }

        private static int[] findMax(int[] arr) {
            int max = Integer.MIN_VALUE;
            int index = -1;
            for (int i = 0; i < arr.length; i++) {
                if (max < arr[i]) {
                    index = i;
                    max = arr[i];
                }
            }
            return new int[]{max, index};
        }

        private static int[] findMin(int[] arr) {
            int min = Integer.MAX_VALUE;
            int index = -1;
            for (int i = 0; i < arr.length; i++) {
                if (min > arr[i]) {
                    index = i;
                    min = arr[i];
                }
            }
            return new int[]{min, index};
        }

        private static void swap(int[] arr, int i, int j) {
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
    }
}
