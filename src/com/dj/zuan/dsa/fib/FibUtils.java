/*
 * FibUtils.java
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

package com.dj.zuan.dsa.fib;

/**
 * calculate Fibonacci
 * @author DJ-Zuan on 2020-10-27
 *
 */
public class FibUtils {

    /**
     * Time complexity: O(fib(n+3))
     * 清华大学 《数据结构（C++语言版）》 第24页 源码及注释改版
     *
     * @param n
     * @return
     */
    public static long fib(int n) { //计算Fibonacci数列的第n项（二分递归版）：O(2^n)
        return (2 > n) ?
                n //若到达递归基，直接取值
                : fib(n - 1) + fib(n - 2); //否则，递归计算前两项，其和即为正解
    }

    private static class FibNum {
        Long val;
    }

    /**
     * Time complexity: O(n)
     * 清华大学 《数据结构（C++语言版）》 第25页 源码及注释改版
     * c++源码及注释如下：
     * __int64 fib(int n, __int64& prev) { //计算Fibonacci数列第n项（线性递归版）：入口形式fib(n, prev)
     *     if (0 == n) //若到达递归基，则
     *         { prev = 1; return 0; } //直接取值：fib(-1) = 1, fib(0) = 0
     *     else { //否则
     *         __int64 prevPrev; prev = fib(n - 1, prevPrev); //递归计算前两项
     *         return prevPrev + prev; //其和即为正解
     *     }
     * } //用辅助变量记录前一项，返回数列的当前项，O(n)
     *
     * @param n
     * @param prev
     * @return
     */
    public static long fib(int n, FibNum prev) {
        if (0 == n)
        { prev.val = 1L; return 0; }
        else {
            FibNum prevPrev = new FibNum(); prev.val = fib(n - 1, prevPrev);
            return prevPrev.val + prev.val;
        }
    }

    public static long fibR(int n) {
        return fib(n, new FibNum());
    }

    /**
     * Time complexity: O(n)
     * 清华大学 《数据结构（C++语言版）》 第26页 源码及注释改版
     *
     * @param n
     * @return
     */
    public static long fibI(int n) { //计算Fibonacci数列的第n项（迭代版）：O(n)
        long f = 1, g = 0; //初始化：fib(-1)、fib(0)
        while (0 < n--) { g += f; f = g - f; } //依据原始定义，通过n次加法和减法计算fib(n)
        return g; //返回
    }

    public static void main(String[] args) {
        long start1 = System.currentTimeMillis();
        System.out.println(fib(40));
        System.out.println(System.currentTimeMillis() - start1);

        long start2 = System.currentTimeMillis();
        System.out.println(fibR(64));
        System.out.println(System.currentTimeMillis() - start2);

        long start3 = System.currentTimeMillis();
        System.out.println(fibI(64));
        System.out.println(System.currentTimeMillis() - start3);

        Fib f = new Fib(10610209857723L);
        System.out.println(f.get());
        System.out.println(f.prev());
        System.out.println(f.prev());
    }
}
