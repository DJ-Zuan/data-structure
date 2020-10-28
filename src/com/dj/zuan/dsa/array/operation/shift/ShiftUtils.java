/*
 * ShiftUtils.java
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

package com.dj.zuan.dsa.array.operation.shift;

import com.dj.zuan.dsa.print.Print;

import static com.dj.zuan.dsa.array.operation.reverse.ReverseUtils.reverse;

/**
 * @author DJ-Zuan on 2020-10-28
 *
 */
public class ShiftUtils {

    /**
     * Space complexity: O(1)
     * Time complexity: O(n)
     * 将数组a[0, n)中的元素整体循环左移k位
     *
     * 清华大学 《数据结构习题解析》 第21页 [1-26] 源码及注释改版
     *
     * @param a
     * @param n
     * @param k
     */
    public static void shift(int[] a, int n, int k) {
        k %= n; //确保k <= n
        reverse(a, k); //将区间a[0, k)倒置：O(3k/2)次操作
        reverse(a, k, n - 1); //将区间a[k, n)倒置：O(3(n - k)/2)次操作
        reverse(a, n); //倒置整个数组a[0, n)倒置：O(3n/2)次操作
    }

    public static void main(String[] args) {
        int[] origin = {1, 2, 3, 4, 5};
        shift(origin, 5, 6);
        Print.printlnArray(origin);
    }
}
