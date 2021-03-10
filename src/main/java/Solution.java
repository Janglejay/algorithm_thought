import java.util.ArrayList;
import java.util.List;

class Solution {
    public String removeDuplicates(String S) {
        List<Integer>[] lists = new ArrayList[26];
        for (int i = 0; i < 26; i++) lists[i] = new ArrayList<>();
        int divide = 0;
        for (int i = 0; i < S.length(); i++) {
            char c = S.charAt(i);
            List<Integer> list = lists[c - 'a'];
            list.add(i - divide);
            if (list.size() > 1 && list.get(list.size() - 1) == list.get(list.size() - 2) + 1) {
                divide += 2;
                list.remove(list.size() - 1);
                list.remove(list.size() - 1);
            }
        }
        char[] charArray = new char[S.length() - divide];
        for (int i = 0; i < lists.length; i++) {
            for (int x : lists[i]) {
                charArray[x] = (char)('a' + i);
            }
        }
        String res = "";
        for (char c : charArray) res += c;
        return res;
    }
}