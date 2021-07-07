package com.liufei.swordFinger;

/**
 * 剑指Office第三题：变态跳台阶问题
 * <p>
 * 一只青蛙一次可以跳上1级台阶，也可以跳上2级……它也可以跳上n级。求该青蛙跳上一个n级的台阶总共有多少种跳法。
 * <p>
 * 假设n>=2，第一步有n种跳法：跳1级、跳2级、到跳n级 跳1级，剩下n-1级，则剩下跳法是f(n-1) 跳2级，剩下n-2级，则剩下跳法是f(n-2) ...... 跳n-1级，剩下1级，
 * 则剩下跳法是f(1) 跳n级，剩下0级，则剩下跳法是f(0) 所以在n>=2的情况下：
 * f(n)=f(n-1)+f(n-2)+...+f(1) 因为f(n-1)=f(n-2)+f(n-3)+...+f(1) 所以f(n)=2*f(n-1) 又f(1)=1,所以可得f(n)=2^(number-1)
 */
public class Algorithm_003 {

    public static void main(String[] args) {
        Algorithm_003 algorithm003 = new Algorithm_003();
        int n = 10;
        int res = algorithm003.JumpFloorII(n);
        System.out.println(res);
    }

    /**
     * 补充：
     * java中有三种移位运算符：
     *
     * “<<” : 左移运算符，等同于乘2的n次方
     * “>>”: 右移运算符，等同于除2的n次方
     * “>>>” 无符号右移运算符，不管移动前最高位是0还是1，右移后左侧产生的空位部分都以0来填充。与>>类似。
     * 例： int a = 16; int b = a << 2;//左移2，等同于16 * 2的2次方，也就是16 * 4 int c = a >> 2;//右移2，等同于16 / 2的2次方，也就是16 / 4
     *
     * @param number
     * @return
     */
    int JumpFloorII(int number) {
        return 1 << --number;//2^(number-1)用位移操作进行，更快
    }
}
