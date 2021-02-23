package basic_algorithm2.binary_algorithm;
import java.util.ArrayList;
import java.util.List;

public class SpecialOrder {
    // The compare API is defined in the parent class Relation:
    //      boolean compare(int a, int b);
    //      return boolean means whether a is less than b.
    private boolean compare(int a, int b) {
        return true;
    }
    public int[] specialSort(int n) {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        for (int i = 2; i <= n; i++) {
            int l = 0;
            int r = list.size() - 1;
            while (l < r) {
                int mid = l + r + 1 >> 1;
                if (compare(list.get(mid), i)) {
                    l = mid;
                } else {
                    r = mid - 1;
                }
            }
            // 当满足条件时，将i插入到l+1位置
            if (compare(list.get(l), i))
                list.add(l + 1, i);
            //不符合条件时，说明找不到一个比他小的数
            //即他本身就是最小的数，此时把他插入到开头位置
            else list.add(0, i);
        }
        int[] res = new int[n];
        for (int i = 0; i < list.size(); i++) {
            res[i] = list.get(i);
        }
        return res;
    }
}
