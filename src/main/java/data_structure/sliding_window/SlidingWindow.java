package data_structure.sliding_window;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class SlidingWindow {
    static final MyScanner in = new MyScanner();
    static final MyWriter myOut = new MyWriter();
    static final PrintWriter out = myOut.out;

    public static void main(String[] args) {
//        function1();
        function2();
    }

    public static void function2() {
        int n = in.nextInt();
        int k = in.nextInt();
        int[] arr = new int[n];
        in.nextIntegerArray(arr);
        int r = 0;
        LinkedList<Integer> list = new LinkedList<>();
        while (r < n) {
            if (!list.isEmpty() && r - list.getFirst() + 1 > k) {
                list.removeFirst();
            }
            while (!list.isEmpty() && arr[list.getLast()] >= arr[r]) {
                list.removeLast();
            }
            list.add(r);
            if (r >= k - 1)
                out.print(arr[list.getFirst()] + " ");
            r++;
        }
        out.println();
        list.clear();
        r = 0;
        while (r < n) {
            if (!list.isEmpty() && r - list.getFirst() + 1 > k) {
                list.removeFirst();
            }
            while (!list.isEmpty() && arr[list.getLast()] <= arr[r]) {
                list.removeLast();
            }
            list.add(r);
            if (r >= k - 1)
                out.print(arr[list.getFirst()] + " ");
            r++;
        }
        out.flush();
        out.close();
    }

    private static void function1() {
        int n = in.nextInt();
        int k = in.nextInt();
        int[] arr = new int[n];
        in.nextIntegerArray(arr);
        int r = 0;
        List<Integer> list = new ArrayList<>();
        while (r < n) {
            if (!list.isEmpty() && r - list.get(0) + 1 > k) {
                list.remove(0);
            }
            while (!list.isEmpty() && arr[list.get(list.size() - 1)] >= arr[r]) {
                list.remove(list.size() - 1);
            }
            list.add(r);
            if (r >= k - 1)
                out.print(arr[list.get(0)] + " ");
            r++;
        }
        out.println();
        list.clear();
        r = 0;
        while (r < n) {
            if (!list.isEmpty() && r - list.get(0) + 1 > k) {
                list.remove(0);
            }
            while (!list.isEmpty() && arr[list.get(list.size() - 1)] <= arr[r]) {
                list.remove(list.size() - 1);
            }
            list.add(r);
            if (r >= k - 1)
                out.print(arr[list.get(0)] + " ");
            r++;
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
