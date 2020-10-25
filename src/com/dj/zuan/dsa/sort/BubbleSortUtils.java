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
 * bubble sort
 * Time complexity: T(n) = O(n^2)
 *
 * @author DJ-Zuan on 2020-10-24
 *
 */
public class BubbleSortUtils {

    /**
     * 将输入的数组排序成非降序列
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

    /**
     * 清华大学 《数据结构（C++语言版）》 第5页源码及注释改版
     *
     * @param a
     * @param n
     */
    public static void bubblesort1AfromTHdsa(int[] a, int n) { //起泡排序算法（版本1A）: 0 <= n
        boolean sorted = false; //整体排序标志，首先假定尚未排序
        while (!sorted) { //在尚未确认已全局排序之前，逐趟进行扫描交换
            sorted = true; //假定已经排序
            for (int i = 1; i < n; i++) { //自左向右逐对检查当前范围a[0, n)内的各相邻元素
                if (a[i - 1] > a[i]) { //一旦a[i - 1]与a[i]逆序，则
                    a[i - 1] ^= a[i];
                    a[i] = a[i - 1] ^ a[i];
                    a[i - 1] ^= a[i]; //swap(a[i - 1], a[i]); 交换之，并
                    sorted = false; //因整体排序不能保证，需要清除排序标志
                }
            }
            n--; //至此末元素必然就位，故可以缩短待排序序列的有效长度
        }
    } //借助布尔型标志位sorted，可及时提前退出，而不致总是蛮力地做n - 1趟扫描交换

    public static void main(String[] args) {
        int[] origin = {4, 3, 5, 1, 2};
        int[] a1 = origin.clone();
        int[] a2 = origin.clone();
        bubbleSort(a1);
        bubblesort1AfromTHdsa(a2, a2.length);
        Print.printlnArray(origin);
        Print.printlnArray(a1);
        Print.printlnArray(a2);
    }
}
