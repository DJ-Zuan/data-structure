/*
 * SearchUtils.java
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

package com.dj.zuan.dsa.vector.utils.search;

import com.dj.zuan.dsa.fib.Fib;

/**
 * @author DJ-Zuan on 2020-11-04
 *
 */
public class SearchUtils {

    /**
     * 清华大学 《数据结构（C++语言版）》 第56页 源码及注释改版
     *
     * @param elem
     * @param e
     * @param lo
     * @param hi
     * @return
     */
    public static int binSearch(int[] elem, int e, int lo, int hi) {
        while (lo < hi) {
            int mi = (lo + hi) >> 1;
            if (e < elem[mi])
                hi = mi;
            else
                lo = mi + 1;
        }
        return --lo;
    }

    /**
     * 清华大学 《数据结构（C++语言版）》 第52页 源码及注释改版
     * Fibonacci查找算法（版本A）：在有序向量的区间[lo, hi)内查找元素e, 0 <= lo <= hi <= size
     *
     * @param elem
     * @param e
     * @param lo
     * @param hi
     * @return
     */
    public static int fibSearch(int[] elem, int e, int lo, int hi) {
        Fib f = new Fib(hi - lo); //用O(log_phi(n = hi - lo)时间创建fib数列
        while (lo < hi) {
            while (hi - lo < f.get()) f.prev(); //通过向前顺序查找（分摊O(1)）——至多迭代几次？
            int mi = lo + (int) f.get() - 1; //确定形如Fib(k) - 1的轴点
            if (e < elem[mi]) hi = mi; //深入前半段[lo, mi)继续查找
            else lo = mi + 1; //深入后半段(mi, hi)继续查找
        }
        return --lo;
    }

    public static void main(String[] args) {
        int[] a = {1, 3, 5, 7, 9, 11, 13};
        System.out.println(binSearch(a, 9, 0, 7));
        System.out.println(fibSearch(a, 15, 0, 7));
    }
}
