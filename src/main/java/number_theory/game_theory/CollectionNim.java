package number_theory.game_theory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class CollectionNim {
    static final MyScanner in = new MyScanner();
    static final MyWriter myOut = new MyWriter();
    static final PrintWriter out = myOut.out;

    private static class SG {
        //可以取的个数的集合
        private int[] collection;
        //sg函数的值
        private int[] sg;
        //石子个数集合
        private int[] stoneNumber;
        private final int N;

        public SG(int N, int[] collection, int[] stoneNumber) {
            this.collection = collection;
            this.stoneNumber = stoneNumber;
            this.N = N;
            this.sg = new int[N];
            Arrays.fill(sg, -1);
        }

        public int getXor() {
            int res = 0;
            for (int x : stoneNumber) {
                res ^= sgFunction(x);
            }
            return res;
        }

        //记忆化搜索过程
        private int sgFunction(int x) {
            //状态已经被算过
            if (sg[x] != -1) {
                return sg[x];
            }
            //存储当前状态所有可以到达的状态
            Set<Integer> set = new HashSet<>();
            for (int out : collection) {
                if (x >= out) {
                    set.add(sgFunction(x - out));
                }
            }
            int number = mex(set);
            sg[x] = number;
            return number;
        }
        //mex函数
        //求出当前集合不存在的最小自然数
        private int mex(Set<Integer> set) {
            for (int i = 0; ; i++) {
                if (!set.contains(i)) {
                    return i;
                }
            }
        }
    }

    public static void main(String[] args) {
        int n = in.nextInt();
        int[] collection = new int[n];
        for (int i = 0; i < n; i++) {
            collection[i] = in.nextInt();
        }
        int m = in.nextInt();
        int[] tones = new int[m];
        for (int i = 0; i < m; i++) {
            tones[i] = in.nextInt();
        }
        int max = -1;
        for (int x : tones) {
            max = Math.max(max, x);
        }
        SG sg = new SG(max + 1, collection, tones);
        int res = sg.getXor();
        if (res != 0) {
            out.println("Yes");
        }else {
            out.println("No");
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
