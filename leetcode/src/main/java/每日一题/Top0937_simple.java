package 每日一题;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 937. 重新排列日志文件   [ https://leetcode-cn.com/problems/reorder-data-in-log-files/ ]
 *
 * 给你一个日志数组 logs。每条日志都是以空格分隔的字串，其第一个字为字母与数字混合的 标识符 。
 *
 * 有两种不同类型的日志：
 *
 * 字母日志：除标识符之外，所有字均由小写字母组成
 * 数字日志：除标识符之外，所有字均由数字组成
 * 请按下述规则将日志重新排序：
 *
 * 所有 字母日志 都排在 数字日志 之前。
 * 字母日志 在内容不同时，忽略标识符后，按内容字母顺序排序；在内容相同时，按标识符排序。
 * 数字日志 应该保留原来的相对顺序。
 * 返回日志的最终顺序。
 *
 *
 * 示例 1：
 *
 * 输入：logs = ["dig1 8 1 5 1","let1 art can","dig2 3 6","let2 own kit dig","let3 art zero"]
 * 输出：["let1 art can","let3 art zero","let2 own kit dig","dig1 8 1 5 1","dig2 3 6"]
 * 解释：
 * 字母日志的内容都不同，所以顺序为 "art can", "art zero", "own kit dig" 。
 * 数字日志保留原来的相对顺序 "dig1 8 1 5 1", "dig2 3 6" 。
 * 示例 2：
 *
 * 输入：logs = ["a1 9 2 3 1","g1 act car","zo4 4 7","ab1 off key dog","a8 act zoo"]
 * 输出：["g1 act car","a8 act zoo","ab1 off key dog","a1 9 2 3 1","zo4 4 7"]
 *
 * 提示：
 *
 * 1 <= logs.length <= 100
 * 3 <= logs[i].length <= 100
 * logs[i] 中，字与字之间都用 单个 空格分隔
 * 题目数据保证 logs[i] 都有一个标识符，并且在标识符之后至少存在一个字
 *
 */
public class Top0937_simple {

    public static void main(String[] args) {
        String[] logs = {"dig1 8 1 5 1", "let1 art can", "dig2 3 6", "let2 own kit dig", "let3 art zero"};
        String[] strings = reorderLogFiles2(logs);
        System.out.println(Arrays.toString(strings));
    }

    /**
     * 分开
     *
     * @param logs
     * @return
     */
    public static String[] reorderLogFiles(String[] logs) {
        List<String> wordLogs = new ArrayList<>();
        List<String> digitalLog = new ArrayList<>();
        for (String log : logs) {
            int index1 = log.indexOf(" ");
            if (Character.isDigit(log.substring(index1 + 1).charAt(0))) {
                digitalLog.add(log);
            } else {
                wordLogs.add(log);
            }
        }
        // 直接对字母日志进行排序即可
        wordLogs.sort((o1, o2) -> {
            int index1 = o1.indexOf(" ");
            int index2 = o2.indexOf(" ");
            String log1 = o1.substring(index1 + 1);
            String log2 = o2.substring(index2 + 1);
            if (log1.equals(log2)) {
                // 按照标识符比
                return o1.substring(0, index1).compareTo(o2.substring(0, index2));
            } else {
                return log1.compareTo(log2);
            }
        });
        wordLogs.addAll(digitalLog);
        return wordLogs.toArray(new String[0]);
    }

    /**
     * 一起处理
     *
     * @param logs
     * @return
     */
    public static String[] reorderLogFiles2(String[] logs) {
        // 直接对字母日志进行排序即可
        Arrays.sort(logs, (o1, o2) -> {
            // 分成2分
            String[] s1 = o1.split(" ", 2);
            String[] s2 = o2.split(" ", 2);
            boolean isDigit1 = Character.isDigit(s1[1].charAt(0));
            boolean isDigit2 = Character.isDigit(s2[1].charAt(0));
            // 如果都是字母
            if (!isDigit1 && !isDigit2) {
                int res = s1[1].compareTo(s2[1]);
                if (res != 0) {
                    return res;
                }
                // 内容相等，按照标识符比
                return s1[0].compareTo(s2[0]);
            }
            // 如果s1 是数字字符，且s2 也是数字字符，则返回0，不需要排序
            // 如果s1 是数字字符，且s2 是字母字符，则返回1，s1 > s2，从小到大
            // 如果s1 是字母字符，则返回-1
            return isDigit1 ? (isDigit2 ? 0 : 1) : -1;
        });
        return logs;
    }
}
