/*
 * BasicIntVector.java
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

package com.dj.zuan.dsa.vector;

import java.util.Arrays;
import java.util.Objects;
import java.util.Random;

import static com.dj.zuan.dsa.vector.utils.search.SearchUtils.binSearch;
import static com.dj.zuan.dsa.vector.utils.search.SearchUtils.fibSearch;
import static com.dj.zuan.dsa.vector.utils.sort.MergeSortUtils.mergeSort;

/**
 * 清华大学 《数据结构（C++语言版）》 第30页 源码及注释改版
 *
 * @author DJ-Zuan on 2020-10-28
 *
 */
public class BasicIntVector implements IntVector {

    /**
     * 默认的初始容量（实际应用中可设置为更大）
     */
    private static final int DEFAULT_CAPACITY = 3;

    /**
     * 规模
     */
    private int size;

    /**
     * 容量
     */
    private int capacity;

    /**
     * 数据区
     */
    private int[] elem;

    /**
     * 复制数组区间a[lo, hi)
     *
     * @param a
     * @param lo
     * @param hi
     */
    protected void copyFrom(int[] a, int lo, int hi) { //以数组区间a[lo, hi)为蓝本复制向量
        elem = new int[capacity = 2 * (hi - lo)]; size = 0; //分配空间，规模清零
        while (lo < hi) //a[lo, hi)内的元素逐一
            elem[size++] = a[lo++]; //复制至elem[0, hi - lo)
    }

    public BasicIntVector() { this(DEFAULT_CAPACITY); }

    public BasicIntVector(int capacity) { elem = new int[capacity]; }

    public BasicIntVector(int[] a, int n) { copyFrom(a, 0, n); } //数组整体复制

    public BasicIntVector(int[] a, int lo, int hi) { copyFrom(a, lo, hi); } //区间

    public BasicIntVector(IntVector v) { copyFrom(v.toArray(), 0, v.size()); } //向量整体复制

    public BasicIntVector(IntVector v, int lo, int hi) { copyFrom(v.toArray(), lo, hi); } //区间

    @Override
    public int size() {
        return size;
    }

    @Override
    public int get(int r) {
        if (r < 0 || r >= size) {
            throw new IllegalArgumentException("rank: " + r + ", size: " + size);
        }

        return elem[r];
    }

    @Override
    public int put(int r, int e) {
        if (r < 0 || r >= size) {
            throw new IllegalArgumentException("rank: " + r + ", size: " + size);
        }

        int oldValue = elem[r];
        elem[r] = e;
        return oldValue;
    }

    /**
     * 清华大学 《数据结构（C++语言版）》 第40页 源码及注释改版
     *
     * @param r
     * @param e
     * @return
     */
    @Override
    public int insert(int r, int e) {
        if (r < 0 || r > size) {
            throw new IllegalArgumentException("rank: " + r + ", size: " + size);
        }

        expand(); //若有必要，扩容
        for (int i = size; i > r; i--) elem[i] = elem[i - 1];  //自后向前，后继元素顺次后移一个单元
        elem[r] = e; size++; //置入新元素并更新容量
        return r; //返回秩
    }

    /**
     * 清华大学 《数据结构（C++语言版）》 第41页底 源码及注释改版
     *
     * @param r
     * @return
     */
    @Override
    public int remove(int r) {
        if (r < 0 || r > size) {
            throw new IllegalArgumentException("rank: " + r + ", size: " + size);
        }

        int e = elem[r]; //备份被删除元素
        remove(r, r + 1); //调用区间删除算法，等效于对区间[r, r + 1)的删除
        return e; //返回被删除元素
    }


    /**
     * 清华大学 《数据结构（C++语言版）》 第41页顶 源码及注释改版
     *
     * @param lo
     * @param hi
     * @return
     */
    public int remove(int lo, int hi) { //删除区间[lo, hi)
        if (lo == hi) return 0; //处于效率考虑，单独处理退化情况，比如remove(0, 0)
        while (hi < size) elem[lo++] = elem[hi++]; //[hi, size)顺次前移hi - lo个单元
        size = lo; //更新规模，直接丢弃掉尾部[lo, size = hi)区间
        shrink(); //若有必要，则缩容
        return hi - lo; //返回被删除元素的数目
    }

    /**
     * 清华大学 《数据结构（C++语言版）》 第44页 源码及注释改版
     *
     * @return
     */
    @Override
    public int disordered() { //返回向量中逆序相邻元素对的总数
        int n = 0; //计数器
        for (int i = 1; i < size; i++) //逐一检查size - 1对相邻元素
            if (elem[i - 1] > elem[i]) n++; //逆序则计数
        return n; //向量有序当且仅当n = 0
    }

