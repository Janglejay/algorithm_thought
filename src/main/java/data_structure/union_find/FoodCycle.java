package data_structure.union_find;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.StringTokenizer;
class FoodCycleUnionFind {
    int[] parent;
    int[] dist;
    public FoodCycleUnionFind(int n) {
        parent = new int[n];
        dist = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
    }

    public void union(int x, int y, int relation) {
        int px = find(x);
        int py = find(y);
        if (relation == 0) {
            parent[px] = py;
            dist[px] = dist[y] - dist[x];
        }else {
            parent[px] = py;
            dist[px] = dist[y] + 1 - dist[x];
        }
    }

    public int find(int index) {
        if (parent[index] != index) {
            int t = parent[index];
            parent[index] = find(parent[index]);
            dist[index] += dist[t];
        }
        return parent[index];
    }
}
public class FoodCycle {
    static final MyScanner in = new MyScanner();
    static final MyWriter myOut = new MyWriter();
    static final PrintWriter out = myOut.out;
    public static void main(String[] args) {
        int n = in.nextInt();
        int m = in.nextInt();
        FoodCycleUnionFind unionFind = new FoodCycleUnionFind(n + 1);
        int res = 0;
        while (m-- > 0) {
            String str = in.nextLine();
            String[] ss = str.split(" ");
            int x = Integer.parseInt(ss[1]);
            int y = Integer.parseInt(ss[2]);
            if (x > n || y > n) {
                res++;
                continue;
            }
            int px = unionFind.find(x);
            int py = unionFind.find(y);
            if ("1".equals(ss[0])) {
                if (px == py && (unionFind.dist[x] - unionFind.dist[y]) % 3 != 0) {
                    res++;
                }else if (px != py){
                   unionFind.union(x, y, 0);
                }
            } else {
                if (px == py && (unionFind.dist[x] - unionFind.dist[y] - 1) % 3 != 0) {
                    res++;
                }else if (px != py) {
                    unionFind.union(x, y, 1);
                }
            }
        }
        out.println(res);
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
