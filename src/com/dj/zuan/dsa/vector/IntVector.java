/*
 * IntVector.java
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

/**
 * 清华大学 《数据结构（C++语言版）》 第29页 源码及注释改版
 *
 * @author DJ-Zuan on 2020-10-28
 *
 */
public interface IntVector {

    /**
     * 报告向量当前的规模（元素总数）
     *
     * @return
     */
    int size();

    /**
     * 获取秩为r的元素
     *
     * @param r
     * @return
     */
    int get(int r);

    /**
     * 用e替换秩为r元素的数值
     *
     * @param r
     * @param e
     * @return
     */
    int put(int r, int e);

    /**
     * e作为秩为r元素插入，原后继元素依次后移
     *
     * @param r
     * @param e
     * @return
     */
    int insert(int r, int e);

    /**
     * 删除秩为r的元素，返回该元素中原存放的对象
     *
     * @param r
     * @return
     */
    int remove(int r);

    /**
     * 判断所有元素是否已按非降序排列
     *
     * @return
     */
    int disordered();

    /**
     * 调整各元素的位置，使之按非降序排列
     */
    void sort();

    /**
     * 无序向量的顺序查找
     * 查找等于e且秩最大的元素
     *
     * @param e
     * @return
     */
    int find(int e);

    /**
     * 查找目标元素e，返回不大于e且秩最大的元素
     *
     * @param e
     * @return
     */
    int search(int e);

    /**
     * 剔除无序向量中重复元素
     *
     * @return
     */
    int deduplicate();

    /**
     * 提出重复元素
     *
     * @return
     */
    int uniquify();

    /**
     * 遍历向量并统一处理所有元素，处理方法由函数对象指定
     *
     * c++源码：
     *   void traverse(void(*) (T&)); //遍历（使用函数指针，只读或局部性修改）
     */
//    void traverse();

    /**
     *
     * @return
     */
    int[] toArray();
}
