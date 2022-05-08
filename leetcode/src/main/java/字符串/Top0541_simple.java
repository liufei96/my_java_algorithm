package 字符串;

/**
 * 
 */
public class Top0541_simple {

    public static void main(String[] args) {
        String s = "abcdefg";
        int k = 2;
        String s1 = reverseStr2(s, k);
        System.out.println(s1);
    }


    public static String reverseStr(String s, int k) {
        char[] chars = s.toCharArray();
        int n = chars.length;
        int count = (n + 2 * k - 1) / (2 * k);
        for (int i = 0; i < count; i++) {
            int left = i * 2 * k, right = Math.min(left + k - 1, n - 1);
            while (left < right) {
                char tmp = chars[left];
                chars[left] = chars[right];
                chars[right] = tmp;
                right--;
                left++;
            }
        }
        return new String(chars);
    }

    public static String reverseStr2(String s, int k) {
        char[] chars = s.toCharArray();
        int n = chars.length;
        for (int i = 0; i < n; i += 2 * k) {
            int left = i, right = Math.min(left + k - 1, n - 1);
            while (left < right) {
                char tmp = chars[left];
                chars[left] = chars[right];
                chars[right] = tmp;
                right--;
                left++;
            }
        }
        return new String(chars);
    }

}
