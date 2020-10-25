/*
 * CountOnesUtils.java
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

package com.dj.zuan.dsa.bit.operation;

/**
 * count ones
 * 统计n中数位1的数目
 *
 * @author DJ-Zuan on 2020-10-25
 *
 */
public class CountOnesUtils {

    /**
     * Time complexity: T(n) = O(logn)
     * 清华大学 《数据结构（C++语言版）》 第13页顶 源码及注释改版
     *
     * @param n n >= 0
     * @return
     */
    public static int countOnes(int n) { //统计整数二进制展开中数位1的总数：O(logn)
//        if (n < 0) {
//            throw new IllegalArgumentException("n cannot be less than 0");
//        }

        int ones = 0; //计数器复位
        while (n > 0) { //在n缩减至0之前，反复地
            ones += n & 1; //检查最低位，若为1则计数
            n >>= 1; //右移一位
        }
        return ones; //返回计数
    } //等效于glibc的内置函数int _builtin_popcount (unsigned int n)

    /**
     * Time complexity: T(n) = O(ones)
     * 清华大学 《数据结构习题解析》 第8页 源码及注释改版
     *
     * @param n
     * @return
     */
    public static int countOnes1(int n) { //统计整数二进制展开中数位1的总数：O(ones)正比于数位1的总数
//        if (n < 0) {
//            throw new IllegalArgumentException("n cannot be less than 0");
//        }

        int ones = 0; //计数器复位
        while (n > 0) { //在n缩减至0之前，反复地
            ones++; //计数（至少有一位1）
            n &= n - 1; //清除当前最靠右的1
        }
        return ones; //返回计数
    } //等效于glibc的内置函数int _builtin_popcount (unsigned int n)

    public static void main(String[] args) {
        System.out.println(countOnes(7));
        System.out.println(countOnes1(7));
    }
}
