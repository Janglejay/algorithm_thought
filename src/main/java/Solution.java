import java.util.HashMap;
import java.util.Map;

class Solution {
    public int subarraysWithKDistinct(int[] arr, int k) {
        return getNumber(arr, k) - getNumber(arr, k - 1);
    }
    private int getNumber(int[] arr, int k) {
        int res = 0;
        int n = arr.length;
        Map<Integer, Integer> map = new HashMap<>();
        int l = 0, r = 0;
        while (r < n) {
            map.put(arr[r], map.getOrDefault(arr[r], 0) + 1);
            while (map.size() > k) {
                int x = arr[l++];
                int number = map.get(x);
                number--;
                if (number <= 0) map.remove(x);
                else map.put(x, number);
            }
            res += r - l + 1;
            r++;
        }
        return res;
    }
}