/*
 * MergeSortUtils.java
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

package com.dj.zuan.dsa.vector.utils.sort;

import com.dj.zuan.dsa.print.Print;

/**
 * Time complexity: O(nlogn)
 *
 * @author DJ-Zuan on 2020-11-04
 *
 */
public class MergeSortUtils {

    /**
     * 清华大学 《数据结构（C++语言版）》 第62页 源码及注释改版
     *
     * @param elem
     * @param lo
     * @param hi
     */
    public static void mergeSort(int[] elem, int lo, int hi) { //向量归并排序
        if (hi - lo < 2) return; //单元素区间自然有序，否则...
        int mi = (lo + hi) >> 1; //以中点为界
        mergeSort(elem, lo, mi); mergeSort(elem, mi, hi); //分别排序
        merge(elem, lo, mi, hi); //归并
    }

    /**
     * 清华大学 《数据结构（C++语言版）》 第63页 源码及注释改版
     * 有序向量的归并
     *
     * @param elem
     * @param lo
     * @param mi
     * @param hi
     */
    public static void merge(int[] elem, int lo, int mi, int hi) { //各自有序的子向量[lo, mi)和[mi, hi)
        int[] tmp = new int[mi - lo]; //
        int leftSize = mi - lo;
        for (int i = 0; i < leftSize; tmp[i] = elem[lo + i++]); //复制前子向量
        int rightSize = hi - mi;
        for (int i = 0, j = 0; i < leftSize || j < rightSize;) {
            if (i < leftSize && (j >= rightSize || tmp[i] <= elem[mi + j]))
                elem[lo++] = tmp[i++];
            if (j < rightSize && (i >= leftSize || elem[mi + j] < tmp[i]))
                elem[lo++] = elem[mi + j++];
        }
    }

    public static void main(String[] args) {
        int[] origin = {3, 4, 1, 5, 2};
        int[] a = origin.clone();
        mergeSort(a, 0, 5);
        Print.printlnArray(a);
    }
}
