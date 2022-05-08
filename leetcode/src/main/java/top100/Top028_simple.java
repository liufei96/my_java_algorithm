package top100;

/**
 * 28. 实现 strStr() [ https://leetcode-cn.com/problems/implement-strstr/  ]
 * <p>
 * <p>
 * 实现strStr()函数。
 * <p>
 * 给你两个字符串haystack 和 needle ，请你在 haystack 字符串中找出 needle 字符串出现的第一个位置（下标从 0 开始）。如果不存在，则返回 -1 。
 * <p>
 * 说明：
 * <p>
 * 当needle是空字符串时，我们应当返回什么值呢？这是一个在面试中很好的问题。
 * <p>
 * 对于本题而言，当needle是空字符串时我们应当返回 0 。这与 C 语言的strstr()以及 Java 的indexOf()定义相符。
 * <p>
 * 示例 1：
 * <p>
 * 输入：haystack = "hello", needle = "ll"
 * 输出：2
 * <p>
 * <p>
 * 示例 2：
 * <p>
 * 输入：haystack = "aaaaa", needle = "bba"
 * 输出：-1
 * <p>
 * 示例 3：
 * <p>
 * 输入：haystack = "", needle = ""
 * 输出：0
 */
public class Top028_simple {

    public static void main(String[] args) {
        Top028_simple top028 = new Top028_simple();
        String haystack = "hello";
        String needle = "llo";
        int result = top028.strStr3(haystack, needle);
        System.out.println(result);
    }

    public int strStr(String haystack, String needle) {
        if (needle.length() == 0) return 0;
        int len = haystack.length();
        int nLen = needle.length();
        for (int i = 0; i < len ; i++) {
            if (haystack.charAt(i) == needle.charAt(0)) {
                for (int j = 0; j < nLen && i + j < len; j++) {
                    if (needle.charAt(j) != haystack.charAt(i + j)) {
                        break;
                    }
                    if (j + 1 == nLen) {
                        return i;
                    }
                }
            }
        }
        return -1;
    }

    /**
     * 优化，KMP算法
     * @param haystack
     * @param needle
     * @return
     */
    public int strStr2(String haystack, String needle) {
        int n = haystack.length();
        int m = needle.length();
        if (m == 0) return 0;
        int[] pi = new int[m];
        for (int i = 1,j=0; i < m; i++) {
            while (j > 0 && needle.charAt(j) != needle.charAt(j)) {
                j = pi[j - 1];
            }
            if (needle.charAt(j) == needle.charAt(i)) {
                j++;
            }
            pi[i] = j;
        }
        for (int i = 0, j = 0; i < n; i++) {
            while (j > 0 && haystack.charAt(i) != needle.charAt(j)) {
                j = pi[j - 1];
            }
            if (haystack.charAt(i) == needle.charAt(j)) {
                j++;
            }
            if (j == m) {
                return i - m + 1;
            }
        }
        return -1;
    }

    /**
     * 双指针
     * @param haystack
     * @param needle
     * @return
     */
    public int strStr3(String haystack, String needle) {
        if (needle.length() == 0) return 0;
        int left = 0, right = 0, index = 0;
        while (right < haystack.length() && index < needle.length()) {
            if (haystack.charAt(right) != needle.charAt(index)) {
                left++;
                right = left;
                index = 0;
            } else {
                right++;
                index++;
            }
        }
        return index == needle.length() ? left : -1;
    }
}
