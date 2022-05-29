package 每日一题;

/**
 * 468. 验证IP地址  [https://leetcode.cn/problems/validate-ip-address/]
 * <p>
 * 给定一个字符串queryIP。如果是有效的 IPv4 地址，返回 "IPv4" ；如果是有效的 IPv6 地址，返回 "IPv6" ；如果不是上述类型的 IP 地址，返回 "Neither" 。
 * <p>
 * 有效的IPv4地址 是 “x1.x2.x3.x4” 形式的IP地址。 其中0 <= xi<= 255且xi不能包含 前导零。例如:“192.168.1.1”、 “192.168.1.0” 为有效IPv4地址， “192.168.01.1” 为无效IPv4地址; “192.168.1.00” 、 “192.168@1.1” 为无效IPv4地址。
 * <p>
 * 一个有效的IPv6地址是一个格式为“x1:x2:x3:x4:x5:x6:x7:x8” 的IP地址，其中:
 * <p>
 * 1 <= xi.length <= 4
 * xi是一个 十六进制字符串 ，可以包含数字、小写英文字母( 'a' 到 'f' )和大写英文字母( 'A' 到 'F' )。
 * 在xi中允许前导零。
 * 例如 "2001:0db8:85a3:0000:0000:8a2e:0370:7334" 和 "2001:db8:85a3:0:0:8A2E:0370:7334" 是有效的 IPv6 地址，而 "2001:0db8:85a3::8A2E:037j:7334" 和 "02001:0db8:85a3:0000:0000:8a2e:0370:7334" 是无效的 IPv6 地址。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：queryIP = "172.16.254.1"
 * 输出："IPv4"
 * 解释：有效的 IPv4 地址，返回 "IPv4"
 * 示例 2：
 * <p>
 * 输入：queryIP = "2001:0db8:85a3:0:0:8A2E:0370:7334"
 * 输出："IPv6"
 * 解释：有效的 IPv6 地址，返回 "IPv6"
 * 示例 3：
 * <p>
 * 输入：queryIP = "256.256.256.256"
 * 输出："Neither"
 * 解释：既不是 IPv4 地址，又不是 IPv6 地址
 * <p>
 * <p>
 * 提示：
 * <p>
 * queryIP 仅由英文字母，数字，字符 '.' 和 ':' 组成。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/validate-ip-address
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Top0468_middle {


    public static void main(String[] args) {
        // String str = "172.16.254.1";
        String str = "192.0.0.1";
        System.out.println(validIPAddress(str));
        System.out.println(":z:".split(":", -1).length);
    }


    public static String validIPAddress(String queryIP) {
        boolean ipv4 = queryIP.contains(".");
        boolean ipv6 = queryIP.contains(":");
        if (!ipv4 && !ipv6) {
            return "Neither";
        }
        // split时加上 -1，不然长度可能会丢失1， 如 ":z:".split(":") 的长度是2，加上-1，就是3了
        String[] split = ipv4 ? queryIP.split("\\.", -1) : queryIP.split(":", -1);
        if (ipv4 && split.length != 4 || ipv6 && split.length != 8) {
            return "Neither";
        }
        for (String s : split) {
            if (ipv4) {
                boolean validIpv4 = isValidIpv4(s);
                if (!validIpv4) {
                    return "Neither";
                }
            }
            if (ipv6) {
                boolean validIpv6 = isValidIpv6(s);
                if (!validIpv6) {
                    return "Neither";
                }
            }
        }
        return ipv4 ? "IPv4" : "IPv6";
    }

    private static boolean isValidIpv4(String s) {
        int len = s.length();
        if (len == 0 || (len > 1 && s.charAt(0) == '0') || len > 3) {
            return false;
        }
        int value = 0;
        for (int i = 0; i < len; i++) {
            char ch = s.charAt(i);
            if (ch < '0' || ch > '9') {
                return false;
            }
            value += (ch - '0') * Math.pow(10, len - 1 - i);
        }
        if (value >= 0 && value <= 255) {
            return true;
        }
        return false;
    }

    private static boolean isValidIpv6(String s) {
        int len = s.length();
        if (len == 0 || len > 4) {
            return false;
        }
        for (int i = 0; i < len; i++) {
            char ch = s.charAt(i);
            if (!(ch >= 'A' && ch <= 'F'
                    || ch >= 'a' && ch <= 'f'
                    || ch >= '0' && ch <= '9')) {
                return false;
            }
        }
        return true;
    }


    public static String validIPAddress2(String IP) {
        String regex0 = "(\\d|[1-9]\\d|1\\d\\d|2[0-4]\\d|25[0-5])";
        String regexIPv4 = regex0 + "(\\." + regex0 + "){3}";
        String regex1 = "([\\da-fA-F]{1,4})";
        String regexIPv6 = regex1 + "(:" + regex1 + "){7}";

        String result = "Neither";
        if (IP.matches(regexIPv4)) {
            result = "IPv4";
        } else if (IP.matches(regexIPv6)) {
            result = "IPv6";
        }
        return result;
    }
}
