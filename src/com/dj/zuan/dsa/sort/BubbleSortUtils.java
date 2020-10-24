/*
 * BubbleSortUtils.java
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

package com.dj.zuan.dsa.sort;

import com.dj.zuan.dsa.print.Print;

/**
 * @author DJ-Zuan on 2020-10-24
 *
 */
public class BubbleSortUtils {

    /**
     * bubble sort
     * 将输入的数组排序成非降序列
     * 时间复杂度 O(n^2)
     * 算法稳定，相同大小的元素排序后的相对顺序仍保持排序前的相对位置
     *
     * @param a
     */
    public static void bubbleSort(int[] a) {
        //入参校验
        if (a == null || a.length == 0) {
            return;
        }

        int n = a.length;
        for (boolean sorted = false; !sorted;) {
            //每一趟遍历前，重置sorted字段
            sorted = true;

            //遍历未完全排序的所有元素
            // 若有逆序对：1. 调整逆序对中元素的位置 2. sorted字段置为false
            // 若无，则表示剩余元素已处于有序状态，无需继续排序
            for (int i = 0; i < n - 1; i++) {
                if (a[i] > a[i + 1]) {
                    a[i] ^= a[i + 1];
                    a[i + 1] = a[i] ^ a[i + 1];
                    a[i] = a[i] ^ a[i + 1];
                    sorted = false;
                }
            }

            //一趟遍历结束，最后一个位置一定为 "最大" 的元素，待排序元素减一
            n--;
        }
    }

    public static void main(String[] args) {
        int[] origin = {4, 3, 5, 1, 2};
        int[] a1 = origin.clone();
        bubbleSort(a1);
        Print.printlnArray(origin);
        Print.printlnArray(a1);
    }
}