    @Override
    public void sort() {
        mergeSort(elem, 0, size);
    }

    @Override
    public int find(int e) { return find(e, 0, size); } //无序向量整体查找

    /**
     * 清华大学 《数据结构（C++语言版）》 第39页 源码及注释改版
     *
     * @param e
     * @param lo
     * @param hi
     * @return
     */
    public int find(int e, int lo, int hi) { //无序向量的顺序查找：返回最后一个元素e的位置；失败时，返回lo - 1
        while ((lo < --hi) && !Objects.equals(elem[hi], e)); //从后向前，顺序查找
        return hi; //若hi < lo，则意味着失败；否则hi即命中元素的秩
    }

    @Override
    public int search(int e) { return (0 >= size) ? -1 : search(e, 0, size); } //有序向量整体查找

    /**
     * 清华大学 《数据结构（C++语言版）》 第47页 源码及注释改版
     *
     * @param e
     * @param lo
     * @param hi
     * @return
     */
    public int search(int e, int lo, int hi) { //在有序向量的区间[lo, hi)内，确定不大于e的最后一个节点的秩
        return new Random().nextInt(2) > 0 ? //按各50%的概率随机使用二分查找或Fibonacci查找
                binSearch(toArray(), e, lo, hi) :
                fibSearch(toArray(), e, lo, hi);
    }

    @Override
    public int deduplicate() {
        int newSize = 1;
        for (int i = 1; i < size; i++)
            if (find(elem[i], 0, newSize) < 0)
                elem[newSize++] = elem[i];
        int oldSize = size;
        size = newSize;
        shrink();
        return oldSize - size;
    }

//    /**
//     * 清华大学 《数据结构（C++语言版）》 第42页 源码及注释改版
//     *
//     * @return
//     */
//    @Override
//    public int deduplicate() { //删除无序向量中重复元素（高效版）
//        int oldSize = size; //记录原规模
//        int i = 1; //从elem[1]开始
//        while (i < size) //自前向后逐一考察各元素elem[i]
//            find(elem[i], 0, i) < 0 ? //在其前缀中寻找与之雷同者（至多一个）
//                    i++ : remove(i); //若无雷同则继续考察其后继，否则删除雷同者
//        return oldSize - size;
//    }

    /**
     * 清华大学 《数据结构（C++语言版）》 第45页 源码及注释改版
     *
     * @return
     */
    @Override
    public int uniquify() { //有序向量重复元素剔除算法（高效版）
        int i = 0, j = 0; //各对互异"相邻"元素的秩
        while (++j < size) //逐一扫描，直至末元素
            if (elem[i] != elem[j]) //跳过雷同者
                elem[++i] = elem[j]; //发现不同元素时，向前移至紧邻于前者右侧
        size = ++i; shrink(); //直接截除尾部的多余元素
        return j - i; //向量规模变化量，即被删除元素总数
    }

    /**
     * 清华大学 《数据结构（C++语言版）》 第38页 源码及注释改版
     *
     * @param lo
     * @param hi
     */
    public void unsort(int lo, int hi) { //等概率随机置乱区间[lo, hi)
        Random r = new Random();
        for (int i = hi - lo; i > 0; i--) { //自后向前
            int j = r.nextInt(i);
            int tmp = elem[lo + i - 1];
            elem[lo + i - 1] = elem[lo + j];
            elem[lo + j] = tmp; //将v[lo + i - 1]与[lo, lo + i)中某一元素随机交换
        }
    }

    public void unsort() { unsort(0, size); } //整体置乱

    @Override
    public int[] toArray() {
        return Arrays.copyOf(elem, size);
    }

    /**
     * 清华大学 《数据结构（C++语言版）》 第34页 源码及注释改版
     */
    private void expand() { //向量空间不足时扩容
        if (size < capacity) return; //尚未满员时，不必扩容
        if (capacity < DEFAULT_CAPACITY) capacity = DEFAULT_CAPACITY; //不低于最小容量
        int[] oldElem = elem; elem = new int[capacity <<= 1]; //容量加倍
        for (int i = 0; i < size; i++)
            elem[i] = oldElem[i]; //复制原向量空间
    }

    /**
     * 清华大学 《数据结构（C++语言版）》 第36页 源码及注释改版
     */
    private void shrink() { //装填因子过小时压缩向量所占空间
        if (capacity < DEFAULT_CAPACITY << 1) return; //不致收缩到到DEFAULT_CAPACITY以下
        if (size << 2 > capacity) return; //以25%为界
        int[] oldElem = elem; elem = new int[capacity >>= 1]; //容量减半
        for (int i = 0; i < size; i++) elem[i] = oldElem[i]; //复制原向量内容
    }
}
