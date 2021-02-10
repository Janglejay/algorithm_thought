import java.util.ArrayList;
import java.util.List;

class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
//        List<Integer> integers = solution.majorityElement(new int[]{1, 1, 1, 3, 3, 2, 2, 2});
        List<Integer> integers = solution.majorityElement(new int[]{2,1,1,3,1,4,5,6});
        System.out.println(integers);
    }

    public List<Integer> majorityElement(int[] nums) {
        int a = 0;
        int b = 0;
        int numberA = -1;
        int numberB = -1;
        int n = nums.length;
        for (int x : nums) {
            if (x == numberA) {
                a++;
                continue;
            }
            if (x == numberB) {
                b++;
                continue;
            }
            if (a == 0 || b == 0) {
                if (a == 0) {
                    a++;
                    numberA = x;
                    continue;
                } else {
                    b++;
                    numberB = x;
                    continue;
                }
            }
            a--;
            b--;
        }
        List<Integer> res = new ArrayList<>();
        if (numberA != -1) {
            int count = 0;
            for (int x : nums) if (x == numberA) count++;
            if (count > n / 3) res.add(numberA);
        }
        if (numberB != -1 && numberB != numberA) {
            int count = 0;
            for (int x : nums) if (x == numberB) count++;
            if (count > n / 3) res.add(numberB);
        }
        return res;
    }
}