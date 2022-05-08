package top100;

public class Top067_simple {

    public static void main(String[] args) {
        Top067_simple main = new Top067_simple();
        String a = "1010";
        String b = "1011";
        System.out.println(main.addBinary(a, b));
        System.out.println(main.toDecimal(a));
        System.out.println(main.toDecimal(b));
        System.out.println(3 << 2);
    }

    public String addBinary(String a, String b) {
        return toBinary(toDecimal(a) + toDecimal(b));
    }

    int toDecimal(String a) {
        int res = 0;
        int len = a.length();
        for (int i = 0; i < len; i++) {
            res += (a.charAt(i) - '0') << (len - 1 - i);
        }
        return res;
    }

    String toBinary(int a) {
        StringBuffer sb = new StringBuffer();
        while (a != 0) {
            sb.append(String.valueOf(a % 2));
            a = a / 2;
        }
        return sb.reverse().toString();
    }
}
