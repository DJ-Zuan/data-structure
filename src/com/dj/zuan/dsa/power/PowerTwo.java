/*
 * PowerTwo.java
 *
 * MIT License
 *
 * Copyright (c) 2020 DJ-Zuan
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 */

package com.dj.zuan.dsa.power;

/**
 * calculate 2^n
 *
 * @author DJ-Zuan on 2020-10-26
 *
 */
public class PowerTwo {

    public static long power2_I1(int n) { //计算 2^n ， n > 0
//        if (n < 0) {
//            throw new IllegalArgumentException("n cannot be less than 0");
//        }

        if (n == 0) { //如果n为0
            return 1; //返回2^0
        }

        int mark = n; //标志，记录数位1的位置，用n初始化
        while ((mark & (mark - 1)) > 0) { //如果 mark 中所剩数位1的个数多余1
            mark &= mark - 1; //清除最右边的数位1
        } //至此，mark为n中最高位的1所代表的值

        long result = 1; //记录结果
        while (mark > 0) { //如果mark大于0
            result *= result; //result取平方
            if ((n & mark) > 0) { //如果n在mark所在的位上数位位1
                result *= 2; //result乘2
            }
            mark >>= 1; //标志位右移一位
        }
        return result;
    }

    /**
     * 清华大学 《数据结构习题解析》 第10页 [1-14] 源码及注释改版
     *
     * @param n
     * @return
     */
    public static long power2_I2(int n) { //幂函数2^n算法（优化迭代版），n >= 0
//        if (n < 0) {
//            throw new IllegalArgumentException("n cannot be less than 0");
//        }

        long pow = 1; //O(1)：累积器初始化为2^0
        long p = 2; //O(1)：累乘项初始化为2
        while (0 < n) { //O(logn)：迭代log(n)轮，每轮都
            if (0 < (n & 1)) //O(1)：根据当前比特位是否为1，决定是否
                pow *= p; //O(1)：将当前累乘项计入累积器
            n >>= 1; //O(1)：指数减半
            p *= p; //O(1)：累乘项自乘
        }
        return pow; //O(1)：返回累积器
    } //O(logn) = O(r)，r为输入指数n的比特位数

    public static void main(String[] args) {
        System.out.println(power2_I1(0));
        System.out.println(power2_I1(1));
        System.out.println(power2_I1(5));

        System.out.println(power2_I2(0));
        System.out.println(power2_I2(1));
        System.out.println(power2_I2(5));
    }
}
