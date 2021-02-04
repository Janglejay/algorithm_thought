import java.util.*;

class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        PriorityQueue<Integer> queue = new PriorityQueue<>((num1, num2) -> num2.compareTo(num1));
        int[][] arr = new int[][]{
                {2, 2}, {1, 2}, {1, 2}, {1, 1}, {1, 2}, {1, 1}, {2, 2}
        };
        int res = solution.numEquivDominoPairs(arr);
        System.out.println(res);
    }
    public int numEquivDominoPairs(int[][] dominoes) {
        int n = dominoes.length;
        for (int[] arr : dominoes) Arrays.sort(arr);
        Set[] map0 = new HashSet[10];
        for (int i = 0; i < 10; i++) map0[i] = new HashSet();
        Set[] map1 = new HashSet[10];
        for (int i = 0; i < 10; i++) map1[i] = new HashSet();
        for (int i = 0; i < n; i++) {
            map0[dominoes[i][0]].add(i);
            map1[dominoes[i][1]].add(i);
        }
        int res = 0;
        Set<Integer> set = new HashSet<>();
        for (int[] arr : dominoes) {
            set.clear();
            set.addAll(map0[arr[0]]);
            set.retainAll(map1[arr[1]]);
            int number = set.size() - 1;
            res += number;
        }
        return res >> 1;
    }
}