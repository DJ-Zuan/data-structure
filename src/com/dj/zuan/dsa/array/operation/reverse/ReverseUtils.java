/*
 * ReverseUtils.java
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

package com.dj.zuan.dsa.array.operation.reverse;

/**
 * @author DJ-Zuan on 2020-10-28
 *
 */
public class ReverseUtils {

    public static void reverse(int[] a, int n) {
        reverse(a, 0, n - 1);
    }

    /**
     * 清华大学 《数据结构（C++语言版）》 第20页 源码及注释改版
     *
     * @param a
     * @param lo
     * @param hi
     */
    public static void reverse(int[] a, int lo, int hi) { //数组倒置（多递归基递归版）
        if (lo < hi) {
            a[lo] ^= a[hi];
            a[hi] = a[lo] ^ a[hi];
            a[lo] ^= a[hi]; //swap(a[lo], a[hi]); 交换a[lo]和a[hi]
            reverse(a, lo + 1, hi - 1); //递归倒置a(lo, hi)
        } //else隐含了两种递归基
    } //O(hi - lo + 1)
}
