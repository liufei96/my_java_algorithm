package 每日一题;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.regex.Pattern;

/**
 * 591. 标签验证器  [ https://leetcode-cn.com/problems/tag-validator/ ]
 */
public class Top0519_hard {

    public static void main(String[] args) {
        String code = "<DIV><UV><![CDATA[<GK><![CDATA[a]]></GK>]]></UV></DIV>";
        System.out.println(isValid(code));
        System.out.println(code.replaceAll("<!\\[CDATA\\[.*?]]>", "x"));
    }

    public static boolean isValid(String code) {
        int n = code.length();
        Deque<String> tags = new ArrayDeque<>();
        int i = 0;
        while (i < n) {
            if (code.charAt(i) == '<') {
                if (i == n - 1) {
                    return false;
                }
                if (code.charAt(i + 1) == '/') {
                    int j = code.indexOf('>', i);
                    if (j < 0) {
                        return false;
                    }
                    String tagName = code.substring(i + 2, j);
                    if (tags.isEmpty() || !tags.peek().equals(tagName)) {
                        return false;
                    }
                    tags.pop();

                    i = j + 1;
                    if (tags.isEmpty() && i != n) {
                        return false;
                    }
                } else if (code.charAt(i + 1) == '!') {
                    if (tags.isEmpty()) {
                        return false;
                    }
                    if (i + 9 > n) {
                        return false;
                    }

                    String cdata = code.substring(i + 2, i + 9);
                    if (!"[CDATA[".equals(cdata)) {
                        return false;
                    }
                    int j = code.indexOf("]]>", i);
                    if (j < 0) {
                        return false;
                    }
                    i = j + 1;
                } else {
                    int j = code.indexOf('>', i);
                    if (j < 0) {
                        return false;
                    }
                    String tagName = code.substring(i + 1, j);
                    if (tagName.length() < 1 || tagName.length() > 9) {
                        return false;
                    }
                    for (int k = 0; k < tagName.length(); ++k) {
                        if (!Character.isUpperCase(tagName.charAt(k))) {
                            return false;
                        }
                    }
                    tags.push(tagName);
                    i = j + 1;
                }
            } else {
                if (tags.isEmpty()) {
                    return false;
                }
                i++;
            }
        }
        return tags.isEmpty();
    }

    public static boolean isValid2(String code) {
        int preIndex = -1, endIndex = -1, len = code.length();
        for (int i = 0; i < code.length(); i++) {
            char ch = code.charAt(i);
            if (ch == '<') {
                if (i == 0) {
                    preIndex = i;
                } else {
                    return false;
                }
            }
            if (preIndex != -1 && ch == '>') {
                endIndex = i;
                break;
            }
        }
        if (len < 2 * (endIndex - preIndex + 1) - 1) {
            return false;
        }
        if (preIndex == -1 || endIndex == -1) {
            return false;
        }
        String preTag = code.substring(0, endIndex + 1);
        String endTag = code.substring(len - (endIndex - preIndex + 2), len);
        boolean tagValid = isValidTag(code.substring(0 + 1, endIndex));
        if (!tagValid || !compareTag(preTag, endTag)) {
            return false;
        }
        // 判断 cdata
        String cdata = code.substring(endIndex + 1, len - (endIndex - preIndex + 2));
        int cdataBeginIndex = cdata.indexOf("<![CDATA[");
        int cdataEndIndex = -1;
        if (cdataBeginIndex != -1) {
            cdataEndIndex = cdata.indexOf("]]>");
            if (cdataEndIndex == -1 || cdataEndIndex < cdataBeginIndex) {
                return false;
            }
        }
        // 注意：cdata里面的字符，不需要验证和考虑了
        int begin = -1, end = -1;
        Deque<String> stack = new ArrayDeque<>();
        StringBuilder tagStringBuilder = new StringBuilder();
        for (int i = 0; i < cdata.length(); i++) {
            char ch = cdata.charAt(i);
            if (cdataBeginIndex != -1 && cdataEndIndex != -1 && i >= cdataBeginIndex && i < cdataEndIndex + 3) {
                if (i == cdataEndIndex + 2) {
                    // 再找下一对
                    cdataBeginIndex = cdata.indexOf("<![CDATA[", i);
                    cdataEndIndex = cdata.indexOf("]]>", i);
                }
                // <A><B<![CDATA[asd]]>></B></A>
                if (begin == -1 && end == -1) {
                    continue;
                } else {
                    tagStringBuilder.append(ch);
                }
            }
            if (ch == '<') {
                begin = i;
            }
            if (begin != -1 && ch == '>') {
                end = i;
            }
            if (begin + 1 < len && begin != -1) {
                if (ch == '/' && begin + 1 == i) {
                    if (stack.isEmpty()) {
                        return false;
                    }
                } else if (ch != '<' && end == -1) {
                    tagStringBuilder.append(ch);
                }
            }
            if (end != -1) {
                String tag = tagStringBuilder.toString();
                // 判断tag内容是否符合全部大写，{1， 9}
                if (!isValidTag(tag)) {
                    return false;
                }
                // 如果tag 等于之前的tag，则直接pop出去
                if (!stack.isEmpty() && stack.peek().equals(tag)) {
                    stack.pop();
                } else {
                    stack.push(tag);
                }
                // 重置
                tagStringBuilder.setLength(0);
                begin = end = -1;
            }
        }
        return stack.isEmpty() && begin == -1 && end == -1;
    }


    static boolean isValidTag(String tag) {
        return Pattern.matches("[A-Z]{1,9}", tag);
    }

    static boolean compareTag(String beginTag, String endTag) {
        return beginTag.charAt(0) == '<' && endTag.charAt(0) == '<' && endTag.charAt(1) == '/' && beginTag.substring(1).equals(endTag.substring(2));
    }
}
