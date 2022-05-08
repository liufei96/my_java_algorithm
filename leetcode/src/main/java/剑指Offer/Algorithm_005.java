package 剑指Offer;

/**
 * 剑指 Offer 05. 替换空格  [ https://leetcode-cn.com/problems/ti-huan-kong-ge-lcof/ ]
 * <p>
 * 请实现一个函数，把字符串 s 中的每个空格替换成"%20"。
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "We are happy."
 * 输出："We%20are%20happy."
 */
public class Algorithm_005 {

    public static void main(String[] args) {
        Algorithm_005 algorithm_005 = new Algorithm_005();
        String s = "We are happy.";
        String res = algorithm_005.replaceSpace(s);
        System.out.println(res);
        System.out.println(algorithm_005.replaceSpace2(s));
    }


    public String replaceSpace(String s) {
        StringBuilder out = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ' ') {
                out.append("%20");
            } else {
                out.append(s.charAt(i));
            }
        }
        return out.toString();
    }

    public String replaceSpace2(String s) {
        return s.replaceAll(" ", "%20");
    }
}
