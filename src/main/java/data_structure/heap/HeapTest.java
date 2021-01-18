package data_structure.heap;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.StringTokenizer;
class Heap{
    int[] heap;
    int size;
    public void creatHeapByArray(int n, int[] arr) {
        heap = new int[n + 1];
        for (int i = 1; i < n + 1; i++) {
            heap[i] = arr[i - 1];
        }
        size = n;
        for (int i = n / 2; i >= 1; i--) {
            down(i);
        }
    }
    public void up(int idx) {
        while (idx / 2 >= 1 && heap[idx / 2] > heap[idx]) {
            int temp = heap[idx / 2];
            heap[idx / 2] = heap[idx];
            heap[idx] = temp;
            idx /= 2;
        }
    }
    public void down(int idx) {
        int left = idx * 2;
        int right = idx * 2 + 1;
        int t = idx;
        if (left <= size && heap[left] < heap[t]) {
            t = left;
        }
        if (right <= size && heap[right] < heap[t]) {
            t = right;
        }
        if (idx == t) {
            return;
        }
        int temp = heap[idx];
        heap[idx] = heap[t];
        heap[t] = temp;
        down(t);
    }
    public int poll() {
        int t = heap[1];
        heap[1] = heap[size];
        size--;
        down(1);
        return t;
    }
}
public class HeapTest {
    static final MyScanner in = new MyScanner();
    static final MyWriter myOut = new MyWriter();
    static final PrintWriter out = myOut.out;
    public static void main(String[] args) {
        int n = in.nextInt();
        int k = in.nextInt();
        int[] arr = new int[n];
        in.nextIntegerArray(arr);
        Heap heap = new Heap();
        heap.creatHeapByArray(n, arr);
        while (k-- > 0) {
            out.print(heap.poll() + " ");
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
